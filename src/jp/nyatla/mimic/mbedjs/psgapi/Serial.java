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
 * Serial Class.
 * This class has the same functions as <a href="https://mbed.org/handbook/Serial">mbed::Serial</a>.
 */
public class Serial extends BasicRemoteInstance<jp.nyatla.mimic.mbedjs.javaapi.Serial>
{
	public final static int None=jp.nyatla.mimic.mbedjs.javaapi.Serial.None;
	public final static int Odd=jp.nyatla.mimic.mbedjs.javaapi.Serial.Odd;
	public final static int Even=jp.nyatla.mimic.mbedjs.javaapi.Serial.Even;
	public final static int Forced1=jp.nyatla.mimic.mbedjs.javaapi.Serial.Forced1;
	public final static int Forced0=jp.nyatla.mimic.mbedjs.javaapi.Serial.Forced0;
	private static jp.nyatla.mimic.mbedjs.javaapi.Serial _new(Mcu i_mcu,int i_tx_pin,int i_rx_pin){
		try{
			return new jp.nyatla.mimic.mbedjs.javaapi.Serial(i_mcu._inst,i_tx_pin,i_rx_pin);
		}catch(MbedJsException e){
			throw new RuntimeException(e);
		}
	}
	public Serial(Mcu i_mcu,int i_tx_pin,int i_rx_pin){
		super(_new(i_mcu,i_tx_pin,i_rx_pin));
	}
	/**
	 * This function set format parameter.
	 * @param {int} i_bits
	 * Bits value. default is 8.
	 * @param {int} i_parity
	 * Parity value.
	 * {@link #None},{@link #Odd},{@link #Even},{@link #Forced1},{@link #Forced0} can be specified.
	 * Default value is {@link #None}.
	 * @param {int} i_stop_bits
	 * Stop bit value.
	 * Default value is 1
	 */
	public void format(int i_bits,int i_parity,int i_stop_bit){
		try {
			this._inst.format(i_bits, i_parity, i_stop_bit);
		} catch (MbedJsException e) {
			throw new RuntimeException(e);
		}
	}

	public void format(int i_bits,int i_parity){
		this.format(i_bits, i_parity,1);
	}
	public void format(int i_bits){
		this.format(i_bits,None,1);
	}
	public void format(){
		this.format(8,None,1);
	}
	public boolean readable(){
		try {
			return this._inst.readable();
		} catch (MbedJsException e) {
			throw new RuntimeException(e);
		}
	}
	public boolean writeable(){
		try {
			return this._inst.writeable();
		} catch (MbedJsException e) {
			throw new RuntimeException(e);
		}
	}
	public void send_break(){
		try {
			this._inst.send_break();
		} catch (MbedJsException e) {
			throw new RuntimeException(e);
		}
	}
	public int putc(int i_c){
		try {
			return this._inst.putc(i_c);
		} catch (MbedJsException e) {
			throw new RuntimeException(e);
		}
	}
	public int puts(String i_s){
		try {
			return this._inst.puts(i_s);
		} catch (MbedJsException e) {
			throw new RuntimeException(e);
		}
	}
	/**
	 * シリアルポートへバイト配列を出力します。
	 * @param i_data
	 * @return
	 */
	public int puts(byte[] i_data){
		try {
			return this._inst.puts(i_data);
		} catch (MbedJsException e) {
			throw new RuntimeException(e);
		}
	}
	
	public int getc(){
		try {
			return this._inst.getc();
		} catch (MbedJsException e) {
			throw new RuntimeException(e);
		}
	}
	/**
	 * ASCII文字列をシリアルポートから取得します。
	 * {@link #gets(int, char)}の'a'指定と同じです。
	 * @param i_len
	 * 取得する文字列長さ又はバイト単位のデータ長
	 * @return
	 */
	public String gets(int i_len){
		return (String)this.gets(i_len,'a');
	}
	/**
	 * ASCII文字列またはバイナリデータ列をシリアルポートから取得します。
	 * @param i_len
	 * 取得する文字列長さ又はバイト単位のデータ長
	 * @param i_mode
	 * 'a'=ASCII,'b'=バイナリ
	 * @return
	 * <ul>
	 * <li>'a' - String
	 * <li>'b' - byte[]
	 * </ul>
	 */
	public Object gets(int i_len,char i_mode){
		try {
			return this._inst.gets(i_len,i_mode);
		} catch (MbedJsException e) {
			throw new RuntimeException(e);
		}
	}	
	public void baud(int i_baudrate)
	{
		try {
			this._inst.baud(i_baudrate);
		} catch (MbedJsException e) {
			throw new RuntimeException(e);
		}
	}
}
