package request;

import exception.RequestException;

/**
 * ��ֵ������
 */
public final class RechargeRequest extends Request
{	
	/**
	 * ��ֵ�����๹�캯��
	 * @param requestID ����ID
	 * @param userID �û�ID
	 * @param amount ��ֵ���
	 * @param method ��ʽ:false-΢�� �� true-֧����
	 * @param requestTime ����ʱ��
	 * @throws RequestException
	 */
	public RechargeRequest(String requestID, String userID, double amount, boolean method, String requestTime) throws RequestException
	{
		super(requestID, userID, "0", amount, false, method, requestTime);
	}
	
	public void display()
	{
		System.out.print
		(
			"Recharge Request: \n" +
			"Request ID: " + requestID + "\n" + 
			"User ID: " + userID + "\n" +
			"Amount: " + amount + "\n" +
			"Method: " + (method == true ? "Alipay" : "WeChat") + "\n" +
			"Request Time: " + requestTime + "\n"
		);
	}
}
