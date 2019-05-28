import elementaryGUI.*;

UI ui;

Label label;
Button button;
TextField textField;

Panel panel;

void settings() {
  size(400, 400);
}

void setup() {
  ui = new UI(this) {
    @Override
      public void displayBackground(Component component) {
      pushMatrix();
      noStroke();
      fill(this.getBackground(), 100);
      rect(0, 0, component.getWidth(), component.getHeight(), 5);
      popMatrix();
    }
  };

  label = new Label(this);
  label.setBounds(10, 10, 100, 50);
  label.setText("Hello");
  label.setUI(ui);

  button = new Button(this);
  button.setBounds(200, 10, 100, 50);
  button.setText("Click me");
  button.setUI(ui);

  panel = new Panel(this);
  panel.setBounds(20, 20, 360, 360);
  panel.setUI(ui);
  panel.add(label);
  panel.add(button);

  textField = new TextField(this);
  textField.setBounds(width*0.5, height*0.5, 160, 25);
  textField.setUI(ui);
  UI clone = ui;
  clone.setColors(#EEEEEE, #FFFFFF);
  textField.setEditedUI(clone);
}

void draw() {
  background(125);
  panel.display();

  textField.display();
}
