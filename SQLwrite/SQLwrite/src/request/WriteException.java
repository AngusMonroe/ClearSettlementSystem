package request;

/**
 * �쳣��Ϣ��
 */
@SuppressWarnings("serial")
public class WriteException extends Exception
{
	/**
	 * �쳣��Ϣ�๹�캯��
	 * @param message �쳣��Ϣ
	 */
	public WriteException(String message)
	{
		super(message);
	}
}
