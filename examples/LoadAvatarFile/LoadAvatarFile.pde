import gwel.game.graphics.*;
import gwel.game.entities.*;
import com.badlogic.gdx.math.*;

Avatar avatar;
MyRenderer renderer;
Affine2 transform;

void setup() {
  size(350, 350);
  renderer = new MyRenderer(this);
  avatar = Avatar.fromFile(sketchFile("data/tete.json"));
  renderer.translate(width/2, height);
  renderer.scale(3);
  
  //renderer.startRecording();
}


void draw() {
  background(255);
  
  avatar.update(1/frameRate);
  avatar.draw(renderer);

  /*
  if (frameCount < 30) {
    renderer.flush();
    fill(255, 0, 0);
    circle(width-32, 32, 16);
  } else {
    renderer.stopRecording();
  }
  */
}
