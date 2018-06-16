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
	public TradeRequest(String requestID, String userID, String merchantID, double amount, boolean operateStatus, String requestTime) throws RequestException
	{
		super(requestID, userID, merchantID, amount, operateStatus, false, requestTime);
	}
	
	public void display()
	{
		System.out.print
		(
			"Trade Request: \n" +
			"Request ID: " + requestID + "\n" + 
			"User ID: " + userID + "\n" +
			"Merchant ID: " + merchantID + "\n" +
			"Amount: " + amount + "\n" +
			"Operate Status: " + operateStatus + "\n" +
			"Request Time: " + requestTime + "\n"
		);
	}
}
