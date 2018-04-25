package request;

import java.sql.Date;

import exception.RequestException;

/**
 * 交易请求类
 */
public final class TradeRequest extends Request
{	
	/**
	 * 交易请求类构造函数
	 * @param requestID 请求ID
	 * @param userID 用户ID
	 * @param sellerID 商家ID
	 * @param amount 交易金额
	 * @param state 操作状态
	 * @param timestamp 请求时间，需要的类型：{@link java.sql.Date}
	 * @throws RequestException
	 */
	public TradeRequest(int requestID, int userID, int sellerID, float amount, boolean state, Date timestamp) throws RequestException
	{
		super(requestID, userID, sellerID, amount, state, timestamp, null, "trade");
	}
}
