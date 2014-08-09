package jp.nyatla.mimic.mbedjs.psgapi.driver;

import processing.core.PVector;
import jp.nyatla.mimic.mbedjs.MbedJsException;
import jp.nyatla.mimic.mbedjs.Vector3d;
import jp.nyatla.mimic.mbedjs.psgapi.I2C;
import jp.nyatla.mimic.mbedjs.psgapi.JavaObjectWrapper;
import jp.nyatla.mimic.mbedjs.psgapi.Mcu;
/**
 * MMA8451Q device driver class.
 * This class has the same functions as <a href="https://mbed.org/users/emilmont/code/MMA8451Q/">https://mbed.org/users/emilmont/code/MMA8451Q/</a>.
 */
public class MMA8451Q extends JavaObjectWrapper<jp.nyatla.mimic.mbedjs.javaapi.driver.MMA8451Q>
{
	private static jp.nyatla.mimic.mbedjs.javaapi.driver.MMA8451Q _new(I2C i_i2c,int i_address){
		try{
			return new jp.nyatla.mimic.mbedjs.javaapi.driver.MMA8451Q(i_i2c.getSuperInstance(),i_address);
		}catch(MbedJsException e){
			throw new RuntimeException(e);
		}
	}
	private static jp.nyatla.mimic.mbedjs.javaapi.driver.MMA8451Q _new(Mcu i_mcu, int sda, int scl, int i_address){
		try{
			return new jp.nyatla.mimic.mbedjs.javaapi.driver.MMA8451Q(i_mcu.getSuperInstance(),sda,scl,i_address);
		}catch(MbedJsException e){
			throw new RuntimeException(e);
		}
	}	
	public MMA8451Q(I2C i_i2c,int i_address)
	{
		super(_new(i_i2c,i_address));
	}
	public MMA8451Q(Mcu i_mcu, int sda, int scl, int i_address)
	{
		super(_new(i_mcu,sda,scl,i_address));
	}
	
	public PVector getAccAllAxis(){
		try {
			Vector3d v=this._inst.getAccAllAxis();
			return new PVector(v.x,v.y,v.z);
		} catch (MbedJsException e) {
			throw new RuntimeException(e);
		}
	}
	public float getAccX()
	{
		try {
			return this._inst.getAccX();
		} catch (MbedJsException e) {
			throw new RuntimeException(e);
		}
	}
	public float getAccY()
	{
		try {
			return this._inst.getAccY();
		} catch (MbedJsException e) {
			throw new RuntimeException(e);
		}
	}
	public float getAccZ()
	{
		try {
			return this._inst.getAccZ();
		} catch (MbedJsException e) {
			throw new RuntimeException(e);
		}
	}
	public int getWhoAmI()
	{
		try {
			return this._inst.getWhoAmI();
		} catch (MbedJsException e) {
			throw new RuntimeException(e);
		}
	}	
	public void dispose()
	{
		try {
			this._inst.dispose();
		} catch (MbedJsException e) {
			throw new RuntimeException(e);
		}
	}
}
