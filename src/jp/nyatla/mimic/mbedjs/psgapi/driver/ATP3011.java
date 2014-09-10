package jp.nyatla.mimic.mbedjs.psgapi.driver;

import jp.nyatla.mimic.mbedjs.MbedJsException;
import jp.nyatla.mimic.mbedjs.psgapi.I2C;
import jp.nyatla.mimic.mbedjs.psgapi.JavaObjectWrapper;
import jp.nyatla.mimic.mbedjs.psgapi.Mcu;


public class ATP3011 extends JavaObjectWrapper<jp.nyatla.mimic.mbedjs.javaapi.driver.ATP3011>
{
	private static jp.nyatla.mimic.mbedjs.javaapi.driver.ATP3011 _new(I2C i_i2c,int i_address){
		try{
			return new jp.nyatla.mimic.mbedjs.javaapi.driver.ATP3011(
					i_i2c.getSuperInstance(),i_address);
		}catch(MbedJsException e){
			throw new RuntimeException(e);
		}
	}
	private static jp.nyatla.mimic.mbedjs.javaapi.driver.ATP3011 _new(
			Mcu i_mcu, int sda, int scl, int i_address){
		try{
			return new jp.nyatla.mimic.mbedjs.javaapi.driver.ATP3011(
					i_mcu.getSuperInstance(),sda,scl,i_address);
		}catch(MbedJsException e){
			throw new RuntimeException(e);
		}
	}
	
	public ATP3011(I2C i_i2c,int i_address){

		super(_new(i_i2c,i_address));
	}
	public ATP3011(Mcu i_mcu, int sda, int scl, int i_address)
	{
		super(_new(i_mcu,sda,scl,i_address));
	}
	public void dispose()
	{
		try {
			this._inst.dispose();
		} catch (MbedJsException e) {
			throw new RuntimeException(e);
		}
	}
	public boolean isActive(int i_timeout_ms)
	{
		try {
			return this._inst.isActive(i_timeout_ms);
		} catch (MbedJsException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void synthe(byte[] i_msg)
	{
		try {
			this._inst.synthe(i_msg);
		} catch (MbedJsException e) {
			throw new RuntimeException(e);
		}
	}
	public void write(byte[] i_msg)
	{
		try {
			this._inst.write(i_msg);
		} catch (MbedJsException e) {
			throw new RuntimeException(e);	
		}
	}
	public boolean isBusy()
	{
		try {
			return this._inst.isBusy();
		} catch (MbedJsException e) {
			throw new RuntimeException(e);	
		}
	}
	
	
	

}
