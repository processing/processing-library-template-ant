package elementaryGUI;

import processing.core.*;

public class Button extends Label implements Clickable {
	private UI clicked;
	private UI touched;

	public Button(PApplet applet) {
		super(applet);
		clicked = new UI(applet.color(255), applet.color(0), applet);
		touched = new UI(applet.color(200), applet.color(255), applet);
	}

	public void setClicked(UI clicked) {
		this.clicked = clicked;
	}

	public UI getClickedUI() {
		return this.clicked;
	}

	public void setTouchedUI(UI touched) {
		this.touched = touched;
	}

	public UI getTouchedUI() {
		return this.touched;
	}

	@Override
	public void setUI(UI ui) {
		super.setUI(ui);

		try {
			this.setClicked((UI) ui.clone());
			this.setTouchedUI((UI) ui.clone());
		} catch (CloneNotSupportedException c) {
		}
		this.getClickedUI().setColors(applet.color(255), applet.color(0));
		this.getTouchedUI().setColors(applet.color(200), applet.color(255));
	}

	@Override
	public boolean hasMouseInside() {
		if (applet.mouseX > this.getX() + this.getParent().getX()
				&& applet.mouseX < this.getX() + this.getParent().getX() + this.getWidth()
				&& applet.mouseY > this.getY() + this.getParent().getY()
				&& applet.mouseY < this.getY() + this.getParent().getY() + this.getHeight()) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean isClicked() {
		if (this.hasMouseInside() && applet.mousePressed && this.isVisible()) {
			applet.mousePressed = false;
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void executeClickedTasks() {
		return;
	}

	@Override
	public void executeTouchedTasks() {
		return;
	}

	@Override
	public void display() {
		if (this.isVisible()) {
			applet.pushMatrix();
			applet.translate(this.getX(), this.getY());
			if (this.isClicked()) {
				this.getClickedUI().displayBackground(this);
				executeClickedTasks();
			} else if (this.hasMouseInside()) {
				this.getTouchedUI().displayBackground(this);
				executeTouchedTasks();
			} else {
				this.getUI().displayBackground(this);
			}

			if (!this.getText().isEmpty())
				this.getUI().displayText(this);
			applet.popMatrix();
		}
	}
}