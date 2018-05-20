package request;

import java.sql.Date;

import exception.RequestException;

/**
 * ����������
 */
public final class TradeRequest extends Request
{	
	/**
	 * ���������๹�캯��
	 * @param requestID ����ID
	 * @param userID �û�ID
	 * @param merchantID �̼�ID
	 * @param amount ���׽��
	 * @param requestStatus ����״̬
	 * @param requestTime ����ʱ�䣬��Ҫ�����ͣ�{@link java.sql.Date}
	 * @throws RequestException
	 */
	public TradeRequest(int requestID, int userID, int merchantID, float amount, boolean requestStatus, Date requestTime) throws RequestException
	{
		super(requestID, userID, merchantID, amount, false, requestStatus, false, requestTime);
	}
}
