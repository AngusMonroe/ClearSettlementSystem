package request;

import java.sql.Date;

import exception.RequestException;

/**
 * 充值请求类
 */
public final class RechargeRequest extends Request
{	
	/**
	 * 充值请求类构造函数
	 * @param requestID 请求ID
	 * @param userID 用户ID
	 * @param amount 充值金额
	 * @param requestStatus 请求状态
	 * @param method 方式:false-微信 或 true-支付宝
	 * @param requestTime 请求时间，需要的类型：{@link java.sql.Date}
	 * @throws RequestException
	 */
	public RechargeRequest(int requestID, int userID, float amount, boolean requestStatus, boolean method, Date requestTime) throws RequestException
	{
		super(requestID, userID, 0, amount, false, requestStatus, method, requestTime);
	}
}
