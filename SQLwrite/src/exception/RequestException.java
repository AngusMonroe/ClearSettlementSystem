package exception;

/**
 * 异常信息类
 */
@SuppressWarnings("serial")
public final class RequestException extends Exception
{
	/**
	 * 异常信息类构造函数
	 * @param message 异常信息
	 */
	public RequestException(String message)
	{
		super(message);
	}
}
