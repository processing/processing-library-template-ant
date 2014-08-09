package jp.nyatla.mimic.mbedjs.psgapi;

import jp.nyatla.mimic.mbedjs.MbedJsException;


/**
 * The base class of the class that is synchronized with the remote instance.
 */
public abstract class BasicRemoteInstance<T extends jp.nyatla.mimic.mbedjs.McuBindClass> extends JavaObjectWrapper<T>
{
	protected BasicRemoteInstance(T i_inst) {
		super(i_inst);
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
