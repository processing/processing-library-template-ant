package gwel.game.graphics;


import com.badlogic.gdx.graphics.Color;


public interface Drawable {
    void draw(MyRenderer renderer);
    void drawSelected(MyRenderer renderer);
}
