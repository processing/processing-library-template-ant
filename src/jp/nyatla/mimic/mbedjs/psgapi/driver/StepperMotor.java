package jp.nyatla.mimic.mbedjs.psgapi.driver;

import jp.nyatla.mimic.mbedjs.MbedJsException;
import jp.nyatla.mimic.mbedjs.javaapi.Mcu;
import jp.nyatla.mimic.mbedjs.psgapi.JavaObjectWrapper;

public class StepperMotor extends JavaObjectWrapper<jp.nyatla.mimic.mbedjs.javaapi.driver.StepperMotor>
{
	private static jp.nyatla.mimic.mbedjs.javaapi.driver.StepperMotor _new (Mcu i_mcu,int i_ctrlA,int i_ctrlB ,
			int i_in1,int i_in2,int i_in3, int i_in4)
	{
		try {
			return new jp.nyatla.mimic.mbedjs.javaapi.driver.StepperMotor(i_mcu,i_ctrlA,i_ctrlB ,
				i_in1,i_in2,i_in3, i_in4);
		} catch (MbedJsException e) {
			throw new RuntimeException(e);
		}
		
	}
	public StepperMotor(Mcu i_mcu,int i_ctrlA,int i_ctrlB ,
			int i_in1,int i_in2,int i_in3, int i_in4)
	{
		super(_new(i_mcu,i_ctrlA,i_ctrlB ,
				i_in1,i_in2,i_in3, i_in4));
	}
	public void dispose()
	{
		try {
			this._inst.dispose();
		} catch (MbedJsException e) {
			throw new RuntimeException(e);
		}
	}
	public void rotate(int i_step , int i_wait_ms)
	{
		try {
			this._inst.rotate(i_step, i_wait_ms);
		} catch (MbedJsException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void close()
	{
		try {
			this._inst.close();
		} catch (MbedJsException e) {
			throw new RuntimeException(e);
		}
	}
	
}
