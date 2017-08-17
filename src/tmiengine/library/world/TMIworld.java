package tmiengine.library.world;

//java arraylist
import java.util.ArrayList;

//processing core
import processing.core.PApplet;
//Fisica library
import fisica.*;
//tmi interfaces
import tmiengine.library.world.interfaces.*;

/**
 * TMIworld class - the incredible machine world creator and manager.
 * This class allows users to safely interface with the TMI world.
 * From this class it is possible to create and remove objects, change gravity
 * and call other managing methods.
 * Due to java packaging protection, this is the only way to access objects classes.
 * 
 * WARNING! Due to Fisica structure, it is not recommended to spawn multiple worlds,
 *  because Fisica worlds interferes with each other!
 */
public class TMIworld implements TMIworldInterface {
	//variables
	//ArrayList of all world's objects
	private ArrayList<TMIobjectsInterface> objects=new ArrayList();
	//Fisica world
	private FWorld world;
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
	
	
	/**
	 * TMIworld constructor - initialises a new instance of TMIworld.
	 * When the constructor is invoked, we have to set the world edges. The given
	 * values will be stored for later use and a Fisica world will be instantiated.
	 * Finally, the constructor will calculate internal dimensions constants
	 * (percentuals) to be used for object positioning and their dimensions.
	 * 
	 * @param X1 First edge x
	 * @param Y1 First edge y
	 * @param X2 Second edge x
	 * @param Y2 Second edge y
	 */
	public TMIworld(double X1, double Y1, double X2, double Y2)
	{
		//stores edges position
		x1=X1;
		y1=Y1;
		x2=X2;
		y2=X2;
		
		//creates a Fisica world with the given edges
		world=new FWorld();
		world.setEdges((float)x1, (float)y1, (float)x2, (float)y2);
		
		//calculates internal percentuals dimensions
		xPercent=Math.abs(x2-x1)/100;
		yPercent=Math.abs(y2-y1)/100;
	}
	
	/**
	 * setWorldGravity - changes world gravity (can be set both x and y axes).
	 * This method provides a way to change world gravity by setting x and y axes force separately.
	 * It is also possible to override default settings by writing true as third parameter.
	 * If you don't want to override defaults, type false as third parameter.
	 * 
	 * @param Gx Gravitational force on x axis
	 * @param Gy Gravitaional force on y axis
	 * @param overrideDefaults Wether to override default gravity settings
	 */
	@Override
	public void setWorldGravity(float Gx, float Gy, boolean overrideDefaults)
	{
		//checks wether values must be saved as defaults or not
		if(overrideDefaults)
		{
			//overwrites default gravity values
			xWorldGravity=Gx;
			yWorldGravity=Gy;
			
			//changes world gravity
			world.setGravity((float)xWorldGravity, (float)yWorldGravity);
		}
		else
		{
			//changes only Fisica world gravity
			world.setGravity(Gx, Gy);
		}
	}

	@Override
	public void updateAndDraw()
	{
	}

	@Override
	public void selectWorldMode(TMIworldMode mode)
	{
	}

	@Override
	public void addToWorld(String object, float X, float Y, int rotation, boolean isFlipped)
	{
	}

	/**
	 * removeFromWorld - removes objects from the world.
	 * Providing a TMI object to this function, it will be eliminated from
	 * the arrayList containing all the objects. The parameter pointer is a basic java
	 * object pointer, but before trying to remove illegal objects from the
	 * arrayList, the function will perform a check on the object implementation.
	 * 
	 * @param object the object to remove from the world
	 */
	@Override
	public void removeFromWorld(Object object)
	{
		//checks wether an object is implementing TMIobjectsInterface
		if(object instanceof TMIobjectsInterface)
		{
			//remove the object from the arrayList
			objects.remove((TMIobjectsInterface)object);
		}
		else
		{
			System.out.println("ERROR--The passed object is not implementing TMIobjectsInterface. Skipping...");
		}
	}

}
