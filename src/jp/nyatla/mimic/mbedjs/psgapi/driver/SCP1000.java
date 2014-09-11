package jp.nyatla.mimic.mbedjs.psgapi.driver;

import jp.nyatla.mimic.mbedjs.MbedJsException;
import jp.nyatla.mimic.mbedjs.javaapi.Mcu;
import jp.nyatla.mimic.mbedjs.javaapi.SPI;
import jp.nyatla.mimic.mbedjs.psgapi.JavaObjectWrapper;

public class SCP1000 extends JavaObjectWrapper<jp.nyatla.mimic.mbedjs.javaapi.driver.SCP1000>
{
	private static jp.nyatla.mimic.mbedjs.javaapi.driver.SCP1000 _new(SPI i_spi,
			int i_cs_pin, int i_drdy_pin)
	{
		try {
			return new jp.nyatla.mimic.mbedjs.javaapi.driver.SCP1000(i_spi,i_cs_pin,i_drdy_pin);
		} catch (MbedJsException e) {
			throw new RuntimeException(e);
		}
	}
	private static jp.nyatla.mimic.mbedjs.javaapi.driver.SCP1000 _new(Mcu i_mcu,
			int i_mosi_pin, int i_miso_pin, int i_sclk_pin, int i_cs_pin, int i_drdy_pin)
	{
		try {
			return new jp.nyatla.mimic.mbedjs.javaapi.driver.SCP1000(i_mcu,
					i_mosi_pin, i_miso_pin, i_sclk_pin, i_cs_pin, i_drdy_pin);
		} catch (MbedJsException e) {
			throw new RuntimeException(e);
		}
	}
	public SCP1000(SPI i_spi,int i_cs_pin, int i_drdy_pin)
	{
		super(_new(i_spi,i_cs_pin,i_drdy_pin));
	}
	public SCP1000(Mcu i_mcu, int i_mosi_pin, int i_miso_pin,
			int i_sclk_pin, int i_cs_pin, int i_drdy_pin)
	{
		super(_new(i_mcu,i_mosi_pin,i_miso_pin,i_sclk_pin,i_cs_pin,i_drdy_pin));
	}
	public void dispose()
	{
		try {
			this._inst.dispose();
		} catch (MbedJsException e) {
			throw new RuntimeException(e);
		}
	}
	public float readPressure()
	{
		try {
			return this._inst.readPressure();
		} catch (MbedJsException e) {
			throw new RuntimeException(e);
		}
	}
	public float readTemperature() 
	{
		try {
			return this._inst.readTemperature();
		} catch (MbedJsException e) {
			throw new RuntimeException(e);
		}
	}
}
