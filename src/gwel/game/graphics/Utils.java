package gwel.game.graphics;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;

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

    /**
     * Converts an array of Vector2 to an array 2D vertices (pair of floats)
     *
     */
    public static float[] verticesArray(Vector2[] vectorArray) {
        float[] vertices = new float[vectorArray.length * 2];
        for (int i = 0; i < vectorArray.length; i++) {
            vertices[2*i] = vectorArray[i].x;
            vertices[2*i + 1] = vectorArray[i].y;
        }

        return vertices;
    }
}
