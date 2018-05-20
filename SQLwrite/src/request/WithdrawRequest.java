package request;

import java.sql.Date;

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
	 * @param requestStatus ����״̬
	 * @param method ��ʽ:false-΢�� �� true-֧����
	 * @param timestamp ����ʱ�䣬��Ҫ�����ͣ�{@link java.sql.Date}
	 * @throws RequestException 
	 */
	public WithdrawRequest(int requestID, int userID, float amount, boolean requestStatus, boolean method, Date requestTime) throws RequestException
	{
		super(requestID, userID, 0, amount, false, requestStatus, method, requestTime);
	}
}
