package gwel.game.graphics;


import com.badlogic.gdx.math.Affine2;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import gwel.game.utils.BoundingBox;


public class DrawableCircle extends DrawablePolygon {
    private float radius;
    private int segments;
    private boolean dirty;
    private final Vector2 center;


    public DrawableCircle(float x, float y, float r) { this(x, y, r, 32); }

    public DrawableCircle(float x, float y, float r, int segments) {
        super();
        center = new Vector2(x, y);
        radius = r;
        this.segments = segments;
        dirty = true;
    }

    private void computeVertices() {
        vertices = new float[2*(segments+1)];
        indices = new short[3*segments];
        int vertIdx = 0;
        int indIdx = 0;
        //vertices[vertIdx++] = center.x;  // Uncomment if outside processing editor
        //vertices[vertIdx++] = center.y;  // Uncomment if outside processing editor

        float angleStep = 2 * MathUtils.PI / segments;
        float cos = MathUtils.cos(angleStep);
        float sin = MathUtils.sin(angleStep);
        float cx, cy;
        float newX = radius, newY = 0;
        vertices[vertIdx++] = center.x + cos*radius;   // Remove those if outside processing editor
        vertices[vertIdx++] = center.y - (sin*radius); // Remove those if outside processing editor
        for (int i=0; i<segments; i++) {
            cx = newX;
            cy = newY;
            vertices[vertIdx++] = center.x + cx;
            vertices[vertIdx++] = center.y + cy;
            newX = cos * cx - sin * cy;
            newY = sin * cx + cos * cy;

            indices[indIdx++] = 0;
            indices[indIdx++] = (short) (1 + i % segments);
            indices[indIdx++] = (short) (1 + (i+1) % segments);
        }

        dirty = false;
    }

    public BoundingBox getBoundingBox() {
        BoundingBox bb = new BoundingBox();
        bb.top = center.y - radius;
        bb.bottom = center.y + radius;
        bb.left = center.x - radius;
        bb.right = center.x + radius;
        return bb;
    }

    @Override
    public boolean contains(float x, float y) {
        return Vector2.dst2(center.x, center.y, x, y) < radius*radius;
    }


    public Vector2 getCenter() {
        return center.cpy();
    }

    public void setCenter(float x, float y) {
        center.set(x, y);
        dirty = true;
    }

    public float getRadius() {
        return radius;
    }

    public void setRadius(float r) {
        this.radius = r;
        dirty = true;
    }

    public int getSegments() { return segments; }

    public void setSegments(int s) {
        segments = s;
        dirty = true;
    }


    @Override
    public float[] getVertices() {
        if (dirty)
            computeVertices();
        return vertices;
    }

    @Override
    public short[] getIndices() {
        if (dirty)
            computeVertices();
        return indices;
    }


    @Override
    public void hardTransform(Affine2 transform) {
        if (dirty)
            computeVertices();
        super.hardTransform(transform);
        transform.applyTo(center);
        radius = radius * transform.m00;
    }

    @Override
    public void draw(Renderer renderer) {
        if (dirty)
            computeVertices();
        super.draw(renderer);
    }
}