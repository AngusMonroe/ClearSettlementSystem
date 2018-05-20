package request;

import java.sql.Date;

import exception.RequestException;

/**
 * ������
 */
public class Request
{	
	protected int requestID;
	protected int userID;
	protected int merchantID;
	protected float amount;
	protected boolean operateStatus;
	protected boolean requestStatus;
	protected boolean method;
	protected Date requestTime;

	/**
	 * �����๹�캯��
	 * @param requestID ����ID
	 * @param userID �û�ID
	 * @param merchantID �̼�ID
	 * @param amount ���
	 * @param operateStatus ����״̬
	 * @param requestStatus ����״̬
	 * @param method ��ʽ:false-΢�� �� true-֧����
	 * @param requestTime ����ʱ�䣬��Ҫ�����ͣ�{@link java.sql.Date}
	 * @throws RequestException
	 */
	protected Request(int requestID, int userID, int merchantID, float amount, boolean operateStatus, boolean requestStatus, boolean method, Date requestTime) throws RequestException
	{
		this.requestID = requestID;
		this.userID = userID;
		this.merchantID = merchantID;
		this.amount = amount;
		this.operateStatus = operateStatus;
		this.requestStatus = requestStatus;
		this.method = method;
		this.requestTime = requestTime;
	}
	
	/**
	 * ��������ID
	 * @return requestID
	 */
	public int getRequestID()
	{
		return requestID;
	}
	
	/**
	 * �����û�ID
	 * @return userID
	 */
	public int getUserID()
	{
		return userID;
	}
	
	/**
	 * �����̼�ID
	 * @return sellerID
	 */
	public int getMerchantID()
	{
		return merchantID;
	}
	
	/**
	 * ���ؽ��
	 * @return amount
	 */
	public float getAmount()
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
	 * ��������״̬
	 * @return state
	 */
	public boolean getRequestStatus()
	{
		return requestStatus;
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
	public Date getRequestTime()
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
}
