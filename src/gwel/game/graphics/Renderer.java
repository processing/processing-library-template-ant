package gwel.game.graphics;


import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Affine2;
import gwel.game.utils.MatrixStack;
import processing.core.PApplet;

import java.util.ArrayDeque;


public abstract class Renderer {
    // parent is a reference to the parent sketch
    protected final PApplet parent;
    private final MatrixStack matrixStack = new MatrixStack();
    public final Color color = new Color();
    public final ArrayDeque<float[]> colorStack = new ArrayDeque<>();


    protected Renderer(PApplet parent) {
        this.parent = parent;
        colorStack.push(new float[] {0f, 0f, 0f, 1f});
    }


    public void setColor(Color color) {
        setColor(color.r, color.g, color.b, color.a);
    }

    public void setColor(float r, float g, float b, float a) {
        color.set(r, g, b, a);
        float[] colorMod = colorStack.getFirst();
        color.r += colorMod[0];
        color.g += colorMod[1];
        color.b += colorMod[2];
        color.a *= colorMod[3];
        color.clamp();
    }


    public void pushColorMod(float[] colorMod) {
        float[] color = new float[4];
        System.arraycopy(colorStack.getFirst(), 0, color, 0, 4);
        color[0] += colorMod[0];
        color[1] += colorMod[1];
        color[2] += colorMod[2];
        color[3] *= colorMod[3];
        colorStack.push(color);
    }

    public void popColorMod() { colorStack.pop(); }



    public void translate(float x, float y) {
        matrixStack.transform.translate(x, y);
    }


    public void scale(float s) {
        matrixStack.transform.scale(s, s);
    }

    public void scale(float sx, float sy) { matrixStack.transform.scale(sx, sy); }


    public void pushMatrix() { matrixStack.push(); }

    public void pushMatrix(Affine2 transform) { matrixStack.push(transform); }

    public void popMatrix() { matrixStack.pop(); }

    public Affine2 getTransform() {
        return new Affine2(matrixStack.transform);
    }

    public void triangles(float[] vertices) {}

    public void triangles(float[] vertices, short[] indices) {}
}
