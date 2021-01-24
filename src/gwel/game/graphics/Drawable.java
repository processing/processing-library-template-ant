package gwel.game.graphics;


import com.badlogic.gdx.math.Affine2;

public interface Drawable {
    void draw(MyRenderer renderer);
    void drawSelected(MyRenderer renderer);
    void setColorMod(float rm, float gm, float bm, float am);
    void hardTransform(Affine2 transform);
    Drawable copy();
}
