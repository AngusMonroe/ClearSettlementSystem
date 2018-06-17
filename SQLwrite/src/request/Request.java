package request;

import exception.RequestException;

/**
 * 请求类
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
	 * 请求类构造函数
	 * @param requestID 请求ID
	 * @param userID 用户ID
	 * @param merchantID 商家ID
	 * @param amount 金额
	 * @param operateStatus 操作状态
	 * @param method 方式:false-微信 或 true-支付宝
	 * @param requestTime 请求时间
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
	 * 返回请求ID
	 * @return requestID
	 */
	public String getRequestID()
	{
		return requestID;
	}
	
	/**
	 * 返回用户ID
	 * @return userID
	 */
	public String getUserID()
	{
		return userID;
	}
	
	/**
	 * 返回商家ID
	 * @return sellerID
	 */
	public String getMerchantID()
	{
		return merchantID;
	}
	
	/**
	 * 返回金额
	 * @return amount
	 */
	public double getAmount()
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
	public String getRequestTime()
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
	
	/**
	 * 显示所有
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
