package jp.nyatla.mimic.mbedjs.psgapi.driver;

import jp.nyatla.mimic.mbedjs.MbedJsException;
import jp.nyatla.mimic.mbedjs.psgapi.I2C;
import jp.nyatla.mimic.mbedjs.psgapi.JavaObjectWrapper;
import jp.nyatla.mimic.mbedjs.psgapi.Mcu;
public class Adafruit_8x8martix extends JavaObjectWrapper<jp.nyatla.mimic.mbedjs.javaapi.driver.adafruit.Adafruit_8x8martix>
{
	private static jp.nyatla.mimic.mbedjs.javaapi.driver.adafruit.Adafruit_8x8martix _new(I2C i_i2c,int i_address){
		return new jp.nyatla.mimic.mbedjs.javaapi.driver.adafruit.Adafruit_8x8martix(i_i2c.getSuperInstance(),i_address);
	}
	private static jp.nyatla.mimic.mbedjs.javaapi.driver.adafruit.Adafruit_8x8martix _new(Mcu i_mcu, int sda, int scl, int i_address){
		try{
			return new jp.nyatla.mimic.mbedjs.javaapi.driver.adafruit.Adafruit_8x8martix(i_mcu.getSuperInstance(),sda,scl,i_address);
		}catch(MbedJsException e){
			throw new RuntimeException(e);
		}
	}
	public Adafruit_8x8martix(I2C i_i2c,int i_address)
	{
		super(_new(i_i2c,i_address));
	}
	public Adafruit_8x8martix(Mcu i_mcu, int sda, int scl, int i_address)
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
}