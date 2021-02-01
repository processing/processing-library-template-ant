package gwel.game.entities;

import com.badlogic.gdx.math.*;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.JsonWriter;
import gwel.game.anim.Animation;
import gwel.game.anim.AnimationCollection;
import gwel.game.graphics.ComplexShape;
import gwel.game.graphics.MyRenderer;

import java.io.*;
import java.util.*;


public class Avatar {
    public ComplexShape shape;
    private final Vector2 position = new Vector2();
    private final Affine2 transform = new Affine2();
    private HashMap<String, Animation[][]> animations;  // Should have better perfs than animationCollection
    private String[] postureNames;
    private ComplexShape[] partsList;
    public ArrayList<Shape2D> physicsShapes;
    private float timeFactor = 1f;


    public Avatar() {
        physicsShapes = new ArrayList<>();
    }


    public void setPosition(float x, float y) { position.set(x, y); }

    public void scale(float s) {
        transform.scale(s, s);
    }

    public void scale(float sx, float sy) { transform.scale(sx, sy); }

    public void timeScale(float s) { timeFactor = s; }


    public void setShape(ComplexShape root) {
        shape = root;
        partsList = root.getPartsList().toArray(new ComplexShape[0]);
    }

    public ComplexShape getShape() { return shape; }


    public ComplexShape[] getPartsList() { return partsList; }


    public void setAnimationCollection(AnimationCollection collection) {
        ArrayList<String> partsName = new ArrayList<>();
        for (ComplexShape partShape : partsList) partsName.add(partShape.getId());

        postureNames = collection.getPostureNames().toArray(new String[0]);
        animations = new HashMap<>();
        for (String postureName : postureNames) {
            HashMap<String, Animation[]> posture = collection.getPosture(postureName);
            Animation[][] postureArray = new Animation[partsList.length][];
            // postureArray entries are left to 'null' if empty
            Arrays.fill(postureArray, null);
            for(Map.Entry<String, Animation[]> entry : posture.entrySet()) {
                int idx = partsName.indexOf(entry.getKey());
                if (idx >= 0)
                    postureArray[idx] = entry.getValue();
            }
            animations.put(postureName, postureArray);
        }
    }

    /**
     * Rebuild an AnimationCollection from the animations array
     *
     * @return AnimationCollection
     */
    public AnimationCollection getAnimationCollection() {
        AnimationCollection animationCollection = new AnimationCollection();
        ArrayList<String> partsName = new ArrayList<>();
        for (ComplexShape partShape : partsList) partsName.add(partShape.getId());

        for (String postureName : postureNames) {
            HashMap<String, Animation[]> posture = new HashMap<>();
            for (String partName : partsName) {
                int partIdx = partsName.indexOf(partName);
                Animation[] partAnims = animations.get(postureName)[partIdx];
                if (partAnims != null)
                    posture.put(partName, partAnims);
            }
            animationCollection.addPosture(postureName, posture);
        }
        /*
        for(Map.Entry<String, Animation[][]> entry : animations.entrySet()) {
            String postureName = entry.getKey();
            HashMap<String, Animation[]> posture = new HashMap<>();
            for (String partName : partsName) {
                int partIdx = partsName.indexOf(partName);
                posture.put(partName, entry.getValue()[partIdx]);
            }
            animationCollection.addPosture(postureName, posture);
        }*/

        return animationCollection;
    }


    public String[] getPostureNames() {
        return postureNames;
    }


    // Should be used by animation editor only (slow)
    public void setPosture(HashMap<String, Animation[]> posture, float transition) {
        Set<String> ids = posture.keySet();
        for (ComplexShape part : partsList) {
            if (ids.contains(part.getId())) {
                Animation[] partAnims;
                partAnims = posture.get(part.getId());
                part.transitionAnimation(partAnims, transition);
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
        loadPosture(postureNames[idx]);
    }

    // Activate a posture from the collection
    public void loadPosture(String postureName) {
        System.out.println(postureName + " loaded");
        Animation[][] posture = animations.get(postureName);
        int i = 0;
        for (Animation[] animList : posture) {
            if (animList != null)
                partsList[i].setAnimationList(animList);
            i++;
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


    // Delete if outside Processing editor
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
            AnimationCollection animationCollection = AnimationCollection.fromJson(jsonAnimation);
            avatar.setAnimationCollection(animationCollection);
            if (animationCollection.size() > 0) {
                avatar.loadPosture(0);
            }
        }

        if (fromJson.has("box2d")) {
            for (JsonValue jsonShape : fromJson.get("box2d")) {
                if (jsonShape.getString("type").equals("circle")) {
                    Circle circle = new Circle();
                    circle.setPosition(jsonShape.getFloat("x"), jsonShape.getFloat("y"));
                    circle.setRadius(jsonShape.getFloat("radius"));
                    avatar.physicsShapes.add(circle);
                } else if (jsonShape.getString("type").equals("polygon")) {
                    Polygon polygon = new Polygon();
                    polygon.setVertices(jsonShape.get("vertices").asFloatArray());
                    avatar.physicsShapes.add(polygon);
                }
            }
        }

        return avatar;
    }

    public void saveFile(String filename) {
        JsonValue json = new JsonValue(JsonValue.ValueType.object);

        json.addChild("animation", getAnimationCollection().toJson());

        JsonValue jsonPhysicsShapes = new JsonValue(JsonValue.ValueType.array);
        for (Shape2D shape : physicsShapes) {
            JsonValue jsonShape = new JsonValue(JsonValue.ValueType.object);
            if (shape.getClass() == Polygon.class) {
                jsonShape.addChild("type", new JsonValue("polygon"));
                JsonValue jsonVertices = new JsonValue(JsonValue.ValueType.array);
                float[] vertices = ((Polygon) shape).getTransformedVertices();
                for (float vert : vertices)
                    jsonVertices.addChild(new JsonValue(vert));
                jsonShape.addChild("vertices", jsonVertices);
            } else if (shape.getClass() == Circle.class) {
                Circle circle = (Circle) shape;
                jsonShape.addChild("type", new JsonValue("circle"));
                jsonShape.addChild("x", new JsonValue(circle.x));
                jsonShape.addChild("y", new JsonValue(circle.y));
                jsonShape.addChild("radius", new JsonValue(circle.radius));
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

    /*
    public void saveFile(String filename, AnimationCollection animationCollection) {
        JsonValue json = new JsonValue(JsonValue.ValueType.object);

        json.addChild("animation", animationCollection.toJson());

        JsonValue jsonPhysicsShapes = new JsonValue(JsonValue.ValueType.array);
        for (Shape2D shape : physicsShapes) {
            JsonValue jsonShape = new JsonValue(JsonValue.ValueType.object);
            if (shape.getClass() == Polygon.class) {
                jsonShape.addChild("type", new JsonValue("polygon"));
                JsonValue jsonVertices = new JsonValue(JsonValue.ValueType.array);
                float[] vertices = ((Polygon) shape).getTransformedVertices();
                for (float vert : vertices)
                    jsonVertices.addChild(new JsonValue(vert));
                jsonShape.addChild("vertices", jsonVertices);
            } else if (shape.getClass() == Circle.class) {
                Circle circle = (Circle) shape;
                jsonShape.addChild("type", new JsonValue("circle"));
                jsonShape.addChild("x", new JsonValue(circle.x));
                jsonShape.addChild("y", new JsonValue(circle.y));
                jsonShape.addChild("radius", new JsonValue(circle.radius));
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
     */
}
