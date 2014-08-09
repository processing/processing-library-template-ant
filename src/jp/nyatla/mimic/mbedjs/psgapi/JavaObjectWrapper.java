package jp.nyatla.mimic.mbedjs.psgapi;

/**
 * Base class of java object wrapper
 * @param <T>
 */
public abstract class JavaObjectWrapper<T>
{
	protected T _inst;
	public T getSuperInstance()
	{
		return this._inst;
	}
	protected JavaObjectWrapper(T i_inst)
	{
		this._inst=i_inst;
	}
}
