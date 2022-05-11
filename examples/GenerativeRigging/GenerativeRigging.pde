// NOT WORKING

import gwel.game.anim.*;
import gwel.game.entities.*;
import gwel.game.graphics.*;
import gwel.game.utils.*;

ComplexShape blob;
MyRenderer renderer;


void setup() {
  size(500, 500);
  
  renderer = new MyRenderer(this);

  blob = buildBlob();
}


void draw() {
  background(255);
  
  blob.update(1f/frameRate);
  
  pushMatrix();
  translate(width/2, height/2);
  blob.draw(renderer);
  popMatrix();
}


ComplexShape buildBlob() {
  // A try at a generative rigging
  int n = 10;

  ComplexShape root = new ComplexShape();
  ComplexShape parent = root;
  float x = 0;
  for (int i=0; i<n; i++) {
    ComplexShape segment = new ComplexShape();
    segment.addShape(new DrawableCircle(x, 0, 20));
    float[] verts = {x, 10, x+50, 6, x+50, -6, x, -10};
    segment.addShape(new DrawablePolygon(verts, null));
    segment.setLocalOrigin(x, 0);
    Animation[] anims = new Animation[1];
    anims[0] = new Animation(new TFSin(0.2f, 0.3f, 0f, x*0.012f), Animation.AXE_ROT);
    segment.setAnimationList(anims);
    parent.addShape(segment);
    parent = segment;
    x += 50;
  }
  return root;
}
