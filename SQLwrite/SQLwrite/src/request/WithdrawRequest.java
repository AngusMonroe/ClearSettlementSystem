package request;

import java.sql.Date;

import sql_connection.SQLConnection;

/**
 * 提现请求类
 */
public final class WithdrawRequest extends Result
{
	private Date timestamp;
	private String method;
	
	/**
	 * 提现请求类构造函数
	 * @param userID 用户ID
	 * @param requestID 请求ID
	 * @param amount 提现金额
	 * @param state 操作状态
	 * @param timestamp 请求时间，需要的类型：{@link java.sql.Date}
	 * @param method 提现方式:"支付宝"或"微信"
	 */
	public WithdrawRequest(int userID, int requestID, float amount, boolean state, Date timestamp, String method)
	{
		super(userID, requestID, amount, state);
		
		this.timestamp = timestamp;
		this.method = method;
	}
	
	/**
	 * 尝试发送请求，提交提现内容
	 * @return 是否成功
	 */
	public boolean sendRequest()
	{
		try
		{
			SQLConnection sqlConnection = new SQLConnection("jdbc:mysql://localhost:3306/bill?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=GMT%2B8", "ruangong", "ruangong");
			sqlConnection.insert(this);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return false;
		}
		enableGetResult();
		return true;
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
	 * 返回提现方式
	 * @return method
	 */
	public String getMethod()
	{
		return method;
	}
}
