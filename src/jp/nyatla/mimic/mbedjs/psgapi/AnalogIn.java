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
 * AnalogIn Class.
 * This class has the functions as <a href="https://mbed.org/handbook/AnalogIn">mbed::AnalogIn</a>.
 */
public class AnalogIn extends BasicRemoteInstance<jp.nyatla.mimic.mbedjs.javaapi.AnalogIn>
{
	private static jp.nyatla.mimic.mbedjs.javaapi.AnalogIn _new(Mcu i_mcu,int i_pin){
		try{
			return new jp.nyatla.mimic.mbedjs.javaapi.AnalogIn(i_mcu._inst,i_pin);
		}catch(MbedJsException e){
			throw new RuntimeException(e);
		}
	}
	public AnalogIn(Mcu i_mcu,int i_pin)
	{
		super(_new(i_mcu,i_pin));
	}
	public float read()
	{
		try {
			return this._inst.read();
		} catch (MbedJsException e) {
			throw new RuntimeException(e);
		}
	}
	public int read_u16()
	{
		try {
			return this._inst.read_u16();
		} catch (MbedJsException e) {
			throw new RuntimeException(e);
		}
	}
	
}