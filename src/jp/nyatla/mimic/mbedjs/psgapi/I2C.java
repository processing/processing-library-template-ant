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
 * I2C Class.
 * This class has the same functions as <a href="https://mbed.org/handbook/I2C">mbed::I2C</a>.
 */
public class I2C extends BasicRemoteInstance<jp.nyatla.mimic.mbedjs.javaapi.I2C>
{
	private static jp.nyatla.mimic.mbedjs.javaapi.I2C _new(Mcu i_mcu,int i_sda_pin,int i_scl_pin){
		try{
			return new jp.nyatla.mimic.mbedjs.javaapi.I2C(i_mcu._inst,i_sda_pin,i_scl_pin);
		}catch(MbedJsException e){
			throw new RuntimeException(e);
		}
	}
	public I2C(Mcu i_mcu,int i_sda_pin,int i_scl_pin){
		super(_new(i_mcu,i_sda_pin,i_scl_pin));
	}
	public void frequency(int i_hz){
		try {
			this._inst.frequency(i_hz);
		} catch (MbedJsException e) {
			throw new RuntimeException(e);
		}
	}
	public static class ReadResult{
		public final int ack;
		public final byte[] data;
		protected ReadResult(jp.nyatla.mimic.mbedjs.javaapi.I2C.ReadResult i_src){
			this.ack=i_src.ack;
			this.data=i_src.data;
		}
	}	
	public ReadResult read(int i_address,int i_length,boolean i_repeated){
		try {
			return new ReadResult(this._inst.read(i_address, i_length, i_repeated));
		} catch (MbedJsException e) {
			throw new RuntimeException(e);
		}
	}
	public int read(int i_ack)
	{
		try {
			return this._inst.read(i_ack);
		} catch (MbedJsException e) {
			throw new RuntimeException(e);
		}
	}
	public int write(int i_address,byte[] i_data,boolean i_repeated){
		try {
			return this._inst.write(i_address, i_data, i_repeated);
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
	public void start()
	{
		try {
			this._inst.start();
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
}
