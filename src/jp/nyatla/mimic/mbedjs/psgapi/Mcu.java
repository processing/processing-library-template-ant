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

import processing.core.PApplet;
import jp.nyatla.mimic.mbedjs.MbedJsException;

/**
 * This is the class that owns the instance of the remote MCU.
 * This class holds a session to mbedJS.
 * This is required when to generate a class of other peripherals.
 */
public class Mcu extends JavaObjectWrapper<jp.nyatla.mimic.mbedjs.javaapi.Mcu>
{
	private static jp.nyatla.mimic.mbedjs.javaapi.Mcu _new(String i_addr){
		try{
			return new jp.nyatla.mimic.mbedjs.javaapi.Mcu(i_addr);
		}catch(MbedJsException e){
			throw new RuntimeException(e);
		}
	}	
	PApplet _parent;
	public Mcu(PApplet i_parent,String i_addr)
	{
		super(_new(i_addr));
		this._parent=i_parent;
		this._parent.registerMethod("dispose", this);
	}
	/**
	 * ラップしているJavaインスタンスを返します。
	 * @return
	 */
	public jp.nyatla.mimic.mbedjs.javaapi.Mcu getBaseInstance()
	{
		return _inst;
	}
	public void dispose()
	{
		this.shutdown();
	}
	public void shutdown()
	{
		if(this._inst!=null){
			this._inst.shutdown();
			this._inst=null;
		}
	}
	/**
	 * The return value object of {@link #getInfo}
	 *
	 */
	public class GetInfoResult{
		public String mcu_eth;
		public String mcu_name;
		public String platform;
		public String version;
		protected GetInfoResult(jp.nyatla.mimic.mbedjs.javaapi.Mcu.GetInfoResult i_src)
		{
			this.mcu_name=i_src.mcu.name;
			this.mcu_eth=i_src.mcu.eth;
			this.platform=i_src.platform;
			this.version=i_src.version;
		}
	}
	public GetInfoResult getInfo()
	{
		try {
			return new GetInfoResult(this._inst.getInfo());
		} catch (MbedJsException e) {
			throw new RuntimeException(e);
		}
	}
}
