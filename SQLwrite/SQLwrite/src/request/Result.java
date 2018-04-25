package request;

/**
 * ���ڱ����ֵ�����ֺͽ��׵���Ϣ�����
 */
public class Result	
{
	private int userID;
	private int requestID;
	private float amount;
	private boolean state;
	private boolean returnResult;
	
	/**
	 * �����Ϣ�๹�캯��
	 * @param userID �û�ID
	 * @param requestID ����ID
	 * @param amount ���
	 * @param state ����״̬
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
	 * <p>��ȡ���
	 * <p>���ô˺���ǰ����Ҫ����sendRequest����֤����ֵΪtrue
	 * @return ��Ϣ�����
	 * @throws WriteException
	 */
	public Result getResult() throws WriteException
	{
		if (!returnResult) throw new WriteException("����getResultǰ����Ҫ����sendRequest����֤����ֵΪtrue");
		
		return this;
	}
	
	/**
	 * ʹ������Ա���ȡ
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
	 * �����û�ID
	 * @return userID
	 */
	public int getUserID()
	{
		return userID;
	}
	
	/**
	 * ��������ID
	 * @return requestID
	 */
	public int getRequestID()
	{
		return requestID;
	}
	
	/**
	 * ���ؽ��
	 * @return amount
	 */
	public float getAmount()
	{
		return amount;
	}
	
	/**
	 * ���ز���״̬
	 * @return state
	 */
	public boolean getState()
	{
		return state;
	}
}
