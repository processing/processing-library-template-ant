package gwel.game.anim;

import com.badlogic.gdx.math.Affine2;
import com.badlogic.gdx.math.Vector2;

public class Posture {
    public Animation[][] groups; // List of animation for every part in a ComplexShape
    public String name;
    public float duration;
    public Vector2[] pivot;

    /*
    private final Affine2 transform, oldTransform, nextTransform;

    public Posture() {
        transform = new Affine2();
        oldTransform = new Affine2();
        nextTransform = new Affine2();
    }

    public void update(float dtime) {

    }
     */
}
