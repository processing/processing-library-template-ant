package gwel.game.utils;

import com.badlogic.gdx.math.Vector2;

public class BoundingBox {
    public float top, bottom, left, right;
    private boolean firstPoint;

    public BoundingBox() {
        reset();
    }

    public void reset() {
        top = 0;
        bottom = 0;
        left = 0;
        right = 0;
        firstPoint = true;
    }

    public Vector2 getCenter() {
        return new Vector2((left + right) * 0.5f, (top + bottom) * 0.5f);
    }

    public Vector2 getDimensions() {
        return new Vector2(right - left, bottom - top);
    }

    public void include(Vector2 point) {
        if (firstPoint) {
            top = point.y;
            bottom = point.y;
            left = point.x;
            right = point.x;
            firstPoint = false;
            return;
        }

        if (point.y < top)
            top = point.y;
        else if (point.y > bottom)
            bottom = point.y;
        if (point.x < left)
            left = point.x;
        else if (point.x > right)
            right = point.x;
    }

    public void include(BoundingBox bb) {
        if (firstPoint) {
            top = bb.top;
            bottom = bb.bottom;
            left = bb.left;
            right = bb.right;
            firstPoint = false;
            return;
        }

        if (bb.top < top)
            top = bb.top;
        if (bb.bottom > bottom)
            bottom = bb.bottom;
        if (bb.left < left)
            left = bb.left;
        if (bb.right > right)
            right = bb.right;
    }

    public boolean contains(Vector2 position) {
        return contains(position.x, position.y);
    }

    public boolean contains(float x, float y) {
        return x > left && x < right && y > top && y < bottom;
    }

    public String toString() {
        String s = String.format(" [NW:%.1f %.1f SE:%.1f %.1f]", left, top, right, bottom);
        return "BoundingBox@" + Integer.toHexString(hashCode()) + s;
    }
}

