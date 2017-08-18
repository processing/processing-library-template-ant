package tmiengine.library;

//processing - core library
import processing.core.*;
//Ricard Marxer - fisica library
import fisica.*;

/**
 * Library wrapper.
 * This is the class wrapper of the library. To create an instance of the library, you must
 * use TMIengine varName=new TMIengine(this);
 */
public class TMIengine
{
	//variables
	//reference to parent sketch (applet)
	private PApplet parentSketch;	

	/**
	 * Library constructor - initialises a new instance of TMIengine.
	 * The constructor needs a PApplet. To assign it during the initialization of the sketch, use the keyword
	 * "this". The constructor stores PApplet and creates an instance of Fisica library.
	 * 
	 * @param parentSketch The PApplet (or simply, sketch) where the library is running
	 */
	public TMIengine(PApplet parent)
	{
		//assigns the applet to the variable
		parentSketch=parent;
		welcomeMessage();
		
		//initializes Fisica library
		Fisica.init(parentSketch);
	}
	
	/**
	 * Library announcer - prints a console message reporting version and author, when a library instance is initialized.
	 */
	private void welcomeMessage()
	{
		//announces the library execution
		System.out.println("##library.name## ##library.prettyVersion## by ##author##");
	}
}