package tmiengine.library.world;

import fisica.FBox;
import tmiengine.library.world.interfaces.TMIobjectsInterface;

class testObject extends FBox implements TMIobjectsInterface {

	testObject()
	{
		super(40, 40);
	}
	
	@Override
	public void freeze() {
		// TODO Auto-generated method stub

	}

	@Override
	public void unfreeze() {
		// TODO Auto-generated method stub

	}

	@Override
	public int update() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void draw() {
		// TODO Auto-generated method stub

	}

	@Override
	public void changeStatus(int statusCode) {
		// TODO Auto-generated method stub

	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub

	}

	@Override
	public void moveTo(float X, float Y) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setAsStaticBody() {
		// TODO Auto-generated method stub

	}

	@Override
	public void setAsDynamicBody() {
		// TODO Auto-generated method stub

	}

	@Override
	public void rotateOnce() {
		// TODO Auto-generated method stub

	}

	@Override
	public void reflect() {
		// TODO Auto-generated method stub

	}

	@Override
	public void manipulate(float W, float H) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean checkForOverlap() {
		// TODO Auto-generated method stub
		return false;
	}

}
