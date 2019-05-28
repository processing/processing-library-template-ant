package elementaryGUI;

import java.util.ArrayList;
import processing.core.*;

public class Panel extends Component {
	public ArrayList<Component> children;

	public Panel(PApplet applet) {
		super(applet);
		children = new ArrayList<Component>();
	}

	public void add(Component component) {
		component.setParent(this);
		children.add(component);
	}

	public ArrayList<Component> getChildren() {
		return this.children;
	}

	@Override
	public void setVisible(boolean visible) {
		super.setVisible(visible);

		for (int i = 0; i < this.getChildren().size(); i++) {
			this.getChildren().get(i).setVisible(visible);
		}
	}

	@Override
	public void display() {
		if (this.isVisible()) {
			applet.pushMatrix();
			applet.translate(this.getX(), this.getY());
			this.getUI().displayBackground(this);

			for (int i = 0; i < this.getChildren().size(); i++) {
				this.getChildren().get(i).display();
			}
			applet.popMatrix();
		}
	}
}
