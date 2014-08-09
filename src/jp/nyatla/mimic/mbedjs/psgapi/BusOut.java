/*
 * Copyright 2014 R.Iizuka
 * http://nyatla.jp/mimic/
 * nyatla39@gmail.com
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package jp.nyatla.mimic.mbedjs.psgapi;


import jp.nyatla.mimic.mbedjs.MbedJsException;

/**
 * BusOut Class.
 * This class has the same functions as <a href="https://mbed.org/handbook/BusOut">mbed::BusOut</a>.
 */
public class BusOut extends BasicRemoteInstance<jp.nyatla.mimic.mbedjs.javaapi.BusOut>
{
	private static jp.nyatla.mimic.mbedjs.javaapi.BusOut _new(Mcu i_mcu,int... i_pins){
		try{
			return new jp.nyatla.mimic.mbedjs.javaapi.BusOut(i_mcu._inst,i_pins);
		}catch(MbedJsException e){
			throw new RuntimeException(e);
		}
	}	
	/**
	 * 
	 * @param i_mcu
	 * @param i_pins
	 * Array of pin of MCU. See {@link PinName}
	 * The maximum number of pin is 16.
	 * @throws MbedJsException
	 */
	public BusOut(Mcu i_mcu,int... i_pins)
	{
		super(_new(i_mcu,i_pins));
	}
	public void write(int i_value)
	{
		try {
			this._inst.write(i_value);
		} catch (MbedJsException e) {
			throw new RuntimeException(e);
		}
	}	
	public int read()
	{
		try {
			return this._inst.read();
		} catch (MbedJsException e) {
			throw new RuntimeException(e);
		}
	}
}
