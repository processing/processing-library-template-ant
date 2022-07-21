package gwel.game.graphics;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import processing.core.PApplet;
import processing.core.PGraphics;
import processing.core.PImage;
import processing.opengl.PGraphicsOpenGL;

public class PGraphicsRenderer extends Renderer {

    //private final PApplet parent;
    private PGraphics buffer;
    private final Color backgroundColor = new Color(1f, 1f, 1f, 0f);


    public PGraphicsRenderer(PApplet parent) {
        super(parent);
    }


    public void setBackgroundColor(float r, float g, float b, float a) { backgroundColor.set(r, g, b, a); }

    public int getBackgroundColor() { return Color.argb8888(backgroundColor); }

    public void setBufferSize(int width, int height) {
        buffer = parent.createGraphics(width, height);
        buffer.smooth();
    }

    public void beginDraw() {
        buffer.beginDraw();
        buffer.noStroke();
    }

    public void endDraw() { buffer.endDraw();}

    public void clear() {
        buffer.background(backgroundColor.r*255f, backgroundColor.g*255f, backgroundColor.b*255f, backgroundColor.a*255f);
    }

    public PImage getBuffer() { return buffer.get(); }

    public void triangles(float[] vertices) {
        Vector2 point = new Vector2();

        //buffer.noStroke();
        buffer.fill(Color.argb8888(color));
        buffer.beginShape();
        for (int i = 0; i < vertices.length; i += 2) {
            point.set(vertices[i], vertices[i+1]);
            getTransform().applyTo(point);
            buffer.vertex(point.x, point.y);
        }
        buffer.endShape(parent.CLOSE);
    }


    public void triangles(float[] vertices, short[] indices) {
        triangles(vertices); // Weird. Why is it not using indices ?

        /*
        buffer.beginShape(parent.TRIANGLES);
        int idx;
        Vector2 point = new Vector2();
        for (int i = 0; i < indices.length; ) {
            idx = indices[i++]<<1;
            point.set(vertices[idx], vertices[idx+1]);
            matrixStack.transform.applyTo(point);
            buffer.vertex(point.x, point.y);

            idx = indices[i++]<<1;
            point.set(vertices[idx], vertices[idx+1]);
            matrixStack.transform.applyTo(point);
            buffer.vertex(point.x, point.y);

            idx = indices[i++]<<1;
            point.set(vertices[idx], vertices[idx+1]);
            matrixStack.transform.applyTo(point);
            buffer.vertex(point.x, point.y);
        }
        buffer.endShape();
         */
    }
}
