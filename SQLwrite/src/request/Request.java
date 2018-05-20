package request;

import java.sql.Date;

import exception.RequestException;

/**
 * 请求类
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
	 * 请求类构造函数
	 * @param requestID 请求ID
	 * @param userID 用户ID
	 * @param merchantID 商家ID
	 * @param amount 金额
	 * @param operateStatus 操作状态
	 * @param requestStatus 请求状态
	 * @param method 方式:false-微信 或 true-支付宝
	 * @param requestTime 请求时间，需要的类型：{@link java.sql.Date}
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
	public int getMerchantID()
	{
		return merchantID;
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
	public boolean getOperateStatus()
	{
		return operateStatus;
	}
	
	/**
	 * 返回请求状态
	 * @return state
	 */
	public boolean getRequestStatus()
	{
		return requestStatus;
	}
	
	/**
	 * 返回充值方式
	 * @return method
	 */
	public boolean getMethod()
	{
		return method;
	}
	
	/**
	 * 返回请求时间，返回的类型：{@link java.sql.Date}
	 * @return timestamp
	 */
	public Date getRequestTime()
	{
		return requestTime;
	}
	
	/**
	 * 设置操作状态
	 * @param operateStatus 操作状态
	 */
	public void setOperateStatus(boolean operateStatus)
	{
		this.operateStatus = operateStatus;
	}
}
