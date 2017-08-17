package tmiengine.library.world.worldInterfaces;

//defines available world modes
public enum TMIworldMode
{
	freeze, //world is stopped
	edit, //world is under editing from the user
	simulate, //world is simulating...
	victory; //object score has been accomplished, world is freezed again
}