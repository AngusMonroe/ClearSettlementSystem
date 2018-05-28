package request;

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
	 * @param operateStatus ����״̬
	 * @param requestTime ����ʱ��
	 * @throws RequestException
	 */
	public TradeRequest(String requestID, String userID, String merchantID, float amount, boolean operateStatus, String requestTime) throws RequestException
	{
		super(requestID, userID, merchantID, amount, operateStatus, false, requestTime);
	}
}
