package request;

import java.sql.Date;

import sql_connection.SQLConnection;

/**
 * ����������
 */
public final class WithdrawRequest extends Result
{
	private Date timestamp;
	private String method;
	
	/**
	 * ���������๹�캯��
	 * @param userID �û�ID
	 * @param requestID ����ID
	 * @param amount ���ֽ��
	 * @param state ����״̬
	 * @param timestamp ����ʱ�䣬��Ҫ�����ͣ�{@link java.sql.Date}
	 * @param method ���ַ�ʽ:"֧����"��"΢��"
	 */
	public WithdrawRequest(int userID, int requestID, float amount, boolean state, Date timestamp, String method)
	{
		super(userID, requestID, amount, state);
		
		this.timestamp = timestamp;
		this.method = method;
	}
	
	/**
	 * ���Է��������ύ��������
	 * @return �Ƿ�ɹ�
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
	 * ��������ʱ�䣬���ص����ͣ�{@link java.sql.Date}
	 * @return timestamp
	 */
	public Date getTimestamp()
	{
		return timestamp;
	}
	
	/**
	 * �������ַ�ʽ
	 * @return method
	 */
	public String getMethod()
	{
		return method;
	}
}
