import com.badlogic.gdx.math.*;
import gwel.game.anim.Animation;


int frameWidth = 100;
int frameHeight = 80;
int spacing = 4;


void setup() {
  size(732, 592);
  noLoop();
}


void draw() {
  background(255);
  
  int posX = spacing;
  int posY = spacing;
  for (String interpolationName : Animation.interpolationNames) {
    Interpolation fn = Animation.getInterpolation(interpolationName);
    PGraphics frame = createInterpolationFrame(frameWidth, frameHeight, fn, interpolationName);
    image(frame, posX, posY);
    posX += frameWidth + spacing;
    if (posX + frameWidth + spacing > width) {
      posX = spacing;
      posY += frameHeight + spacing;
    }
  }
}


PGraphics createInterpolationFrame(int w, int h, Interpolation fn, String name) {
  PGraphics pg = createGraphics(w, h);
  pg.beginDraw();
  pg.background(#FFC500);
  float prev = h * fn.apply(0f);
  float val;
  float penX;
  float stepX = 2f;
  pg.stroke(0);
  for (penX=stepX; penX<w; penX+=stepX) {
    val = h * fn.apply(penX/w);
    pg.line(penX-stepX, h-prev, penX, h-val);
    prev = val;
  }
  val = h * fn.apply(1f);
  pg.line(penX-stepX, h-prev, w, h-val);
  pg.fill(0, 127);
  pg.text(name, w-textWidth(name)-4, h-4);
  pg.endDraw();
  return pg;
}
