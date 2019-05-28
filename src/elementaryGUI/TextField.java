package elementaryGUI;

import processing.core.*;

public class TextField extends Label implements Clickable {
	private boolean editable;
	private int maxLetters;

	private UI edited;

	public TextField(PApplet applet) {
		super(applet);
		editable = false;
		maxLetters = (int) (this.getWidth() / applet.textWidth('w'));
		edited = new UI(applet.color(170), applet.color(255), applet);
	}

	public void setEditable(boolean editable) {
		this.editable = editable;
	}

	public boolean isEditable() {
		return this.editable;
	}

	public void setEditedUI(UI edited) {
		this.edited = edited;
	}

	public UI getEditedUI() {
		return this.edited;
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
		if (!this.isEditable()) {
			this.setEditable(true);
		}
	}

	@Override
	public void executeTouchedTasks() {
		return;
	}

	private void edit() {
		if (applet.keyPressed) {
			if (applet.key == applet.CODED)
				return;

			if (applet.key == applet.BACKSPACE && this.getText().length() >= 1) {
				this.setText(this.getText().substring(0, this.getText().length() - 1));
			} else if (applet.key >= ' ') {
				this.setText(this.getText() + applet.key);
			}
			applet.keyPressed = false;
		}
	}

	@Override
	public void display() {
		if (this.isVisible()) {
			applet.pushMatrix();
			applet.translate(this.getX(), this.getY());
			this.getUI().displayBackground(this);

			maxLetters = (int) (this.getWidth() / applet.textWidth('w'));

			if (this.isClicked()) {
				this.executeClickedTasks();
			} else if (!this.isClicked() && applet.mousePressed) {
				this.setEditable(false);
			}

			if (this.isEditable()) {
				this.getEditedUI().displayBackground(this);
				this.edit();
			}

			if (!this.getText().isEmpty()) {
				this.getUI().displayText(this);
			}
			applet.popMatrix();
		}
	}
}
