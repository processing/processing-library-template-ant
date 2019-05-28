package elementaryGUI;

import processing.core.*;

public class Component extends Displayable implements Cloneable {
	public PApplet applet;

	private Component parent;
	private UI ui;

	private PVector position;
	private PVector dimension;

	private boolean hasParent;

	public Component(PApplet applet) {
		this.position = new PVector(0, 0);
		this.dimension = new PVector(0, 0);
		this.ui = new UI(applet);
		this.hasParent = false;
		this.applet = applet;
	}

	public void setX(float x) {
		this.getPosition().x = x;
	}

	public float getX() {
		return this.getPosition().x;
	}

	public void setY(float y) {
		this.getPosition().y = y;
	}

	public float getY() {
		return this.getPosition().y;
	}

	public void setWidth(float _width) {
		this.getDimension().x = _width;
	}

	public float getWidth() {
		return this.getDimension().x;
	}

	public void setHeight(float _height) {
		this.getDimension().y = _height;
	}

	public float getHeight() {
		return this.getDimension().y;
	}

	public void setPosition(PVector position) {
		this.position = position;
	}

	public void setPosition(float x, float y) {
		this.getPosition().x = x;
		this.getPosition().y = y;
	}

	public PVector getPosition() {
		return this.position;
	}

	public void setDimension(PVector dimension) {
		this.dimension = dimension;
	}

	public void setDimension(float _width, float _height) {
		this.dimension.x = _width;
		this.dimension.y = _height;
	}

	public PVector getDimension() {
		return this.dimension;
	}

	public void setBounds(PVector position, PVector dimension) {
		this.setPosition(position);
		this.setDimension(dimension);
	}

	public void setBounds(float x, float y, float _width, float _height) {
		this.setPosition(x, y);
		this.setDimension(_width, _height);
	}

	public void setUI(UI ui) {
		this.ui = ui;
	}

	public UI getUI() {
		return this.ui;
	}

	public void setParent(Component component) {
		this.hasParent = true;
		this.parent = new Component(applet);
		this.parent = component;
	}

	public Component getParent() {
		if (this.hasParent) {
			return this.parent;
		} else {
			return new Component(applet);
		}
	}

	@Override
	public void display() {
		if (this.isVisible()) {
			applet.pushMatrix();
			applet.translate(this.getX(), this.getY());
			this.getUI().displayBackground(this);
			applet.popMatrix();
		}
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}