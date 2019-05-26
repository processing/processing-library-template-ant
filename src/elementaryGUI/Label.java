package elementaryGUI;

import processing.core.*;

public class Label extends Component {
	PApplet applet;
	private float textSize;
	private String text;

	private boolean autoTextSize;

	public Label() {
		super();
		text = "";
		textSize = 12;
		autoTextSize = true;
	}

	public void setText(String text) {
		if (this.isTextSizeAuto()) {
			this.setTextSize(this.getWidth() / text.length() > this.getHeight() - 10 ? this.getHeight() - 10
					: this.getWidth() / text.length());
		}
		this.text = text;
	}

	public String getText() {
		return this.text;
	}

	public void setTextSize(float textSize) {
		this.textSize = textSize;
	}

	public float getTextSize() {
		return textSize;
	}

	public void setAutoTextSize(boolean autoTextSize) {
		this.autoTextSize = autoTextSize;
	}

	public boolean isTextSizeAuto() {
		return this.autoTextSize;
	}

	@Override
	public void display() {
		if (this.isVisible()) {
			applet.pushMatrix();
			applet.translate(this.getX(), this.getY());
			this.getUI().displayBackground(this);

			if (!this.getText().isEmpty()) {
				this.getUI().displayText(this);
			}
			applet.popMatrix();
		}
	}
}