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
	protected int sellerID;
	protected float amount;
	protected boolean state;
	protected Date timestamp;
	protected boolean method;

	/**
	 * �����๹�캯��
	 * @param requestID ����ID
	 * @param userID �û�ID
	 * @param sellerID �̼�ID
	 * @param amount ���
	 * @param state ����״̬
	 * @param timestamp ����ʱ�䣬��Ҫ�����ͣ�{@link java.sql.Date}
	 * @param method ��ʽ:false-΢�� �� true-֧����
	 * @throws RequestException
	 */
	protected Request(int requestID, int userID, int sellerID, float amount, boolean state, Date timestamp, boolean method) throws RequestException
	{
		this.requestID = requestID;
		this.userID = userID;
		this.sellerID = sellerID;
		this.amount = amount;
		this.state = state;
		this.timestamp = timestamp;
		this.method = method;
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
	public int getSellerID()
	{
		return sellerID;
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
	public boolean getState()
	{
		return state;
	}
	
	/**
	 * ��������ʱ�䣬���ص����ͣ�{@link java.sql.Date}
	 * @return timestamp
	 */
	public Date getTimestamp()
	{
		return timestamp;
	}
	
	/**
	 * ���س�ֵ��ʽ
	 * @return method
	 */
	public boolean getMethod()
	{
		return method;
	}
}
