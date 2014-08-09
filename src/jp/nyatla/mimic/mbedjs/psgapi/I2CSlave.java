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
 * I2CSlave Class.
 * This class has the same functions as <a href="https://mbed.org/handbook/I2CSlave">mbed::I2CSlave</a>.
 */
public class I2CSlave extends BasicRemoteInstance<jp.nyatla.mimic.mbedjs.javaapi.I2CSlave>
{
	public final static int NoData=jp.nyatla.mimic.mbedjs.javaapi.I2CSlave.NoData;
	public final static int ReadAddressed=jp.nyatla.mimic.mbedjs.javaapi.I2CSlave.ReadAddressed;
	public final static int WriteGeneral=jp.nyatla.mimic.mbedjs.javaapi.I2CSlave.WriteGeneral;
	public final static int WriteAddressed=jp.nyatla.mimic.mbedjs.javaapi.I2CSlave.WriteAddressed;
	private static jp.nyatla.mimic.mbedjs.javaapi.I2CSlave _new(Mcu i_mcu,int i_sda_pin,int i_scl_pin){
		try{
			return new jp.nyatla.mimic.mbedjs.javaapi.I2CSlave(i_mcu._inst,i_sda_pin,i_scl_pin);
		}catch(MbedJsException e){
			throw new RuntimeException(e);
		}
	}
	public I2CSlave(Mcu i_mcu,int i_sda_pin,int i_scl_pin){
		super(_new(i_mcu,i_sda_pin,i_scl_pin));
	}
	public void frequency(int i_hz){
		try {
			this._inst.frequency(i_hz);
		} catch (MbedJsException e) {
			throw new RuntimeException(e);
		}
	}
	public void address(int i_value){
		try {
			this._inst.address(i_value);
		} catch (MbedJsException e) {
			throw new RuntimeException(e);
		}
	}	
	public I2C.ReadResult read(int i_length){
		try {
			return new I2C.ReadResult(this._inst.read(i_length));
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
	public int write(byte[] i_data){
		try {
			return this._inst.write(i_data);
		} catch (MbedJsException e) {
			throw new RuntimeException(e);
		}
	}
	public int write(int i_ack)
	{
		try {
			return this._inst.write(i_ack);
		} catch (MbedJsException e) {
			throw new RuntimeException(e);
		}
	}

	public void stop()
	{
		try {
			this._inst.stop();
		} catch (MbedJsException e) {
			throw new RuntimeException(e);
		}
	}

	public int receive()
	{
		try {
			return this._inst.receive();
		} catch (MbedJsException e) {
			throw new RuntimeException(e);
		}
	}

}