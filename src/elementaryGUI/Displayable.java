package elementaryGUI;

public abstract class Displayable {
	protected boolean isVisible = true;

	protected abstract void display();

	protected void setVisible(boolean visible) {
		this.isVisible = visible;
	}

	protected boolean isVisible() {
		return this.isVisible;
	}
}