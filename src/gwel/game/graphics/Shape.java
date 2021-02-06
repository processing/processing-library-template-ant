package gwel.game.graphics;

import com.badlogic.gdx.math.Affine2;

public interface Shape extends Drawable {
    void hardTransform(Affine2 transform);
    boolean contains(float x, float y);
    Shape copy();
}
