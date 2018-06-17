package request;

import exception.RequestException;

/**
 * ������
 */
public class Request
{	
	protected String requestID;
	protected String userID;
	protected String merchantID;
	protected double amount;
	protected boolean operateStatus;
	protected boolean method;
	protected String requestTime;

	/**
	 * �����๹�캯��
	 * @param requestID ����ID
	 * @param userID �û�ID
	 * @param merchantID �̼�ID
	 * @param amount ���
	 * @param operateStatus ����״̬
	 * @param method ��ʽ:false-΢�� �� true-֧����
	 * @param requestTime ����ʱ��
	 * @throws RequestException
	 */
	protected Request(String requestID, String userID, String merchantID, double amount, boolean operateStatus, boolean method, String requestTime) throws RequestException
	{
		this.requestID = requestID;
		this.userID = userID;
		this.merchantID = merchantID;
		this.amount = amount;
		this.operateStatus = operateStatus;
		this.method = method;
		this.requestTime = requestTime;
	}
	
	/**
	 * ��������ID
	 * @return requestID
	 */
	public String getRequestID()
	{
		return requestID;
	}
	
	/**
	 * �����û�ID
	 * @return userID
	 */
	public String getUserID()
	{
		return userID;
	}
	
	/**
	 * �����̼�ID
	 * @return sellerID
	 */
	public String getMerchantID()
	{
		return merchantID;
	}
	
	/**
	 * ���ؽ��
	 * @return amount
	 */
	public double getAmount()
	{
		return amount;
	}
	
	/**
	 * ���ز���״̬
	 * @return state
	 */
	public boolean getOperateStatus()
	{
		return operateStatus;
	}
	
	/**
	 * ���س�ֵ��ʽ
	 * @return method
	 */
	public boolean getMethod()
	{
		return method;
	}
	
	/**
	 * ��������ʱ�䣬���ص����ͣ�{@link java.sql.Date}
	 * @return timestamp
	 */
	public String getRequestTime()
	{
		return requestTime;
	}
	
	/**
	 * ���ò���״̬
	 * @param operateStatus ����״̬
	 */
	public void setOperateStatus(boolean operateStatus)
	{
		this.operateStatus = operateStatus;
	}
	
	/**
	 * ��ʾ����
	 */
	public void display()
	{
		System.out.print
		(
			"Request: \n" +
			"Request ID: " + requestID + "\n" + 
			"User ID: " + userID + "\n" +
			"Merchant ID: " + merchantID + "\n" +
			"Amount: " + amount + "\n" +
			"Operate Status: " + operateStatus + "\n" +
			"Method: " + (method == true ? "Alipay" : "WeChat") + "\n" +
			"Request Time: " + requestTime + "\n"
		);
	}
}
