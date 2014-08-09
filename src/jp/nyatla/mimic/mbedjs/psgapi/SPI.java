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
 * SPI Class.
 * This class has the same functions as <a href="https://mbed.org/handbook/SPI">mbed::SPI</a>.
 */
public class SPI extends BasicRemoteInstance<jp.nyatla.mimic.mbedjs.javaapi.SPI>
{
	private static jp.nyatla.mimic.mbedjs.javaapi.SPI _new(Mcu i_mcu,int i_mosi_pin,int i_miso_pin,int i_sclk_pin){
		try{
			return new jp.nyatla.mimic.mbedjs.javaapi.SPI(i_mcu._inst,i_mosi_pin,i_miso_pin,i_sclk_pin);
		}catch(MbedJsException e){
			throw new RuntimeException(e);
		}
	}	
	public SPI(Mcu i_mcu,int i_mosi_pin,int i_miso_pin,int i_sclk_pin){
		super(_new(i_mcu,i_mosi_pin,i_miso_pin,i_sclk_pin));
	}
	public int write(int i_value){
		try {
			return this._inst.write(i_value);
		} catch (MbedJsException e) {
			throw new RuntimeException(e);
		}
	}	
	public void frequency(int i_value){
		try {
			this._inst.frequency(i_value);
		} catch (MbedJsException e) {
			throw new RuntimeException(e);
		}
	}
	public void format(int i_bits,int i_mode){
		try {
			this._inst.format(i_bits, i_mode);
		} catch (MbedJsException e) {
			throw new RuntimeException(e);
		}
	}
}
