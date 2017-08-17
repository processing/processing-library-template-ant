package tmiengine.library.world.interfaces;

//base objects interface
public interface TMIobjectsInterface
{
	//The class contructor needs all object properties such as dimension, position (both percentual), rotation, facing direction, and texture
	//eg: constructor(Wpercent, Hpercent, Xpercent, Ypercent, rotation, isFlipped, texturePath)
	public void freeze(); //freezes the object in position, making it intangible and ungrabbable. It is called during freeze, victory and edit mode
	public void unfreeze(); //unfreezes the object, making it ready for simulation purposes
	public int update(); //updates object statistics
	public void draw(); //draws the object (needed for Fisica library)
	public void changeStatus(int statusCode); //changes object status
	public void reset(); //resets the object for a new simulation
	public void moveTo(float X, float Y); //moves the object during edit mode
	public void setAsStaticBody(); //set object as static
	public void setAsDynamicBody(); //set object as dynamic
	public void rotateOnce(); //rotates the object in four possible ways (if possible, depends on the object)
	public void reflect(); //reflects the object in two possible ways (if possible, depends on the object)
	public void manipulate(float W, float H); //modifies width and length of the object (if possible, depends on the object)
	public boolean checkForOverlap(); //checks if objects are overlapped during edit mode
}
