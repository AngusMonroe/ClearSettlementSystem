package request;

/**
 * 用于保存充值、提现和交易的信息结果类
 */
public class Result	
{
	private int userID;
	private int requestID;
	private float amount;
	private boolean state;
	private boolean returnResult;
	
	/**
	 * 结果信息类构造函数
	 * @param userID 用户ID
	 * @param requestID 请求ID
	 * @param amount 金额
	 * @param state 操作状态
	 */
	protected Result(int userID, int requestID, float amount, boolean state)
	{
		this.userID = userID;
		this.requestID = requestID;
		this.amount = amount;
		this.state = state;
		this.returnResult = false;
	}
	
	/**
	 * <p>获取结果
	 * <p>调用此函数前，需要调用sendRequest并保证返回值为true
	 * @return 信息结果类
	 * @throws WriteException
	 */
	public Result getResult() throws WriteException
	{
		if (!returnResult) throw new WriteException("调用getResult前，需要调用sendRequest并保证返回值为true");
		
		return this;
	}
	
	/**
	 * 使结果可以被获取
	 */
	public void enableGetResult()
	{
		returnResult = true;
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
	
	/**
	 * 返回用户ID
	 * @return userID
	 */
	public int getUserID()
	{
		return userID;
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
}
