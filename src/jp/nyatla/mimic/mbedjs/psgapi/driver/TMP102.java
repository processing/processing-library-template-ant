package jp.nyatla.mimic.mbedjs.psgapi.driver;

import jp.nyatla.mimic.mbedjs.MbedJsException;
import jp.nyatla.mimic.mbedjs.psgapi.I2C;
import jp.nyatla.mimic.mbedjs.psgapi.JavaObjectWrapper;
import jp.nyatla.mimic.mbedjs.psgapi.Mcu;

public class TMP102 extends JavaObjectWrapper<jp.nyatla.mimic.mbedjs.javaapi.driver.TMP102>
{
	private static jp.nyatla.mimic.mbedjs.javaapi.driver.TMP102 _new(
			I2C i_i2c,int i_address)
	{
		try{
			return new jp.nyatla.mimic.mbedjs.javaapi.driver.TMP102(
					i_i2c.getSuperInstance(),i_address);
		}catch(MbedJsException e){
			throw new RuntimeException(e);
		}
	}
	private static jp.nyatla.mimic.mbedjs.javaapi.driver.TMP102 _new(
			Mcu i_mcu, int sda, int scl, int i_address){
		try{
			return new jp.nyatla.mimic.mbedjs.javaapi.driver.TMP102(
					i_mcu.getSuperInstance(),sda,scl,i_address);
		}catch(MbedJsException e){
			throw new RuntimeException(e);
		}
	}	
	public TMP102(I2C i_i2c,int i_address)
	{
		super(_new(i_i2c,i_address));
	}
	public TMP102(Mcu i_mcu, int sda, int scl, int i_address)
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
	public float read(){
		try {
			return this._inst.read();
		} catch (MbedJsException e) {
			throw new RuntimeException(e);
		}
	}
}
