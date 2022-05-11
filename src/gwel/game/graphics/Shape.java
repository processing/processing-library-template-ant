package gwel.game.graphics;

import com.badlogic.gdx.math.Affine2;
import com.badlogic.gdx.math.Vector2;
import gwel.game.utils.BoundingBox;

public interface Shape extends Drawable {
    void hardTransform(Affine2 transform);
    boolean contains(Vector2 p);
    boolean contains(float x, float y);
    Shape copy();
}
