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
	 * @param state ����״̬
	 * @param timestamp ����ʱ�䣬��Ҫ�����ͣ�{@link java.sql.Date}
	 * @param method ��ʽ:0-΢�� �� 1-֧����
	 * @throws RequestException 
	 */
	public WithdrawRequest(int requestID, int userID, float amount, boolean state, Date timestamp, boolean method) throws RequestException
	{
		super(requestID, userID, 0, amount, state, timestamp, method);
	}
}
