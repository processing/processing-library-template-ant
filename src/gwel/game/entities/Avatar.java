package gwel.game.entities;

import com.badlogic.gdx.math.*;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.JsonWriter;
import gwel.game.anim.Animation;
import gwel.game.anim.PostureCollection;
import gwel.game.anim.Posture;
import gwel.game.graphics.*;

import java.io.*;
import java.util.*;


public class Avatar {
    public ComplexShape shape;
    private final Vector2 position = new Vector2();
    private final Affine2 transform = new Affine2();
    public PostureCollection postures;
    //private HashMap<String, Animation[][]> animations;  // Should have better perfs than animationCollection
    //private String[] postureNames;
    private ComplexShape[] partsList;
    public ArrayList<Shape> physicsShapes;
    private float timeFactor = 1f;
    private Posture currentPosture;


    public Avatar() {
        physicsShapes = new ArrayList<>();
    }


    public void setPosition(float x, float y) { position.set(x, y); }


    public void scale(float s) {
        scale(s, s);
    }

    public void scale(float sx, float sy) { transform.scale(sx, sy); }

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
    public String[] getPartsNamePre() {
        return shape.getIdListPre("").toArray(new String[0]);
    }


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
    public void setPosture(HashMap<String, Animation[]> fullAnimation) {
        setPosture(fullAnimation, 0.2f);
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
            if (animList != null) partsList[i].setAnimationList(animList);
        }
    }


    // Play every animation from the animationCollection sequencially
    public void playSequencially() {

    }


    public void updateAnimation(float dtime) { shape.update(timeFactor * dtime); }

    public void resetAnimation() {
        shape.reset();
    }

    public void clearAnimation() {
        for (ComplexShape part : partsList)
            part.clearAnimationList();
    }


    public void draw(MyRenderer renderer) {
        renderer.pushMatrix();
        renderer.translate(position.x, position.y);
        renderer.pushMatrix(transform);
        shape.draw(renderer);
        renderer.popMatrix();
        renderer.popMatrix();
    }


    /**
     * Delete if outside Processing editor
     */
    public void drawSelectedOnly(MyRenderer renderer) {
        shape.drawSelectedOnly(renderer);
    }


    public static Avatar fromFile(File file) {
        return fromFile(file, true);
    }

    public static Avatar fromFile(File file, boolean loadAnim) {
        JsonValue fromJson;
        try {
            InputStream in = new FileInputStream(file);
            fromJson = new JsonReader().parse(in);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        Avatar avatar = new Avatar();

        // Load shape first
        if (fromJson.has("geometry")) {
            JsonValue jsonGeometry = fromJson.get("geometry");
            avatar.setShape(ComplexShape.fromJson(jsonGeometry));
        } else {
            System.out.println("No geometry data found !");
        }

        if (loadAnim && fromJson.has("animation")) {
            JsonValue jsonAnimation = fromJson.get("animation");
            PostureCollection animationCollection = PostureCollection.fromJson(jsonAnimation, avatar.getPartsName());
            avatar.postures = animationCollection;
            if (animationCollection.size() > 0) {
                avatar.loadPosture(0);
            }
        }

        if (fromJson.has("box2d")) {
            for (JsonValue jsonShape : fromJson.get("box2d")) {
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


    public void saveFile(String filename) {
        JsonValue json = new JsonValue(JsonValue.ValueType.object);

        json.addChild("animation", postures.toJson(getPartsName()));

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
}
