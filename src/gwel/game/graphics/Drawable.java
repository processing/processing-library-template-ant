package gwel.game.graphics;


import com.badlogic.gdx.graphics.Color;
import gwel.game.utils.MatrixStack;


public interface Drawable {
    void draw(Renderer renderer);
    void drawSelected(PRenderer renderer);
}
