package jp.nyatla.mimic.mbedjs.psgapi.driver;
import jp.nyatla.mimic.mbedjs.MbedJsException;
import jp.nyatla.mimic.mbedjs.psgapi.*;
/**
 * MPL115A2 device driver class.
 * This class based on <a href="http://mbed.org/users/yamaguch/code/MPL115A2/">http://mbed.org/users/yamaguch/code/MPL115A2/</a>.
 */
public class MPL115A2 extends JavaObjectWrapper<jp.nyatla.mimic.mbedjs.javaapi.driver.MPL115A2>
{
	private static jp.nyatla.mimic.mbedjs.javaapi.driver.MPL115A2 _new(I2C i_i2c,int i_address){
		try{
			return new jp.nyatla.mimic.mbedjs.javaapi.driver.MPL115A2(i_i2c.getSuperInstance(),i_address);
		}catch(MbedJsException e){
			throw new RuntimeException(e);
		}
	}
	private static jp.nyatla.mimic.mbedjs.javaapi.driver.MPL115A2 _new(Mcu i_mcu, int sda, int scl, int i_address){
		try{
			return new jp.nyatla.mimic.mbedjs.javaapi.driver.MPL115A2(i_mcu.getSuperInstance(),sda,scl,i_address);
		}catch(MbedJsException e){
			throw new RuntimeException(e);
		}
	}	
	public MPL115A2(I2C i_i2c,int i_address)
	{
		super(_new(i_i2c,i_address));
	}
	public MPL115A2(Mcu i_mcu, int sda, int scl, int i_address)
	{
		super(_new(i_mcu,sda,scl,i_address));
	}
	/**
	 * センサから読み取ったバイナリデータを格納します。
	 * @return
	 */
	public byte[] readRaw(){
		try {
			return this._inst.readRaw();
		} catch (MbedJsException e) {
			throw new RuntimeException(e);
		}
	}
	/**
	 * センサから値を読み取ります。
	 * @return
	 * 気圧、気温データを格納した配列です。
	 */
	public float[] read(){
		try {
			return this._inst.read();
		} catch (MbedJsException e) {
			throw new RuntimeException(e);
		}
	}
	/**
	 * センサから気圧を読み取ります。
	 * @return
	 * kPa単位の値です。
	 */
	public float getPressure(){
		try {
			return this._inst.getPressure();
		} catch (MbedJsException e) {
			throw new RuntimeException(e);
		}
	}
	/**
	 * センサから温度を読み取ります。
	 * @return
	 * 摂氏単位の値です。
	 */
	public float getTemperature(){
		try {
			return this._inst.getTemperature();
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
