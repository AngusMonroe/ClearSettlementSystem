package request;

/**
 * 用于保存充值、提现和交易的信息结果类
 */
public class Result
{	
	private int requestID;
	private int userID;
	private float amount;
	private boolean state;
	
	/**
	 * 结果信息类构造函数
	 * @param requestID 请求ID
	 * @param userID 用户ID
	 * @param amount 金额
	 * @param state 操作状态
	 */
	protected Result(int requestID, int userID, float amount, boolean state)
	{
		this.requestID = requestID;
		this.userID = userID;
		this.amount = amount;
		this.state = state;
	}
	
	@Override
	public String toString()
	{
		return
				"userID: " + userID + "\n" +
				"requestID: " + requestID + "\n" +
				"amount: " + amount + "\n" +
				"state: " + state + "\n";
	}
}
