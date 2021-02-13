package gwel.game.anim;

import com.badlogic.gdx.utils.JsonValue;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Class used to edit animations in SgAnimator (Animation tool for Processing)
 *
 */
public class PostureCollection {
    private ArrayList<Posture> postures;


    public PostureCollection() {
        postures = new ArrayList<>();
    }


    public int size() {
        return postures.size();
    }


    public void addPosture(Posture posture) {
        postures.add(posture);
    }


    public void updatePosture(int idx, Posture posture) {
        postures.set(idx, posture);
    }


    public Posture getPosture(int idx) {
        return postures.get(idx);
    }

    public Posture getPosture(String postureName) {
        // Not optimal (maybe should use an HashMap for fast lookup)
        for (Posture posture : postures) {
            if (posture.name.equals(postureName))
                return posture;
        }
        return null;
    }


    /*public ArrayList<String> getPostureNames() {
        return postureNames;
    }

    public String getPostureName(int idx) { return postureNames.get(idx); }
*/

    public static PostureCollection fromJson(JsonValue jsonPostureArray, String[] partsName) {
        PostureCollection animCollection = new PostureCollection();

        for (JsonValue jsonPosture : jsonPostureArray.iterator()) {
            Posture posture = new Posture();
            posture.name = jsonPosture.getString("name");
            posture.duration = jsonPosture.getFloat("duration", 0f);
            Animation[][] groups = new Animation[partsName.length][];
            Arrays.fill(groups, null);
            JsonValue jsonGroups = jsonPosture.get("groups");
            for (JsonValue jsonGroup : jsonGroups.iterator()) {
                String id = jsonGroup.getString("id");
                ArrayList<Animation> animationList = new ArrayList<>();
                JsonValue functions = jsonGroup.get("functions");
                for (JsonValue jsonAnimation : functions.iterator())
                    animationList.add(Animation.fromJson(jsonAnimation));
                int idx = -1;
                for (int i = 0; i < partsName.length; i++) {
                    if (partsName[i].equals(id)) {
                        idx = i;
                        break;
                    }
                }
                if (idx >= 0) groups[idx] = animationList.toArray(new Animation[0]);
            }
            posture.groups = groups;
            animCollection.addPosture(posture);
        }
        return animCollection;
    }


    public JsonValue toJson(String[] partsName) {
        JsonValue jsonPostureCollection = new JsonValue(JsonValue.ValueType.array);
        for (Posture posture : postures) {
            JsonValue jsonGroups = new JsonValue(JsonValue.ValueType.array);
            // Every part in a posture
            for (int i = 0; i < partsName.length; i++) {
                if (posture.groups[i] != null) {
                    JsonValue jsonGroup = new JsonValue(JsonValue.ValueType.object);
                    JsonValue animationArray = new JsonValue(JsonValue.ValueType.array);
                    // Animations linked to a single part
                    for (Animation anim : posture.groups[i]) animationArray.addChild(anim.toJson());
                    jsonGroup.addChild("id", new JsonValue(partsName[i]));
                    jsonGroup.addChild("functions", animationArray);
                    jsonGroups.addChild(jsonGroup);
                }
            }
            JsonValue jsonPosture = new JsonValue(JsonValue.ValueType.object);
            jsonPosture.addChild("name", new JsonValue(posture.name));
            jsonPosture.addChild("duration", new JsonValue(posture.duration));
            jsonPosture.addChild("groups", jsonGroups);
            jsonPostureCollection.addChild(jsonPosture);
        }
        return jsonPostureCollection;
    }
}