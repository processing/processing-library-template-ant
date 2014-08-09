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
 * This class is Memory reader/writer.
 * It can read/writes memory on remote mcu directly.
 */
public class Memory extends JavaObjectWrapper<jp.nyatla.mimic.mbedjs.javaapi.Memory>
{
	public Memory(Mcu i_mcu){
		super(new jp.nyatla.mimic.mbedjs.javaapi.Memory(i_mcu._inst));
	}
	/**
	 * This function reads byte(s) from the remote mcu memory.
	 * @param i_addr
	 * Address of source on remote mcu.
	 * @param i_size
	 * Size in byte to read.
	 * @return
	 * Array of read data.
	 * @throws MbedJsException
	 */
	public byte[] read(int i_addr,int i_size)
	{
		try {
			return this._inst.read(i_addr, i_size);
		} catch (MbedJsException e) {
			throw new RuntimeException(e);
		}
	}
	/**
	 * This function writes byte(s) to the remote mcu memory.
	 * @param i_addr
	 * Address of destination on remote mcu.
	 * @param i_data
	 * Array of write data.
	 * @throws MbedJsException
	 */
	public void write(int i_addr,byte[] i_data)
	{
		try {
			this._inst.write(i_addr, i_data);
		} catch (MbedJsException e) {
			throw new RuntimeException(e);
		}
	}
	/**
	 * This function reads dword(s) from the remote mcu memory.
	 * @param i_addr
	 * Address of source on remote mcu.
	 * Must be a 4-byte units.
	 * @param i_size
	 * Size in byte to read.
	 * Must be a 4-byte units.
	 * @return
	 * Array of read data.
	 * @throws MbedJsException
	 */
	public int[] read32(int i_addr,int i_size)
	{
		try {
			return this._inst.read32(i_addr, i_size);
		} catch (MbedJsException e) {
			throw new RuntimeException(e);
		}
	}
	/**
	 * This function writes dword(s) to the remote mcu memory.
	 * @param i_addr
	 * Address of destination on remote mcu.
	 * Must be a 4-byte units.
	 * @param i_data
	 * Array of write data.
	 * Must be a 4-byte units.
	 * @throws MbedJsException
	 */
	public void write32(int i_addr,int[] i_data)
	{
		try {
			this._inst.write32(i_addr, i_data);
		} catch (MbedJsException e) {
			throw new RuntimeException(e);
		}
	}


}
