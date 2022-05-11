package gwel.game.graphics;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Affine2;
import com.badlogic.gdx.math.Vector2;
import processing.core.PApplet;
import processing.core.PGraphics;

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
	public final static String VERSION = "##library.prettyVersion##";

	// myParent is a reference to the parent sketch
	private final PApplet myParent;
	private PGraphics buffer;
	private Color color;
	public final ArrayDeque<Affine2> matrixStack;
	public final ArrayDeque<float[]> colorStack;
	private Affine2 transform;
	private ComplexShape selected;
	static public final Color selectedColor = new Color(0.0f, 1.0f, 0.0f, 0.6f);
	private boolean wireframe = false;
	private boolean recording = false;
	private Color backgroundColor = new Color(1f, 1f, 1f, 0f);
	private int frameNumber;


	public MyRenderer(PApplet theParent) {
		myParent = theParent;
		color = new Color();
		transform = new Affine2();
		matrixStack = new ArrayDeque<>();
		matrixStack.push(new Affine2());
		colorStack = new ArrayDeque<>();
		colorStack.push(new float[] {0f, 0f, 0f, 1f});

		System.out.println("Game renderer initiated..." + " Version " + VERSION);
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

	public void setColor(Color color) {
		setColor(color.r, color.g, color.b, color.a);
	}


	public void translate(float x, float y) {
		transform.translate(x, y);
	}


	public void scale(float s) {
		transform.scale(s, s);
	}

	public void scale(float sx, float sy) { transform.scale(sx, sy); }


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


	public Affine2 getTransform() {
		return new Affine2(transform);
	}


	/**
	 * Delete if outside SgAnimator
	 */
	public void setSelected(ComplexShape selected) {
		this.selected = selected;
	}

	/**
	 * Delete if outside SgAnimator
	 */
	public ComplexShape getSelected() {
		return selected;
	}


	public void triangles(float[] vertices) {
		Vector2 point = new Vector2();

		if (recording) {
			buffer.noStroke();
			buffer.fill(Color.argb8888(color));
			buffer.beginShape();
		}

		myParent.noStroke();
		myParent.fill(Color.argb8888(color));
		myParent.beginShape();

		for (int i = 0; i < vertices.length; i += 2) {
			point.set(vertices[i], vertices[i+1]);
			transform.applyTo(point);
			myParent.vertex(point.x, point.y);
			if (recording)
				buffer.vertex(point.x, point.y);
		}

		myParent.endShape(myParent.CLOSE);
		if (recording)
			buffer.endShape(myParent.CLOSE);
	}


	public void triangles(float[] vertices, short[] indices) {
		if (wireframe) {
			myParent.stroke(Color.argb8888(color));
			myParent.strokeWeight(1);
			myParent.noFill();
		} else {
			triangles(vertices);
			return;
		}

		myParent.beginShape(myParent.TRIANGLES);
		int idx;
		Vector2 point = new Vector2();
		for (int i = 0; i < indices.length; ) {
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


	/**
	 * Delete if outside SgAnimator
	 */
	/*
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
	}*/


	/**
	 * Delete if outside SgAnimator
	 */
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


	/**
	 * Delete if outside SgAnimator
	 */
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


	/**
	 * Delete if outside SgAnimator
	 */
	public void drawAxes() {
		for (int i=1; i<=10; i++) {
			Vector2 point = new Vector2(0f, -10*i);
			transform.applyTo(point);
			myParent.stroke(0, 127);
			if (i%5 == 0) {
				myParent.strokeWeight(2);
				myParent.line(point.x-8, point.y, point.x+8, point.y);
			} else {
				myParent.strokeWeight(1);
				myParent.line(point.x-4, point.y, point.x+4, point.y);
			}
		}
	}


	/**
	 * Delete if outside SgAnimator
	 */
	public void toggleWireframe() { wireframe = !wireframe; }


	/**
	 * Sets the background color when recording frames to disk
	 *
	 * Delete if outside SgAnimator
	 */
	public void setBackgroundColor(float r, float g, float b, float a) { backgroundColor.set(r, g, b, a); }


	/**
	 * Save the buffer to file if recording and clear buffer
	 *
	 * Delete if outside SgAnimator
	 */
	public void flush() {
		if (recording) {
			buffer.endDraw();
			buffer.save(String.format("frames/frame-%03d.png", frameNumber++));
			buffer.clear();
			buffer.beginDraw();
			buffer.background(backgroundColor.r*255f, backgroundColor.g*255f, backgroundColor.b*255f, backgroundColor.a*255f);
		}
	}


	/**
	 * Shapes rendered will be saved to file
	 *
	 * Delete if outside SgAnimator
	 */
	public void startRecording() {
		startRecording(0);
	}

	/**
	 * Shapes rendered will be saved to file
	 *
	 * Delete if outside SgAnimator
	 */
	public void startRecording(int startFrame) {
		recording = true;
		buffer = myParent.createGraphics(myParent.width, myParent.height);
		buffer.beginDraw();
		buffer.background(backgroundColor.r*255f, backgroundColor.g*255f, backgroundColor.b*255f, backgroundColor.a*255f);
		frameNumber = startFrame;
	}

	/**
	 * Delete if outside SgAnimator
	 */
	public void stopRecording() {
		recording = false;
	}

	/**
	 * Delete if outside SgAnimator
	 */
	public boolean isRecording() { return recording; }

	/**
	 * return the version of the Library.
	 *
	 * @return String
	 */
	public static String version() {
		return VERSION;
	}
}

