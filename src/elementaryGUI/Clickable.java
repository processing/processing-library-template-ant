package elementaryGUI;

public interface Clickable {
	public boolean hasMouseInside();

	public boolean isClicked();

	public void executeClickedTasks();

	public void executeTouchedTasks();
}