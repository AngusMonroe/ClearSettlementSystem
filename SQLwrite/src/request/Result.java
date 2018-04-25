package request;

/**
 * ���ڱ����ֵ�����ֺͽ��׵���Ϣ�����
 */
public class Result
{	
	private int requestID;
	private int userID;
	private float amount;
	private boolean state;
	
	/**
	 * �����Ϣ�๹�캯��
	 * @param requestID ����ID
	 * @param userID �û�ID
	 * @param amount ���
	 * @param state ����״̬
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
