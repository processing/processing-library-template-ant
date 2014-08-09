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
 * DigitalIn Class.
 * This class has the same functions as <a href="https://mbed.org/handbook/DigitalIn">mbed::DigitalIn</a>.
 */
public class DigitalIn extends BasicRemoteInstance<jp.nyatla.mimic.mbedjs.javaapi.DigitalIn>
{
	private static jp.nyatla.mimic.mbedjs.javaapi.DigitalIn _new(Mcu i_mcu,int i_pin){
		try{
			return new jp.nyatla.mimic.mbedjs.javaapi.DigitalIn(i_mcu._inst,i_pin);
		}catch(MbedJsException e){
			throw new RuntimeException(e);
		}
	}	
	public DigitalIn(Mcu i_mcu,int i_pin){
		super(_new(i_mcu,i_pin));
	}
	public int read(){
		try {
			return this._inst.read();
		} catch (MbedJsException e) {
			throw new RuntimeException(e);
		}
	}
	public void mode(int i_pin_mode){
		try {
			this._inst.mode(i_pin_mode);
		} catch (MbedJsException e) {
			throw new RuntimeException(e);
		}
	}
}
