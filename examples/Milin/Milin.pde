import com.badlogic.gdx.utils.*;
import com.badlogic.gdx.math.*;
import gwel.game.anim.*;
import gwel.game.graphics.*;
import gwel.game.entities.Avatar;


MyRenderer renderer;
ComplexShape mill;

void setup() {
  size(700, 700);
  colorMode(HSB, 1f);

  renderer = new MyRenderer(this);

  makeMill();

  background(1f);
}


void makeMill() {
  int numWings = floor(random(8, 32));
  float innerRadius = numWings * 4;
  println(innerRadius);
  float duration = 4f;
  int r1 = floor(random(1, 15));

  // Single wing shape
  float[] verts = new float[] {0f, 10f, 100f, 18f, 106f, 6f, 110f, 0f, 106f, -6f, 100f, -18f, 0f, -10f};
  ComplexShape wing = new ComplexShape();
  DrawablePolygon shape = new DrawablePolygon(verts);
  color c = color(random(1), 0.4f, 1f);
  shape.setColor(red(c), green(c), blue(c), 0.5f);
  wing.addShape(shape);
  TimeFunction stretch = new TFSin(duration*random(0.8f, 2f), 0.4f, map(numWings, 8, 32, 0.5f, 2f), 0f);
  wing.addAnimation(new Animation(stretch, Animation.AXE_SX));
  TimeFunction translate = new TFConstant(innerRadius);
  wing.addAnimation(new Animation(translate, Animation.AXE_X));

  mill = new ComplexShape();
  for (int i=0; i<numWings; i++) {
    ComplexShape newWing = wing.copy();
    newWing.setColorMod(random(0.9, 1.1), random(0.9, 1.1), random(0.9, 1.1), 1f);
    newWing.getAnimation(0).setParam("phase", r1*i*360/numWings);
    TimeFunction rotate = new TFConstant((float) i/numWings);
    // Functions value get automaticaly mapped to range [-180,180] when using rotation axe 
    // but you could also a value in degrees between 0 and 360 to TFConstant and invoke setAmp(1.f) on the animation
    newWing.addAnimation(new Animation(rotate, Animation.AXE_ROT));
    mill.addShape(newWing);
  }

  TimeFunction spin = new TFSpin(0f, duration*random(2f, 4f), 1f);
  mill.addAnimation(new Animation(spin, Animation.AXE_ROT));
}

void mouseClicked() {
  background(1f);
  makeMill();
}


void draw() {
  background(1);
  //fill(1f, 0.01f);
  //rect(0, 0, width, height);
  pushMatrix();
  translate(width/2, height/2);  
  mill.update(1f/frameRate);
  mill.draw(renderer);

  popMatrix();
}
