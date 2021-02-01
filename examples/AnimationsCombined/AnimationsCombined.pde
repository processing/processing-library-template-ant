import com.badlogic.gdx.utils.*;
import gwel.game.anim.*;
import gwel.game.graphics.*;


ComplexShape shape;
MyRenderer renderer;


void setup() {
  size(500, 500);
  
  renderer = new MyRenderer(this);
  
  shape = new ComplexShape();
  DrawableCircle circle = new DrawableCircle(0, 0, 2);
  circle.setColor(0.8f, 0.3f, 0f, 1f);
  shape.addShape(circle);
  
  TFTimetable fn = new TFTimetable(4f, true, false);
  float[] array = new float[] {20, -50, 40, -70, 0, 30};
  fn.setTable(array);
  shape.addAnimation(new Animation(new TFEaseFromTo(-200, 200, 0, 4, "linear", false, true), Animation.AXE_X));
  shape.addAnimation(new Animation(fn, Animation.AXE_Y));
  shape.addAnimation(new Animation(new TFSin(2f, 3f, 6f, 0f), Animation.AXE_Z));
}


void draw() {
  background(255);
  pushMatrix();
  translate(width/2, height/2);
  shape.update(1f/frameRate);
  shape.draw(renderer);
  popMatrix();
}
