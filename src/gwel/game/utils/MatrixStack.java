package gwel.game.utils;

import com.badlogic.gdx.math.Affine2;
import java.util.ArrayDeque;


public class MatrixStack {
    private final ArrayDeque<Affine2> stack;
    public Affine2 transform;

    public MatrixStack() {
        stack = new ArrayDeque<>();
        transform = new Affine2();
    }

    public void push() {
        stack.push(transform);
        transform = new Affine2();
        transform.preMul(stack.getFirst());
    }

    public void push(Affine2 matrix) {
        stack.push(transform);
        transform = new Affine2(matrix);
        transform.preMul(stack.getFirst());
    }

    public void pop() {
        transform = stack.pop();
    }
}
