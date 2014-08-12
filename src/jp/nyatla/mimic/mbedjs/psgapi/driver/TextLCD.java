package jp.nyatla.mimic.mbedjs.psgapi.driver;

import jp.nyatla.mimic.mbedjs.MbedJsException;
import jp.nyatla.mimic.mbedjs.psgapi.JavaObjectWrapper;
import jp.nyatla.mimic.mbedjs.psgapi.Mcu;
/**
 * TextLCD device driver class.
 * This class has the same functions as <a href="http://mbed.org/users/simon/code/TextLCD/">http://mbed.org/users/simon/code/TextLCD/</a>.
 */
public class TextLCD  extends JavaObjectWrapper<jp.nyatla.mimic.mbedjs.javaapi.driver.TextLCD>
{
	public final static int LCD16x2	=jp.nyatla.mimic.mbedjs.javaapi.driver.TextLCD.LCD16x2;
	public final static int LCD16x2B=jp.nyatla.mimic.mbedjs.javaapi.driver.TextLCD.LCD16x2B;
	public final static int LCD20x2	=jp.nyatla.mimic.mbedjs.javaapi.driver.TextLCD.LCD20x2;
	public final static int LCD20x4	=jp.nyatla.mimic.mbedjs.javaapi.driver.TextLCD.LCD20x4;	
	private static jp.nyatla.mimic.mbedjs.javaapi.driver.TextLCD _new(Mcu i_mcu , int i_rs_pin, int i_ee_pin,
			int i_d0_pin, int i_d1_pin, int i_d2_pin, int i_d3_pin,int i_lcd_type){
		try{
			return new jp.nyatla.mimic.mbedjs.javaapi.driver.TextLCD(i_mcu.getSuperInstance() ,  i_rs_pin,  i_ee_pin,
					 i_d0_pin,  i_d1_pin,  i_d2_pin,  i_d3_pin, i_lcd_type);
		}catch(MbedJsException e){
			throw new RuntimeException(e);
		}
	}	
	/**
	 * A TextLCD interface for driving 4-bit HD44780-based LCDs
	 * LCDインスタンスを生成します。
	 * @param i_mcu
	 * @param i_rs_pin
	 * Instruction/data control line
	 * @param i_ee_pin
	 * Enable line (clock)
	 * @param i_d0_pin
	 * Data lines for using as a 4-bit interface(0)
	 * @param i_d1_pin
	 * Data lines for using as a 4-bit interface(1)
	 * @param i_d2_pin
	 * Data lines for using as a 4-bit interface(2)
	 * @param i_d3_pin
	 * Data lines for using as a 4-bit interface(3)
	 * @param i_lcd_type LCD
	 */
	public TextLCD(Mcu i_mcu , int i_rs_pin, int i_ee_pin,int i_d0_pin, int i_d1_pin, int i_d2_pin, int i_d3_pin,int i_lcd_type)
	{
		super(_new(i_mcu ,  i_rs_pin,  i_ee_pin, i_d0_pin,  i_d1_pin,  i_d2_pin,  i_d3_pin, i_lcd_type));
	}
	public int columns()
	{
		return this._inst.columns();
	}
	public void cls()
	{
		try {
			this._inst.cls();
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
	public void locate(int i_column,int i_row)
	{
		this._inst.locate(i_column, i_row);
	}
	public int putc(int i_ch)
	{
		try {
			return this._inst.putc(i_ch);
		} catch (MbedJsException e) {
			throw new RuntimeException(e);
		}
	}
	public int puts(String i_s)
	{
		try {
			return this._inst.puts(i_s);
		} catch (MbedJsException e) {
			throw new RuntimeException(e);
		}
		
	}
	public int rows(){
		return this._inst.rows();
	}
}
