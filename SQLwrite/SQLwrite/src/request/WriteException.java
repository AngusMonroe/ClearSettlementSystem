package request;

/**
 * 异常信息类
 */
@SuppressWarnings("serial")
public class WriteException extends Exception
{
	/**
	 * 异常信息类构造函数
	 * @param message 异常信息
	 */
	public WriteException(String message)
	{
		super(message);
	}
}
