package jp.nyatla.mimic.mbedjs.psgapi;

import jp.nyatla.mimic.mbedjs.MbedJsException;


/**
 * The base class of the class that is synchronized with the remote instance.
 */
public abstract class BasicRemoteInstance<T extends jp.nyatla.mimic.mbedjs.McuBindClass>
{
	protected T _inst;
	protected BasicRemoteInstance()
	{
	}
	/**
	 * This function deletes remote instance from remote mcu.
	 */
	public boolean dispose()
	{
		try {
			return this._inst.dispose();
		} catch (MbedJsException e) {
			throw new RuntimeException(e);
		}
	}

}
