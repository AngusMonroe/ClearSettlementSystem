package request;

import java.sql.Date;

import exception.RequestException;

/**
 * 提现请求类
 */
public final class WithdrawRequest extends Request
{	
	/**
	 * 提现请求类构造函数
	 * @param requestID 请求ID
	 * @param userID 用户ID
	 * @param amount 提现金额
	 * @param state 操作状态
	 * @param timestamp 请求时间，需要的类型：{@link java.sql.Date}
	 * @param method 提现方式:"支付宝"或"微信"
	 * @throws RequestException 
	 */
	public WithdrawRequest(int requestID, int userID, float amount, boolean state, Date timestamp, String method) throws RequestException
	{
		super(requestID, userID, 0, amount, state, timestamp, method, RecordType.WITHDRAW);
	}
}
