package jp.nyatla.mimic.mbedjs.psgapi.driver;

import jp.nyatla.mimic.mbedjs.MbedJsException;
import jp.nyatla.mimic.mbedjs.psgapi.I2C;
import jp.nyatla.mimic.mbedjs.psgapi.JavaObjectWrapper;
import jp.nyatla.mimic.mbedjs.psgapi.Mcu;

public class ACM1602NI extends JavaObjectWrapper<jp.nyatla.mimic.mbedjs.javaapi.driver.ACM1602NI>
{
	private static jp.nyatla.mimic.mbedjs.javaapi.driver.ACM1602NI _new(I2C i_i2c,int i_address)
	{
		try{
			return new jp.nyatla.mimic.mbedjs.javaapi.driver.ACM1602NI(
					i_i2c.getSuperInstance(),i_address);
		}catch(MbedJsException e){
			throw new RuntimeException(e);
		}

	}
	private static jp.nyatla.mimic.mbedjs.javaapi.driver.ACM1602NI 
						_new(Mcu i_mcu, int sda, int scl, int i_address)
	{
		try{
			return new jp.nyatla.mimic.mbedjs.javaapi.driver.ACM1602NI(
					i_mcu.getSuperInstance(),sda,scl,i_address);
		}catch(MbedJsException e){
			throw new RuntimeException(e);
		}
	}
	public ACM1602NI(I2C i_i2c,int i_address)
	{
		super(_new(i_i2c,i_address));
	}
	public ACM1602NI(Mcu i_mcu, int sda, int scl, int i_address)
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
	public void character(int i_column, int i_row, int i_c)
	{
		try {
			this._inst.character(i_column, i_row, i_c);
		} catch (MbedJsException e) {
			throw new RuntimeException(e);
		}
	}
	public void cls()
	{
		try {
			this._inst.cls();
		} catch (MbedJsException e) {
			throw new RuntimeException(e);
		}
		
	}
	public void locate(int i_column, int i_row) {
		this._inst.locate(i_column, i_row);
	}
	
	public int puts(String i_s) {
		try {
			return this._inst.puts(i_s);
		} catch (MbedJsException e) {
			throw new RuntimeException(e);
		}
	}
	
	
}
