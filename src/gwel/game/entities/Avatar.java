package gwel.game.entities;

import com.badlogic.gdx.math.*;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.JsonWriter;
import gwel.game.anim.Animation;
import gwel.game.anim.PostureCollection;
import gwel.game.anim.Posture;
import gwel.game.graphics.*;
import gwel.game.utils.MatrixStack;
import processing.core.PGraphics;

import java.io.*;
import java.util.*;



public class Avatar {
    public ComplexShape shape;
    public boolean paused = false;
    private final Vector2 position = new Vector2();
    private final Affine2 transform = new Affine2();
    public PostureCollection postures;
    private ComplexShape[] partsList;
    public ArrayList<Shape> physicsShapes;
    private float timeFactor = 1f;
    private Posture currentPosture;
    private boolean flipX = false;
    private boolean flipY = false;
    private float scaleX = 1;
    private float scaleY = 1;
    private float angle = 0;


    public Avatar() {
        physicsShapes = new ArrayList<>();
    }


    public void setPosition(float x, float y) { position.set(x, y); }

    public Vector2 getPosition() { return position.cpy(); }


    public void setAngle(float a) { angle = a; }

    public float getAngle() { return angle; }

    /**
     * Scale geometry and animation data
     *
     * @param s
     */
    public void scale(float s) {
        scale(s, s);
    }

    /**
     * Scale geometry and animation data
     *
     * @param sx
     * @param sy
     */
    public void scale(float sx, float sy) {
        scaleX *= sx;
        scaleY *= sy;
    }

    public void setScale(float sx, float sy) {
        scaleX = sx;
        scaleY = sy;
    }

    //public float getScaleX() { return scaleX; }
    //public float getScaleY() { return scaleY; }

    public void setFlipX(boolean flip) { flipX = flip; }
    public void setFlipY(boolean flip) { flipY = flip; }


    public void scalePhysics(float s) {
        Affine2 scaleTransform = new Affine2().setToScaling(s, s);
        for (Shape shape : physicsShapes) {
            if (shape.getClass() == DrawablePolygon.class) {
                DrawablePolygon polygon = (DrawablePolygon) shape;
                polygon.hardTransform(scaleTransform);
            } else if (shape.getClass() == DrawableCircle.class) {
                DrawableCircle circle = (DrawableCircle) shape;
                circle.hardTransform(scaleTransform);
            }
        }
    }


    public void timeScale(float s) { timeFactor = s; }


    /**
     * Check if a world point is inside the avatar's shape
     * A first draw call must have been made to initialise the transform matrix
     *
     * @param x
     * @param y
     * @return
     */
    public boolean contains(float x, float y) {
        Vector2 point = new Vector2(x, y);
        transform.inv().applyTo(point);
        for (Shape shape : physicsShapes) {
            if (shape.contains(point.x, point.y))
                return true;
        }
        return false;
    }


    public void setShape(ComplexShape root) {
        shape = root;
        partsList = root.getPartsList().toArray(new ComplexShape[0]);
    }

    public ComplexShape getShape() { return shape; }


    public ComplexShape[] getPartsList() { return partsList; }


    public String[] getPartsName() {
        return shape.getIdList().toArray(new String[0]);
    }

    /**
     * Delete if outside Processing editor
     */
    /*
    public String[] getPartsNamePre() {
        return shape.getIdListPre("").toArray(new String[0]);
    }*/


    // Should be used by animation editor only (slow)
    public void setPosture(HashMap<String, Animation[]> posture, float transition) {
        Set<String> ids = posture.keySet();
        for (ComplexShape part : partsList) {
            if (ids.contains(part.getId())) {
                Animation[] animList;
                animList = posture.get(part.getId());
                part.transitionAnimation(animList, transition);
            } else {
                part.transitionAnimation(new Animation[0], transition);
            }
        }
    }

    // Should be used by animation editor only (slow)
    public void setPosture(HashMap<String, Animation[]> posture) {
        setPosture(posture, 0.2f);
    }


    // Activate a posture from the collection
    public void loadPosture(int idx) {
        loadPosture(postures.getPosture(idx));
    }

    // Activate a posture from the collection
    public void loadPosture(String postureName) {
        loadPosture(postures.getPosture(postureName));
    }

    public void loadPosture(Posture posture) {
        for (int i = 0; i < partsList.length; i++) {
            Animation[] animList = posture.groups[i];
            if (animList != null) {
                partsList[i].setAnimationList(animList);
            } else {
                partsList[i].clearAnimationList();
            }
        }
    }


    // Play every animation from the animationCollection sequencially
    public void playSequencially() {

    }


    public void update(float dtime) {
        if (!paused)
            shape.update(timeFactor * dtime);
    }

    public void resetAnimation() {
        shape.reset();
    }

    public void clearAnimation() {
        for (ComplexShape part : partsList)
            part.clearAnimationList();
    }


    public void draw(Renderer renderer) {
        transform.setToTranslation(position.x, position.y);
        transform.scale(flipX ? -scaleX : scaleX, flipY ? -scaleY : scaleY);
        transform.rotateRad(angle);
        renderer.pushMatrix(transform);
        shape.draw(renderer);
        renderer.popMatrix();
    }


    /**
     * Delete if outside Processing editor
     */
    public void drawSelectedOnly(PRenderer renderer) {
        shape.drawSelectedOnly(renderer);
    }


    public Avatar copy() {
        Avatar newAvatar = new Avatar();
        newAvatar.shape = shape.copy();
        for (Shape shape : physicsShapes)
            newAvatar.physicsShapes.add(shape.copy());
        newAvatar.scale(scaleX, scaleY);
        return newAvatar;
    }


    public static Avatar fromFile(File file) {
        return fromFile(file, true);
    }

    public static Avatar fromFile(File file, boolean loadAnim) {
        JsonValue json;
        try {
            InputStream in = new FileInputStream(file);
            json = new JsonReader().parse(in);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (com.badlogic.gdx.utils.SerializationException e) {
            System.out.println("Corrupted file");
            //e.printStackTrace();
            return null;
        }

        if (json.has("fmt_ver")) {
            String versionString = json.getString("fmt_ver");
            if (versionString == "1.0")
                return load_v1(json, loadAnim);
            else if (versionString == "2.0")
                return load_v2(json, loadAnim);
            else
                return load_v1(json, loadAnim);
        } else {
            return load_v1(json, loadAnim);
        }
    }

    private static Avatar load_v1(JsonValue json, boolean loadAnim) {
        Avatar avatar = new Avatar();

        // Load shape first
        if (json.has("geometry")) {
            JsonValue jsonGeometry = json.get("geometry");
            avatar.setShape(ComplexShape.fromJson(jsonGeometry));
        } else {
            System.out.println("No geometry data found !");
            return null;
        }

        if (loadAnim && json.has("animation")) {
            JsonValue jsonAnimation = json.get("animation");
            PostureCollection postureCollection = PostureCollection.fromJson(jsonAnimation, avatar.getPartsName());
            avatar.postures = postureCollection;
            if (postureCollection.size() > 0) {
                avatar.loadPosture(0);
            }
        }

        if (json.has("box2d")) {
            for (JsonValue jsonShape : json.get("box2d")) {
                if (jsonShape.getString("type").equals("circle")) {
                    DrawableCircle circle = new DrawableCircle(0, 0, 0);
                    circle.setCenter(jsonShape.getFloat("x"), jsonShape.getFloat("y"));
                    circle.setRadius(jsonShape.getFloat("radius"));
                    avatar.physicsShapes.add(circle);
                } else if (jsonShape.getString("type").equals("polygon")) {
                    DrawablePolygon polygon = new DrawablePolygon();
                    polygon.setVertices(jsonShape.get("vertices").asFloatArray());
                    avatar.physicsShapes.add(polygon);
                }
            }
        }
        return avatar;
    }

    private static Avatar load_v2(JsonValue json, boolean loadAnim) {
        List<Avatar> avatars = new ArrayList<>();

        if (json.has("avatars")) {
            JsonValue jsonAvatars = json.get("avatars");
            for (JsonValue jsonAvatar : jsonAvatars.iterator()) {
                Avatar avatar = new Avatar();

                // Load shape first
                if (jsonAvatar.has("geometry")) {
                    JsonValue jsonGeometry = jsonAvatar.get("geometry");
                    avatar.setShape(ComplexShape.fromJson(jsonGeometry));
                } else {
                    System.out.println("No geometry data found !");
                }

                if (json.has("box2d")) {
                    for (JsonValue jsonShape : json.get("box2d")) {
                        if (jsonShape.getString("type").equals("circle")) {
                            DrawableCircle circle = new DrawableCircle(0, 0, 0);
                            circle.setCenter(jsonShape.getFloat("x"), jsonShape.getFloat("y"));
                            circle.setRadius(jsonShape.getFloat("radius"));
                            avatar.physicsShapes.add(circle);
                        } else if (jsonShape.getString("type").equals("polygon")) {
                            DrawablePolygon polygon = new DrawablePolygon();
                            polygon.setVertices(jsonShape.get("vertices").asFloatArray());
                            avatar.physicsShapes.add(polygon);
                        }
                    }
                }

                avatars.add(avatar);
            }
        }

        Avatar avatar;
        if (avatars.size() > 0)
            avatar = avatars.get(0);
        else
            return null;

        if (loadAnim && json.has("animation")) {
            JsonValue jsonAnimation = json.get("animation");
            PostureCollection postureCollection = PostureCollection.fromJson(jsonAnimation, avatar.getPartsName());
            avatar.postures = postureCollection;
            if (postureCollection.size() > 0) {
                avatar.loadPosture(0);
            }
        }
        return avatar;
    }

    public void saveFile(String filename) {
        JsonValue json = new JsonValue(JsonValue.ValueType.object);

        json.addChild("library version", new JsonValue(PRenderer.version()));
        json.addChild("format version", new JsonValue("1.0"));

        if (postures != null)
            json.addChild("animation", postures.toJson(getPartsName()));

        // Box2D shapes
        JsonValue jsonPhysicsShapes = new JsonValue(JsonValue.ValueType.array);
        for (Shape shape : physicsShapes) {
            JsonValue jsonShape = new JsonValue(JsonValue.ValueType.object);
            if (shape.getClass() == DrawablePolygon.class) {
                jsonShape.addChild("type", new JsonValue("polygon"));
                JsonValue jsonVertices = new JsonValue(JsonValue.ValueType.array);
                float[] vertices = ((DrawablePolygon) shape).getVertices();
                for (float vert : vertices)
                    jsonVertices.addChild(new JsonValue(vert));
                jsonShape.addChild("vertices", jsonVertices);
            } else if (shape.getClass() == DrawableCircle.class) {
                DrawableCircle circle = (DrawableCircle) shape;
                jsonShape.addChild("type", new JsonValue("circle"));
                jsonShape.addChild("x", new JsonValue(circle.getCenter().x));
                jsonShape.addChild("y", new JsonValue(circle.getCenter().y));
                jsonShape.addChild("radius", new JsonValue(circle.getRadius()));
            }
            jsonPhysicsShapes.addChild(jsonShape);
        }
        json.addChild("box2d", jsonPhysicsShapes);
        json.addChild("geometry", shape.toJson(false));

        try {
            FileWriter writer = new FileWriter(filename);
            writer.write(json.prettyPrint(JsonWriter.OutputType.json, 80));
            writer.close();
            System.out.println("Avatar data saved to " + filename);
        } catch (IOException e) {
            System.out.println("An error occurred while writing to " + filename);
            e.printStackTrace();
        }
    }

    public void saveFile_v2(String filename) {
        JsonValue json = new JsonValue(JsonValue.ValueType.object);
        json.addChild("lib_ver", new JsonValue(PRenderer.version()));
        json.addChild("fmt_ver", new JsonValue("2.0"));

        if (postures != null) {
            json.addChild("animation", postures.toJson(getPartsName()));
        }

        // Box2D shapes
        JsonValue jsonPhysicsShapes = new JsonValue(JsonValue.ValueType.array);
        for (Shape shape : physicsShapes) {
            JsonValue jsonShape = new JsonValue(JsonValue.ValueType.object);
            if (shape.getClass() == DrawablePolygon.class) {
                jsonShape.addChild("type", new JsonValue("polygon"));
                JsonValue jsonVertices = new JsonValue(JsonValue.ValueType.array);
                float[] vertices = ((DrawablePolygon) shape).getVertices();
                for (float vert : vertices)
                    jsonVertices.addChild(new JsonValue(vert));
                jsonShape.addChild("vertices", jsonVertices);
            } else if (shape.getClass() == DrawableCircle.class) {
                DrawableCircle circle = (DrawableCircle) shape;
                jsonShape.addChild("type", new JsonValue("circle"));
                jsonShape.addChild("x", new JsonValue(circle.getCenter().x));
                jsonShape.addChild("y", new JsonValue(circle.getCenter().y));
                jsonShape.addChild("radius", new JsonValue(circle.getRadius()));
            }
            jsonPhysicsShapes.addChild(jsonShape);
        }

        JsonValue jsonAvatar = new JsonValue(JsonValue.ValueType.object);
        jsonAvatar.addChild("box2d", jsonPhysicsShapes);
        jsonAvatar.addChild("geometry", shape.toJson(false));

        JsonValue jsonAvatars = new JsonValue(JsonValue.ValueType.array);
        jsonAvatars.addChild(jsonAvatar);

        json.addChild("avatars", jsonAvatars);

        try {
            FileWriter writer = new FileWriter(filename);
            writer.write(json.prettyPrint(JsonWriter.OutputType.json, 80));
            writer.close();
            System.out.println("Avatar data saved to " + filename);
        } catch (IOException e) {
            System.out.println("An error occurred while writing to " + filename);
            e.printStackTrace();
        }
    }
}
