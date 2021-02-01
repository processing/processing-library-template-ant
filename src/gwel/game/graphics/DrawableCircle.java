package gwel.game.graphics;


import com.badlogic.gdx.math.Affine2;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;


public class DrawableCircle extends DrawablePolygon {
    private float radius;
    private int segments;


    public DrawableCircle(float x, float y, float r) { this(x, y, r, 32); }

    public DrawableCircle(float x, float y, float r, int segments) {
        super();
        radius = r;
        this.segments = segments;

        vertices = new float[2*(segments+1)];
        indices = new short[3*segments];
        int vertIdx = 0;
        int indIdx = 0;
        //vertices[vertIdx++] = x;  // Uncomment if outside processing editor
        //vertices[vertIdx++] = y;  // Uncomment if outside processing editor

        float angleStep = 2 * MathUtils.PI / segments;
        float cos = MathUtils.cos(angleStep);
        float sin = MathUtils.sin(angleStep);
        float cx, cy;
        float newX = radius, newY = 0;
        vertices[vertIdx++] = x + cos*radius;   // Remove those if outside processing editor
        vertices[vertIdx++] = y - (sin*radius); // Remove those if outside processing editor
        for (int i=0; i<segments; i++) {
            cx = newX;
            cy = newY;
            vertices[vertIdx++] = x + cx;
            vertices[vertIdx++] = y + cy;
            newX = cos * cx - sin * cy;
            newY = sin * cx + cos * cy;

            indices[indIdx++] = 0;
            indices[indIdx++] = (short) (1 + i % segments);
            indices[indIdx++] = (short) (1 + (i+1) % segments);
        }
    }


    public Vector2 getCenter() {
        return new Vector2(vertices[0], vertices[1]);
    }

    public float getRadius() {
        return radius;
    }

    public int getSegments() { return segments; }


    @Override
    public void hardTransform(Affine2 transform) {
        super.hardTransform(transform);
        radius = radius * transform.m00;
    }
}