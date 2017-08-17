package tmiengine.library.world.interfaces;

import tmiengine.library.world.interfaces.TMIworldMode;

//base world interface
public interface TMIworldInterface
{
	//the constructor creates a Fisica world with the given edges and setup some internal variables
	public void setWorldGravity(float Gx, float Gy); //sets the gravity in the world
	public void updateAndDraw(); //uses step, update and draw functions from Fisica and this library to update and draw the world
	public void selectWorldMode(TMIworldMode mode); //freezes or unfreezes all the objects in the world, depending on the world status
	public void addToWorld(String object, float X, float Y, int rotation, boolean isFlipped); //adds a specific object to the world with the given characteristics
	public void removeFromWorld(Object object); //deletes an object from the world
}
