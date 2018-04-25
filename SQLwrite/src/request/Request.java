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
	 * 请求类构造函数
	 * @param requestID 请求ID
	 * @param userID 用户ID
	 * @param sellerID 商家ID
	 * @param amount 金额
	 * @param state 操作状态
	 * @param timestamp 请求时间，需要的类型：{@link java.sql.Date}
	 * @param method 方式:"支付宝"或"微信"或null，依赖于recordType
	 * @param recordType 类型:"recharge"或"withdraw"或"trade"
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
			if (method != "支付宝" && method != "微信") throw new RequestException("方式应为\"支付宝\"或\"微信\"");
		}
		else if (recordType == "trade")
		{
			if (method != null) throw new RequestException("方式应为null");
		}
		else
		{
			throw new RequestException("类型应为\"recharge\"或\"withdraw\"或\"trade\"");
		}
	}
	
	/**
	 * 获取结果信息
	 * @return 请求结果
	 */
	public Result getResult()
	{
		return new Result(requestID, userID, amount, state);
	}
	
	/**
	 * 返回请求ID
	 * @return requestID
	 */
	public int getRequestID()
	{
		return requestID;
	}
	
	/**
	 * 返回用户ID
	 * @return userID
	 */
	public int getUserID()
	{
		return userID;
	}
	
	/**
	 * 返回商家ID
	 * @return sellerID
	 */
	public int getSellerID()
	{
		return sellerID;
	}
	
	/**
	 * 返回金额
	 * @return amount
	 */
	public float getAmount()
	{
		return amount;
	}
	
	/**
	 * 返回操作状态
	 * @return state
	 */
	public boolean getState()
	{
		return state;
	}
	
	/**
	 * 返回请求时间，返回的类型：{@link java.sql.Date}
	 * @return timestamp
	 */
	public Date getTimestamp()
	{
		return timestamp;
	}
	
	/**
	 * 返回充值方式
	 * @return method
	 */
	public String getMethod()
	{
		return method;
	}
	
	/**
	 * 返回类型
	 * @return recordType
	 */
	public String getRecordType()
	{
		return recordType;
	}
}
