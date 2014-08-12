package jp.nyatla.mimic.mbedjs.psgapi.driver;
import jp.nyatla.mimic.mbedjs.MbedJsException;
import jp.nyatla.mimic.mbedjs.psgapi.I2C;
import jp.nyatla.mimic.mbedjs.psgapi.JavaObjectWrapper;
import jp.nyatla.mimic.mbedjs.psgapi.Mcu;
/**
 * LM75B device driver class.
 * This class has the same functions as <a href="https://mbed.org/users/neilt6/code/LM75B/">https://mbed.org/users/neilt6/code/LM75B/</a>.
 */
public class LM75B extends JavaObjectWrapper<jp.nyatla.mimic.mbedjs.javaapi.driver.LM75B>
{
	private static jp.nyatla.mimic.mbedjs.javaapi.driver.LM75B _new(I2C i_i2c,int i_address){
		try{
			return new jp.nyatla.mimic.mbedjs.javaapi.driver.LM75B(i_i2c.getSuperInstance(),i_address);
		}catch(MbedJsException e){
			throw new RuntimeException(e);
		}
	}
	private static jp.nyatla.mimic.mbedjs.javaapi.driver.LM75B _new(Mcu i_mcu, int sda, int scl, int i_address){
		try{
			return new jp.nyatla.mimic.mbedjs.javaapi.driver.LM75B(i_mcu.getSuperInstance(),sda,scl,i_address);
		}catch(MbedJsException e){
			throw new RuntimeException(e);
		}
	}	
	public LM75B(I2C i_i2c,int i_address)
	{
		super(_new(i_i2c,i_address));
	}
	public LM75B(Mcu i_mcu, int sda, int scl, int i_address)
	{
		super(_new(i_mcu,sda,scl,i_address));
	}
	public float read(){
		try {
			return this._inst.read();
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
