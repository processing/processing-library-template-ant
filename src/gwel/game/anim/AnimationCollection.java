package gwel.game.anim;

import com.badlogic.gdx.utils.JsonValue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Class used to edit animations in SgAnimator (Animation tool for Processing)
 *
 */
public class AnimationCollection {
    private ArrayList<String> postureNames;
    private ArrayList<HashMap<String, Animation[]>> postures;

    public AnimationCollection() {
        postureNames = new ArrayList<>();
        postures = new ArrayList<>();
    }


    public int size() {
        return postures.size();
    }


    public void addPosture(String postureName, HashMap<String, Animation[]> posture) {
        postureNames.add(postureName);
        postures.add(posture);
    }


    public void updatePosture(int idx, String postureName, HashMap<String, Animation[]> posture) {
        postureNames.set(idx, postureName);
        postures.set(idx, posture);
    }


    public HashMap<String, Animation[]> getPosture(int idx) {
        return postures.get(idx);
    }


    public HashMap<String, Animation[]> getPosture(String animName) {
        int idx = postureNames.indexOf(animName);
        return getPosture(idx);
    }


    public ArrayList<String> getPostureNames() {
        return postureNames;
    }

    public String getPostureName(int idx) { return postureNames.get(idx); }


    public static AnimationCollection fromJson(JsonValue jsonPostureArray) {
        AnimationCollection animCollection = new AnimationCollection();

        for (JsonValue jsonPosture : jsonPostureArray.iterator()) {
            HashMap<String, Animation[]> posture = new HashMap<>();
            String animName = jsonPosture.getString("name");
            JsonValue groups = jsonPosture.get("groups");
            for (JsonValue group : groups.iterator()) {
                ArrayList<Animation> animationList = new ArrayList<>();
                String id = group.getString("id", "none");
                JsonValue functions = group.get("functions");
                for (JsonValue jsonAnimation : functions.iterator()) {
                    animationList.add(Animation.fromJson(jsonAnimation));
                }
                posture.put(id, animationList.toArray(new Animation[0]));
            }
            animCollection.addPosture(animName, posture);
        }
        return animCollection;
    }

    public JsonValue toJson() {
        JsonValue jsonAnimCollection = new JsonValue(JsonValue.ValueType.array);
        for (String postureName : postureNames) {
            JsonValue groups = new JsonValue(JsonValue.ValueType.array);
            HashMap<String, Animation[]> posture = getPosture(postureName);
            // Every part in a posture
            for (Map.Entry<String, Animation[]> entry : posture.entrySet()) {
                JsonValue group = new JsonValue(JsonValue.ValueType.object);
                JsonValue animationArray = new JsonValue(JsonValue.ValueType.array);
                // Animations linked to a single part
                for (Animation anim : entry.getValue())
                    animationArray.addChild(anim.toJson());
                group.addChild("id", new JsonValue(entry.getKey()));
                group.addChild("functions", animationArray);
                groups.addChild(group);
            }
            JsonValue jsonPosture = new JsonValue(JsonValue.ValueType.object);
            jsonPosture.addChild("name", new JsonValue(postureName));
            jsonPosture.addChild("groups", groups);
            jsonAnimCollection.addChild(jsonPosture);
        }
        return jsonAnimCollection;
    }
}