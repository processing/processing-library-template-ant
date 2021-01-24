import com.badlogic.gdx.math.*;
import gwel.game.anim.Animation;


Interpolation fn;
int interpolationNumber = 0;
String interpolationName;
float inVal, outVal;
float circleRadius = 50;


void setup() {
  size(500, 500);
  ellipseMode(CENTER);
  noStroke();
  
  fn = Animation.getInterpolation(interpolationNumber);
  interpolationName = Animation.interpolationNames[interpolationNumber];
}


void draw() {
  background(255);
  fill(#FFC500);
  outVal = fn.apply(inVal);
  circle(map(outVal, 0f, 1f, circleRadius, width-circleRadius), height/2, 2*circleRadius);
  
  fill(0, 127);
  text(interpolationName, (width/2) - textWidth(interpolationName)/2, height-20);
  
  inVal += 0.01f;
  if (inVal > 1f)
    inVal = 0f;  
}


void mouseClicked() {
  interpolationNumber = (interpolationNumber+1) % Animation.interpolationNames.length;
  fn = Animation.getInterpolation(interpolationNumber);
  interpolationName = Animation.interpolationNames[interpolationNumber];
  inVal = 0f;
}
