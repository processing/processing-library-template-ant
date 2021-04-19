import gwel.game.anim.*;
import gwel.game.entities.*;
import gwel.game.graphics.*;
import gwel.game.utils.*;

MyRenderer renderer = new MyRenderer(this);
int counter = 0;

ArrayList<Avatar> mushrooms = new ArrayList();

void setup() {
  size(600, 300);
  
  renderer.setBackgroundColor(1f, 1f, 1f, 1f);
  //renderer.startRecording();
}


void draw() {
  background(255);

  for (Avatar mushroom : mushrooms) {
    mushroom.update(1/frameRate);
    mushroom.draw(renderer);
  }
  
  //renderer.flush();
}


void mouseClicked() {
  Avatar m = createMushroom(mouseX);
  m.scale(1/(1+counter*0.2f));
  mushrooms.add(m);
  
  counter += 1;
}


Avatar createMushroom(float x) {
  ComplexShape mushroomShape = new ComplexShape();
  ComplexShape original = Avatar.fromFile(sketchFile("data/mushroom.json")).shape;

  int i = floor(random(3)) + 1;
  for (int j=1; j<=i; j++) {
    ComplexShape other = original.copy();
    float tilt = random(-25f, 25f);
    float phase1 = random(-180, 180);
    float phase2 = phase1 + 180;
    float phase3 = random(-180, 180);
    other.getAnimation(0).getFunction().setParam("phase", phase1);
    other.getAnimation(1).getFunction().setParam("phase", phase2);
    other.getAnimation(2).getFunction().setParam("phase", phase3);
    other.addAnimation(new Animation(new TFConstant(tilt/360f), Animation.AXE_ROT));
    other.hardTranslate(tilt/20f, 0);
    float s = (pow(random(0.5, 0.7), j));
    other.hardScale(s, s);
    other.setColorMod(random(0.7f, 1.1f)+j/10f, random(0.7f, 1.1f)+j/10f, random(0.7f, 1.1f)+j/10f, 1);
    mushroomShape.addShape(other);
  }
  Avatar mushroom;
  mushroom = new Avatar();
  mushroom.shape = mushroomShape;
  mushroom.scale(random(15, 20));
  mushroom.setPosition(x, height-10);

  return mushroom;
}
