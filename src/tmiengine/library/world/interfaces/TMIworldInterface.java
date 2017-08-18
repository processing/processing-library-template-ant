package tmiengine.library.world.interfaces;

//world modes
import tmiengine.library.world.interfaces.TMIworldMode;
//base object interface
import tmiengine.library.world.interfaces.TMIobjectsInterface;

//base world interface
public interface TMIworldInterface
{
	//the constructor creates a Fisica world with the given edges and setup some internal variables
	public void setWorldGravity(float Gx, float Gy, boolean overrideDefaults); //sets the gravity in the world
	public void updateAndDraw(); //uses step, update and draw functions from Fisica and this library to update and draw the world
	public void selectWorldMode(TMIworldMode mode); //freezes or unfreezes all the objects in the world, depending on the world status
	public TMIworldMode getWorldMode(); //returns the current world mode
	public void addToWorld(String object, float X, float Y, int rotation, boolean isFlipped); //adds a specific object to the world with the given characteristics
	public void removeFromWorld(TMIobjectsInterface object); //deletes an object from the world
	public void moveTo(TMIobjectsInterface object, float X, float Y); //moves an object without making it escape the world (use during edit mode with mouse grab!)
}
