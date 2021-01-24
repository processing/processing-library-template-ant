package gwel.game.graphics;

import com.badlogic.gdx.graphics.Color;

public class Utils {
    private Utils() {}


    // Converts from Processing color format to LibGdx Color class
    public static Color pColorToGDXColor(int rgb) {
        float red = (rgb >> 16) & 0xff;
        float green = (rgb >> 8) & 0xff;
        float blue = (rgb) & 0xff;
        float alpha = (rgb >> 24) & 0xff;
        return new Color(red/255.0f, green/255.0f, blue/255.0f, alpha/255.0f);
    }
}
