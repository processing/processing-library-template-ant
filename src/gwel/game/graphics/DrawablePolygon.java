package gwel.game.graphics;


import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Affine2;
import com.badlogic.gdx.math.EarClippingTriangulator;
import com.badlogic.gdx.math.Vector2;
import gwel.game.utils.BoundingBox;


public class DrawablePolygon implements Shape {
    protected float[] vertices;
    protected short[] indices;
    protected final Color color;

    public DrawablePolygon() {
        color = new Color(1, 1, 1, 1);
        vertices = new float[0];
        indices = new short[0];
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

    public BoundingBox getBoundingBox() {
        BoundingBox bb = new BoundingBox();
        for (int i = 0; i < vertices.length; i += 2) {
            float x = vertices[i];
            float y = vertices[i + 1];
            bb.include(new Vector2(x, y));
        }
        return bb;
    }

    public BoundingBox getBoundingBox(Affine2 transform) {
        BoundingBox bb = new BoundingBox();
        for (int i = 0; i < vertices.length; i += 2) {
            Vector2 p = new Vector2(vertices[i], vertices[i+1]);
            transform.applyTo(p);
            bb.include(p);
        }
        return bb;
    }

    @Override
    public boolean contains(Vector2 point) {
        return contains(point.x, point.y);
    }

    /**
     * From package com.badlogic.gdx.math.Polygon;
     */
    @Override
    public boolean contains(float x, float y) {
        int numFloats = vertices.length;
        int intersects = 0;

        for(int i = 0; i < numFloats; i += 2) {
            float x1 = vertices[i];
            float y1 = vertices[i + 1];
            float x2 = vertices[(i + 2) % numFloats];
            float y2 = vertices[(i + 3) % numFloats];
            if ((y1 <= y && y < y2 || y2 <= y && y < y1) && x < (x2 - x1) / (y2 - y1) * (y - y1) + x1) {
                ++intersects;
            }
        }

        return (intersects & 1) == 1;
    }


    /*
        setColor expects values in the range [0, 1]
     */
    public void setColor(float r, float g, float b, float a) {
        color.set(r, g, b, a);
    }

    public void setColor(Color c) {
        color.set(c);
    }

    public Color getColor() { return color.cpy(); }


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
        polygon.setColor(color);
        return polygon;
    }


    public void draw(Renderer renderer) {
        renderer.setColor(color);
        renderer.triangles(vertices, indices);
    }

    // Delete if outside SgAnimator
    public void drawSelected(PRenderer renderer) {
        renderer.setColor(PRenderer.selectedColor);
        renderer.triangles(vertices);
    }
}