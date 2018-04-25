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
	 * @param sellerID �̼�ID
	 * @param amount ���׽��
	 * @param state ����״̬
	 * @param timestamp ����ʱ�䣬��Ҫ�����ͣ�{@link java.sql.Date}
	 * @throws RequestException
	 */
	public TradeRequest(int requestID, int userID, int sellerID, float amount, boolean state, Date timestamp) throws RequestException
	{
		super(requestID, userID, sellerID, amount, state, timestamp, null, "trade");
	}
}
