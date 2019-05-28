package elementaryGUI;

public abstract class Displayable {
	protected boolean isVisible = true;

	public abstract void display();

	public void setVisible(boolean visible) {
		this.isVisible = visible;
	}

	public boolean isVisible() {
		return this.isVisible;
	}
}