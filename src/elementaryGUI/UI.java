package elementaryGUI;

import processing.core.*;

public class UI implements Cloneable {
	public PApplet applet;
	private int background;
	private int foreground;

	public UI(PApplet applet) {
		background = applet.color(0);
		foreground = applet.color(255);
		this.applet = applet;
	}

	public UI(int background, int foreground, PApplet applet) {
		this.setBackground(background);
		this.setForeground(foreground);
		this.applet = applet;
	}

	public void setBackground(int background) {
		this.background = background;
	}

	public int getBackground() {
		return this.background;
	}

	public void setForeground(int foreground) {
		this.foreground = foreground;
	}

	public int getForeground() {
		return this.foreground;
	}

	public void setColors(int background, int foreground) {
		this.setBackground(background);
		this.setForeground(foreground);
	}

	public void displayBackground(Component component) {
		applet.fill(this.getBackground());
		applet.rect(0, 0, component.getWidth(), component.getHeight());
	}

	public void displayText(Label label) { // Make Something for every component
		applet.fill(this.getForeground());
		applet.textSize(label.getTextSize());
		applet.text(label.getText(), (float) ((label.getWidth() - applet.textWidth(label.getText())) * 0.5),
				(float) ((label.getTextSize()) * 0.85 + (label.getHeight() - (label.getTextSize())) * 0.5));
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}