package tmiengine.library.world;

//world interface
import tmiengine.library.world.worldInterfaces.*;
//base object interface
import tmiengine.library.world.objects.objectsInterfaces.TMIobjectsInterface;
//processing core
import processing.core.*;
//java arraylist
import java.util.ArrayList;

//world class
public class TMIworld implements TMIworldInterface {
	//variables
	//parent sketch (where the library is being executed)
	private PApplet parentSketch;
	
	//ArrayList of all world's objects
	private ArrayList<TMIobjectsInterface> objects=new ArrayList();
	
	//world status
	//when the world starts, it is freezed
	private TMIworldMode mode=TMIworldMode.freeze;
	
	//world gravity
	private double xWorldGravity=0.0;
	private double yWorldGravity=25.0;
	
	//world edges
	private double x1, y1;
	private double x2, y2;
	
	//internal dimensions
	private double xPercent, yPercent;
	
	@Override
	public void setWorldGravity(float Gx, float Gy) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateAndDraw() {
		// TODO Auto-generated method stub

	}

	@Override
	public void selectWorldMode(TMIworldMode mode) {
		// TODO Auto-generated method stub
	}

	@Override
	public void addToWorld(String object, float X, float Y, int rotation, boolean isFlipped) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeFromWorld(Object object) {
		// TODO Auto-generated method stub

	}

}
