package request;

import java.sql.Date;

import exception.RequestException;

public class Request
{
	protected int requestID;
	protected int userID;
	protected int sellerID;
	protected float amount;
	protected boolean state;
	protected Date timestamp;
	protected String method;
	protected String recordType;

	/**
	 * �����๹�캯��
	 * @param requestID ����ID
	 * @param userID �û�ID
	 * @param sellerID �̼�ID
	 * @param amount ���
	 * @param state ����״̬
	 * @param timestamp ����ʱ�䣬��Ҫ�����ͣ�{@link java.sql.Date}
	 * @param method ��ʽ:"֧����"��"΢��"��null��������recordType
	 * @param recordType ����:"recharge"��"withdraw"��"trade"
	 * @throws RequestException
	 */
	protected Request(int requestID, int userID, int sellerID, float amount, boolean state, Date timestamp, String method, String recordType) throws RequestException
	{
		this.requestID = requestID;
		this.userID = userID;
		this.sellerID = sellerID;
		this.amount = amount;
		this.state = state;
		this.timestamp = timestamp;
		this.method = method;
		this.recordType = recordType;
		
		if (recordType == "recharge" || recordType == "withdraw")
		{
			if (method != "֧����" && method != "΢��") throw new RequestException("��ʽӦΪ\"֧����\"��\"΢��\"");
		}
		else if (recordType == "trade")
		{
			if (method != null) throw new RequestException("��ʽӦΪnull");
		}
		else
		{
			throw new RequestException("����ӦΪ\"recharge\"��\"withdraw\"��\"trade\"");
		}
	}
	
	/**
	 * ��ȡ�����Ϣ
	 * @return ������
	 */
	public Result getResult()
	{
		return new Result(requestID, userID, amount, state);
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
	public String getMethod()
	{
		return method;
	}
	
	/**
	 * ��������
	 * @return recordType
	 */
	public String getRecordType()
	{
		return recordType;
	}
}
