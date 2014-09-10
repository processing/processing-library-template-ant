package jp.nyatla.mimic.mbedjs.psgapi.driver;

import jp.nyatla.mimic.mbedjs.MbedJsException;
import jp.nyatla.mimic.mbedjs.psgapi.I2C;
import jp.nyatla.mimic.mbedjs.psgapi.JavaObjectWrapper;
import jp.nyatla.mimic.mbedjs.psgapi.Mcu;

public class LPS331 extends JavaObjectWrapper<jp.nyatla.mimic.mbedjs.javaapi.driver.LPS331>
{
	private static jp.nyatla.mimic.mbedjs.javaapi.driver.LPS331 _new(I2C i_i2c,int i_address){
		try{
			return new jp.nyatla.mimic.mbedjs.javaapi.driver.LPS331(
					i_i2c.getSuperInstance(),i_address);
		}catch(MbedJsException e){
			throw new RuntimeException(e);
		}
	}
	private static jp.nyatla.mimic.mbedjs.javaapi.driver.LPS331 _new(
			Mcu i_mcu, int sda, int scl, int i_address){
		try{
			return new jp.nyatla.mimic.mbedjs.javaapi.driver.LPS331(
					i_mcu.getSuperInstance(),sda,scl,i_address);
		}catch(MbedJsException e){
			throw new RuntimeException(e);
		}
	}	
	public LPS331(I2C i_i2c,int i_address){

		super(_new(i_i2c,i_address));
	}
	public LPS331(Mcu i_mcu, int sda, int scl, int i_address)
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
	public int whoami() 
	{
		try {
			return this._inst.whoami();
		} catch (MbedJsException e) {
			throw new RuntimeException(e);
		}
	}
	public boolean isAvailable()
	{
		try {
			return this._inst.isAvailable();
		} catch (MbedJsException e) {
			throw new RuntimeException(e);
		}
	}
	public void setResolution(int i_pressure_avg,int i_temp_avg) 
	{
		try {
			this._inst.setResolution(i_pressure_avg, i_temp_avg);
		} catch (MbedJsException e) {
			throw new RuntimeException(e);
		}
	}
	public void setActive(boolean i_is_active) 
	{
		try {
			this._inst.setActive(i_is_active);
		} catch (MbedJsException e) {
			throw new RuntimeException(e);
		}
	}
	public void setDataRate(int i_datarate)
	{
		try {
			this._inst.setDataRate(i_datarate);
		} catch (MbedJsException e) {
			throw new RuntimeException(e);
		}
	}
	public float getPressure()
	{
		try {
			return this._inst.getPressure();
		} catch (MbedJsException e) {
			throw new RuntimeException(e);
		}
	}
	public float getTemperature()
	{
		try {
			return this._inst.getTemperature();
		} catch (MbedJsException e) {
			throw new RuntimeException(e);
		}
	}
	public byte[] _read_multibyte(byte i_startsubaddress,int i_count)
	{
		try {
			return this._inst._read_multibyte(i_startsubaddress, i_count);
		} catch (MbedJsException e) {
			throw new RuntimeException(e);
		}
	}
	
}
