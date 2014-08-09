package jp.nyatla.mimic.mbedjs.psgapi.driver;

import jp.nyatla.mimic.mbedjs.MbedJsException;
import jp.nyatla.mimic.mbedjs.psgapi.I2C;
import jp.nyatla.mimic.mbedjs.psgapi.JavaObjectWrapper;
import jp.nyatla.mimic.mbedjs.psgapi.Mcu;
/**
 * MMA7660 device driver class.
 * This class has the same functions as <a href="https://mbed.org/users/Sissors/code/MMA7660/">https://mbed.org/users/Sissors/code/MMA7660/</a>.
 */
public class MMA7660 extends JavaObjectWrapper<jp.nyatla.mimic.mbedjs.javaapi.driver.MMA7660>
{
	public final static int Up		=jp.nyatla.mimic.mbedjs.javaapi.driver.MMA7660.Up;
	public final static int Down	=jp.nyatla.mimic.mbedjs.javaapi.driver.MMA7660.Down;
	public final static int Right	=jp.nyatla.mimic.mbedjs.javaapi.driver.MMA7660.Right;
	public final static int Left	=jp.nyatla.mimic.mbedjs.javaapi.driver.MMA7660.Left;
	public final static int Back	=jp.nyatla.mimic.mbedjs.javaapi.driver.MMA7660.Back;
	public final static int Front	=jp.nyatla.mimic.mbedjs.javaapi.driver.MMA7660.Front;
	public final static int Unknown	=jp.nyatla.mimic.mbedjs.javaapi.driver.MMA7660.Unknown;	
	private static jp.nyatla.mimic.mbedjs.javaapi.driver.MMA7660 _new(I2C i_i2c, int i_address){
		try {
			return new jp.nyatla.mimic.mbedjs.javaapi.driver.MMA7660(i_i2c.getSuperInstance(),i_address);
		} catch (MbedJsException e) {
			throw new RuntimeException(e);
		}
	}
	private static jp.nyatla.mimic.mbedjs.javaapi.driver.MMA7660 _new(Mcu i_mcu, int sda, int scl, int i_address){
		try {
			return new jp.nyatla.mimic.mbedjs.javaapi.driver.MMA7660(i_mcu.getSuperInstance(),sda,scl,i_address);
		} catch (MbedJsException e) {
			throw new RuntimeException(e);
		}
	}

	public MMA7660(I2C i_i2c,int i_address)
	{
		super(_new(i_i2c,i_address));
	}
	public MMA7660(Mcu i_mcu, int sda, int scl, int i_address)
	{
		super(_new(i_mcu,sda,scl,i_address));

	}
	public boolean testConnection()
	{
		try {
			return this._inst.testConnection();
		} catch (MbedJsException e) {
			throw new RuntimeException(e);
		}
	}
	public float x()
	{
		try {
			return this._inst.x();
		} catch (MbedJsException e) {
			throw new RuntimeException(e);
		}
	}
	
	public float y()
	{
		try {
			return this._inst.y();
		} catch (MbedJsException e) {
			throw new RuntimeException(e);
		}
	}
	public float z()
	{
		try {
			return this._inst.z();
		} catch (MbedJsException e) {
			throw new RuntimeException(e);
		}
	}

	public void setSampleRate(int i_samplerate)
	{
		try {
			this._inst.setSampleRate(i_samplerate);
		} catch (MbedJsException e) {
			throw new RuntimeException(e);
		}
	}
	public void setActive(boolean i_state){
		try {
			this._inst.setActive(i_state);
		} catch (MbedJsException e) {
			throw new RuntimeException(e);
		}
	}
	public int[] readData_int()
	{
		try {
			return this._inst.readData_int();
		} catch (MbedJsException e) {
			throw new RuntimeException(e);
		}
	}
	public float[] readData()
	{
		try {
			return this._inst.readData();
		} catch (MbedJsException e) {
			throw new RuntimeException(e);
		}
	}
	public int getSide()
	{
		try {
			return this._inst.getSide();
		} catch (MbedJsException e) {
			throw new RuntimeException(e);
		}
	}
	public int getOrientation()
	{
		try {
			return this._inst.getOrientation();
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
