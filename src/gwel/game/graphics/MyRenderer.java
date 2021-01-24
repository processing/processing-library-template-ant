package gwel.game.graphics;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Affine2;
import com.badlogic.gdx.math.Vector2;
import processing.core.PApplet;

import java.util.ArrayDeque;

/**
 * This is a template class and can be used to start a new processing Library.
 * Make sure you rename this class as well as the name of the example package 'template' 
 * to your own Library naming convention.
 * 
 * (the tag example followed by the name of an example included in folder 'examples' will
 * automatically include the example in the javadoc.)
 *
 * @example Hello 
 */

public class MyRenderer {
	// myParent is a reference to the parent sketch
	PApplet myParent;
	Color color;
	public final ArrayDeque<Affine2> matrixStack;
	private Affine2 transform;
	public final static String VERSION = "##library.prettyVersion##";
	private ComplexShape selected;
	static public final Color selectedColor = new Color(0.0f, 1.0f, 0.0f, 0.6f);
	private boolean wireframe = false;


	public MyRenderer(PApplet theParent) {
		myParent = theParent;
		color = new Color();
		transform = new Affine2();
		matrixStack = new ArrayDeque<>();
		matrixStack.push(new Affine2());

		System.out.println("spaceGame Renderer initiated...");
	}


	public void setColor(float r, float g, float b, float a) {
		this.color.set(r, g, b, a);
	}

	public void setColor(Color color) {
		this.color.set(color);
	}


	public void translate(float x, float y) {
		transform.translate(x, y);
	}


	public void scale(float s) {
		transform.scale(s, s);
	}


	public void pushMatrix() {
		matrixStack.push(transform);
		transform = new Affine2();
		transform.preMul(matrixStack.getFirst());
	}

	public void pushMatrix(Affine2 matrix) {
		matrixStack.push(transform);
		transform = new Affine2(matrix);
		transform.preMul(matrixStack.getFirst());
	}


	public void popMatrix() {
		transform = matrixStack.pop();
	}


	public Affine2 getTransform() {
		return new Affine2(transform);
	}


	// Used for the animation editor
	public void setSelected(ComplexShape selected) {
		this.selected = selected;
	}

	public ComplexShape getSelected() {
		return selected;
	}


	public void triangles(float[] vertices) {
		Vector2 point = new Vector2();

		myParent.noStroke();
		myParent.fill(Color.argb8888(color));

		myParent.beginShape();
		for (int i=0; i<vertices.length; i+=2) {
			point.set(vertices[i], vertices[i + 1]);
			transform.applyTo(point);
			myParent.vertex(point.x, point.y);
		}
		myParent.endShape(myParent.CLOSE);
	}

	public void triangles(float[] vertices, short[] indices) {
		if (wireframe) {
			myParent.stroke(Color.argb8888(color));
			myParent.noFill();
		} else {
			triangles(vertices);
			return;
		}

		myParent.beginShape(myParent.TRIANGLES);
		int idx;
		Vector2 point = new Vector2();
		for (int i=0; i<indices.length; ) {
			idx = indices[i++]<<1;
			point.set(vertices[idx], vertices[idx+1]);
			transform.applyTo(point);
			myParent.vertex(point.x, point.y);

			idx = indices[i++]<<1;
			point.set(vertices[idx], vertices[idx+1]);
			transform.applyTo(point);
			myParent.vertex(point.x, point.y);

			idx = indices[i++]<<1;
			point.set(vertices[idx], vertices[idx+1]);
			transform.applyTo(point);
			myParent.vertex(point.x, point.y);
		}
		myParent.endShape();
	}


	// Used for compatibility with processing only
	public void circle(float x, float y, float r) {
		Vector2 point = new Vector2(x, y);
		Vector2 radiusPoint = new Vector2(x+r, y);

		if (wireframe) {
			myParent.stroke(Color.argb8888(color));
			myParent.noFill();
		} else {
			myParent.noStroke();
			myParent.fill(Color.argb8888(color));
		}

		transform.applyTo(point);
		transform.applyTo(radiusPoint);
		r = point.dst(radiusPoint);
		myParent.circle(point.x, point.y, 2*r);
	}


	// Used for the animation editor
	public void drawMarker(float x, float y) {
		Vector2 point = new Vector2(x, y);
		transform.applyTo(point);
		myParent.strokeWeight(1);
		myParent.stroke(0, 0, 255);
		myParent.line(point.x-6, point.y, point.x+6, point.y);
		myParent.line(point.x, point.y-6, point.x, point.y+6);
		myParent.textSize(10);
		myParent.fill(0, 0, 255);
		myParent.text(x + ", " + y, point.x+4, point.y-2);
	}


	// Used for the animation editor
	public void drawPivot() {
		if (selected != null) {
			Vector2 point = selected.getLocalOrigin();
			selected.getAbsoluteTransform().applyTo(point);
			transform.applyTo(point);

			myParent.noStroke();
			myParent.fill(0, 0, 255);
			myParent.circle(point.x, point.y, 8);
		}
	}


	// Used for the animation editor
	public void drawAxes() {
		for (int i=1; i<=10; i++) {
			Vector2 point = new Vector2(0f, -10*i);
			transform.applyTo(point);
			myParent.stroke(0, 127);
			if (i%10 == 0) {
				myParent.strokeWeight(2);
				myParent.line(point.x-8, point.y, point.x+8, point.y);
			} else {
				myParent.strokeWeight(1);
				myParent.line(point.x-4, point.y, point.x+4, point.y);
			}
		}
	}


	// Used for the animation editor
	public void toggleWireframe() { wireframe = !wireframe; }


	/**
	 * return the version of the Library.
	 *
	 * @return String
	 */
	public static String version() {
		return VERSION;
	}
}

