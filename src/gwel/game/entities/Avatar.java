package gwel.game.entities;

import com.badlogic.gdx.math.Vector2;
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
    private ComplexShape rootShape;
    private Vector2 position;
    private HashMap<String, Animation[][]> animations;  // Should have better perfs than animationCollection
    private ComplexShape[] partsList;


    public Avatar() {
        position = new Vector2();
    }


    public void setShape(ComplexShape root) {
        rootShape = root;
        partsList = root.getPartsList().toArray(new ComplexShape[0]);
    }

    public ComplexShape getShape() { return rootShape; }


    public ComplexShape[] getPartsList() { return partsList; }


    public void setAnimationCollection(AnimationCollection collection) {
        ArrayList<String> partsName = new ArrayList<>();
        for (int i=0; i<partsList.length; i++)
            partsName.add(partsList[i].getId());

        animations = new HashMap<>();
        for (String animName : collection.getPosturesNameList()) {
            HashMap<String, Animation[]> fullAnimation = collection.getPosture(animName);
            Animation[][] fullAnimationArray = new Animation[partsList.length][];
            // fullAnimationArray entries are left to 'null' if empty
            Arrays.fill(fullAnimationArray, null);
            for(Map.Entry<String, Animation[]> entry : fullAnimation.entrySet()) {
                int idx = partsName.indexOf(entry.getKey());
                if (idx >= 0)
                    fullAnimationArray[idx] = entry.getValue();
            }
            animations.put(animName, fullAnimationArray);
        }
    }


    public String[] getAnimationsNameList() {
        return (String[]) animations.keySet().toArray();
    }


    // Should be used by animation editor only (slow)
    public void setPosture(HashMap<String, Animation[]> fullAnimation, float duration) {
        Set<String> ids = fullAnimation.keySet();
        for (ComplexShape part : partsList) {
            if (ids.contains(part.getId())) {
                Animation[] anims;
                anims = fullAnimation.get(part.getId());
                part.transitionAnimation(anims, duration);
            } else {
                part.transitionAnimation(new Animation[0], duration);
            }
        }
        /*
        for (Map.Entry<String, Animation[]> entry : fullAnimation.entrySet()) {
            int i=0;
            for (; i<partsList.length; i++) {
                if (partsList[i].getId().equals(entry.getKey())) {
                    partsList[i].transitionAnimation((ArrayList<Animation>) Arrays.asList(entry.getValue()), duration);
                    break;
                }
            }
        }
        */
    }

    public void setPosture(HashMap<String, Animation[]> fullAnimation) {
        setPosture(fullAnimation, 0.2f);
    }


    // Activate a fullAnimation from the collection
    public void setAnimation(String animName) {
        Animation[][] fullAnimation = animations.get(animName);
        int i = 0;
        for (Animation[] animList : fullAnimation) {
            if (animList != null)
                partsList[i].setAnimationList(animList);
            i++;
        }
    }


    // Play every animation from the animationCollection sequencially
    public void playSequencially() {

    }


    public void updateAnimation(float dtime) { rootShape.updateAnimation(dtime); }

    public void resetAnimation() {
        rootShape.resetAnimation();
    }

    public void clearAnimation() {
        for (ComplexShape part : partsList)
            part.clearAnimationList();
    }


    public void draw(MyRenderer renderer) {
        renderer.pushMatrix();
        renderer.translate(position.x, position.y);
        rootShape.draw(renderer);
        renderer.popMatrix();
    }

    public void drawSelectedOnly(MyRenderer renderer) {
        rootShape.drawSelectedOnly(renderer);
    }


    public static Avatar fromFile(File file) {
        return fromFile(file, true, true);
    }

    public static Avatar fromFile(File file, boolean loadGeom, boolean loadAnim) {
        Avatar avatar = new Avatar();
        JsonValue fromJson = null;
        try {
            InputStream in = new FileInputStream(file);
            fromJson = new JsonReader().parse(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (fromJson == null)
            return avatar;

        // Load shape first
        if (loadGeom && fromJson.has("geometry")) {
            JsonValue jsonGeometry = fromJson.get("geometry");
            avatar.setShape(ComplexShape.fromJson(jsonGeometry));
        }

        if (loadAnim && fromJson.has("animation")) {
            JsonValue jsonAnimation = fromJson.get("animation");
            AnimationCollection animationCollection = AnimationCollection.fromJson(jsonAnimation);
            avatar.setAnimationCollection(animationCollection);
            if (animationCollection.size() > 0) {
                avatar.setPosture(animationCollection.getPosture(0));
            }
        }

        return avatar;
    }

    public void saveFile(String filename, AnimationCollection animationCollection) {
        JsonValue json = new JsonValue(JsonValue.ValueType.object);
        json.addChild("geometry", rootShape.toJson(false));
        json.addChild("animation", animationCollection.toJson());

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
