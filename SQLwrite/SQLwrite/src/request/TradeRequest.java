package request;

import java.sql.Date;

import sql_connection.SQLConnection;

/**
 * 交易请求类
 */
public class TradeRequest extends Result
{
	private int sellerID;
	private Date timestamp;
	
	/**
	 * 交易请求类构造函数
	 * @param userID 用户ID
	 * @param sellerID 商家ID
	 * @param requestID 请求ID
	 * @param amount 充值金额
	 * @param state 操作状态
	 * @param timestamp 请求时间，需要的类型：{@link java.sql.Date}
	 * @throws WriteException
	 */
	public TradeRequest(int userID, int sellerID, int requestID, float amount, boolean state, Date timestamp)
	{		
		super(userID, requestID, amount, state);
		
		this.sellerID = sellerID;
		this.timestamp = timestamp;
	}
	
	/**
	 * 尝试发送请求，提交交易内容
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
	 * 返回商家ID
	 * @return sellerID
	 */
	public int getSellerID()
	{
		return sellerID;
	}
	
	/**
	 * 返回请求时间，返回的类型：{@link java.sql.Date}
	 * @return timestamp
	 */
	public Date getTimestamp()
	{
		return timestamp;
	}
}
