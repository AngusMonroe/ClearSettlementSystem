package request;

import exception.RequestException;

/**
 * ����������
 */
public final class WithdrawRequest extends Request
{	
	/**
	 * ���������๹�캯��
	 * @param requestID ����ID
	 * @param userID �û�ID
	 * @param amount ���ֽ��
	 * @param method ��ʽ:false-΢�� �� true-֧����
	 * @param timestamp ����ʱ��
	 * @throws RequestException 
	 */
	public WithdrawRequest(String requestID, String userID, double amount, boolean method, String requestTime) throws RequestException
	{
		super(requestID, userID, "0", amount, false, method, requestTime);
	}
}
