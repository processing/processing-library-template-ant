package tmiengine.library;

//processing - core library
import processing.core.*;
//Ricard Marxer - fisica library
import fisica.*;

/**
 * This is the class wrapper of the library. To create an instance of the library, you must
 * use TMIengine <varName>=new TMIengine(this);
 */

public class TMIengine
{
	//variables
	//reference to parent sketch (applet)
	PApplet parentSketch;	

	/**
	 * The constructor needs a PApplet. To assign it during the initialization, use the keyword
	 * "this". The constructor also create an instance of Fisica library.
	 * 
	 * @param parentSketch
	 */
	public TMIengine(PApplet parent)
	{
		//assigns the applet to the variable
		parentSketch=parent;
		welcomeMessage();
		
		//initializes Fisica library
		Fisica.init(parentSketch);
	}
	
	
	private void welcomeMessage()
	{
		//announces the library execution
		System.out.println("##library.name## ##library.prettyVersion## by ##author##");
	}
}