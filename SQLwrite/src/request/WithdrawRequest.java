package request;

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
	 * @param method 方式:false-微信 或 true-支付宝
	 * @param timestamp 请求时间
	 * @throws RequestException 
	 */
	public WithdrawRequest(String requestID, String userID, double amount, boolean method, String requestTime) throws RequestException
	{
		super(requestID, userID, "0", amount, false, method, requestTime);
	}
}
