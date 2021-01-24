import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.utils.*;
import gwel.game.anim.*;
import gwel.game.graphics.*;
import gwel.game.entities.*;


Avatar avatar;
MyRenderer renderer;

void setup() {
  size(500, 500);
  renderer = new MyRenderer(this);
  avatar = Avatar.fromFile(sketchFile("data/tete.json"));
}


void draw() {
  avatar.updateAnimation(1/frameRate);
  
  pushMatrix();
  translate(width/2, height);
  scale(3);
  background(255);
  avatar.draw(renderer);
  popMatrix();
}
