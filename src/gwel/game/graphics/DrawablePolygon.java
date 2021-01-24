package gwel.game.graphics;


import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Affine2;
import com.badlogic.gdx.math.EarClippingTriangulator;
import com.badlogic.gdx.math.Vector2;


public class DrawablePolygon implements Drawable {
    protected float[] vertices;
    protected short[] indices;
    protected final Color colorOrig, colorMod;


    public DrawablePolygon() {
        colorOrig = new Color(1, 1, 1, 1);
        colorMod = new Color(1, 1, 1, 1);
    }

    public DrawablePolygon(float[] vertices, short[] indices) {
        this();
        this.vertices = vertices;
        this.indices = indices;
    }

    public DrawablePolygon(float[] vertices) {
        this();
        EarClippingTriangulator triangulator = new EarClippingTriangulator();
        this.vertices = vertices;
        indices = triangulator.computeTriangles(vertices).toArray();
    }


    /*
        setColor expects values in the range [0, 1]
     */
    public void setColor(float r, float g, float b, float a) {
        colorOrig.set(r, g, b, a);
        colorMod.set(r, g, b, a);
    }

    public void setColor(Color c) {
        colorOrig.set(c);
        colorMod.set(c);
    }

    public Color getColor() { return colorOrig; }

    public void setColorMod(float rm, float gm, float bm, float am) {
        colorMod.set(colorOrig);
        colorMod.mul(rm, gm, bm, am);
    }


    @Override
    public void hardTransform(Affine2 transform) {
        float[] newVerts = new float[vertices.length];
        for (int i=0; i<vertices.length/2; i++) {
            Vector2 point = new Vector2(vertices[2*i], vertices[2*i+1]);
            transform.applyTo(point);
            newVerts[2*i] = point.x;
            newVerts[2*i+1] = point.y;
        }
        vertices = newVerts;
    }


    public float[] getVertices() {
        return vertices;
    }

    public void setVertices(float[] verts) {
        vertices = verts;
    }


    public short[] getIndices() {
        return indices;
    }

    public void setIndices(short[] tris) {
        indices = tris;
    }


    public DrawablePolygon copy() {
        DrawablePolygon polygon = new DrawablePolygon(vertices.clone(), indices.clone());
        polygon.setColor(colorOrig.cpy());
        return polygon;
    }


    public void draw(MyRenderer renderer) {
        renderer.setColor(colorMod);
        renderer.triangles(vertices, indices);
    }

    public void drawSelected(MyRenderer renderer) {
        renderer.setColor(MyRenderer.selectedColor);
        renderer.triangles(vertices);
    }
}