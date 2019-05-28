package elementaryGUI;

import processing.core.*;

public class Test {
	private PVector p;
	public Test(PApplet applet) {
		p = new PVector(100, 100);
		applet.text("Hello", p.x, p.y);
	}
}
