package exception;

/**
 * �쳣��Ϣ��
 */
@SuppressWarnings("serial")
public final class RequestException extends Exception
{
	/**
	 * �쳣��Ϣ�๹�캯��
	 * @param message �쳣��Ϣ
	 */
	public RequestException(String message)
	{
		super(message);
	}
}
