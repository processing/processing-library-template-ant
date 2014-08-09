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
 * PwmOut Class.
 * This class has the same functions as <a href="https://mbed.org/handbook/PwmOut">mbed::PwmOut</a>.
 */
public class PwmOut extends BasicRemoteInstance<jp.nyatla.mimic.mbedjs.javaapi.PwmOut>
{
	private static jp.nyatla.mimic.mbedjs.javaapi.PwmOut _new(Mcu i_mcu,int i_pin){
		try{
			return new jp.nyatla.mimic.mbedjs.javaapi.PwmOut(i_mcu._inst,i_pin);
		}catch(MbedJsException e){
			throw new RuntimeException(e);
		}
	}		
	public PwmOut(Mcu i_mcu,int i_pin){
		super(_new(i_mcu,i_pin));
	}
	public void write(float i_value)
	{
		try {
			this._inst.write(i_value);
		} catch (MbedJsException e) {
			throw new RuntimeException(e);
		}
	}
	public float read()
	{
		try {
			return this._inst.read();
		} catch (MbedJsException e) {
			throw new RuntimeException(e);
		}
	}
	public void period(float i_value)
	{
		try {
			this._inst.period(i_value);
		} catch (MbedJsException e) {
			throw new RuntimeException(e);
		}
	}
	public void period_ms(int i_value)
	{
		try {
			this._inst.period_ms(i_value);
		} catch (MbedJsException e) {
			throw new RuntimeException(e);
		}
	}
	public void period_us(int i_value)
	{
		try {
			this._inst.period_us(i_value);
		} catch (MbedJsException e) {
			throw new RuntimeException(e);
		}
	}
	public void pulsewidth(float i_value)
	{
		try {
			this._inst.pulsewidth(i_value);
		} catch (MbedJsException e) {
			throw new RuntimeException(e);
		}
	}
	public void pulsewidth_ms(int i_value)
	{
		try {
			this._inst.pulsewidth_ms(i_value);
		} catch (MbedJsException e) {
			throw new RuntimeException(e);
		}
	}
	public void pulsewidth_us(int i_value)
	{
		try {
			this._inst.pulsewidth_us(i_value);
		} catch (MbedJsException e) {
			throw new RuntimeException(e);
		}
	}
}
