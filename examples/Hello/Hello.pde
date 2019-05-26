import elementaryGUI.*;

Component c;

void settings() {
  size(400, 400);
}

void setup() {
  c = new Component(this);
}

void draw() {
  c.display();
}
