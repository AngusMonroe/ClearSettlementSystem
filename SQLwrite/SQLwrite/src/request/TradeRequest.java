package request;

import java.sql.Date;

import sql_connection.SQLConnection;

/**
 * ����������
 */
public class TradeRequest extends Result
{
	private int sellerID;
	private Date timestamp;
	
	/**
	 * ���������๹�캯��
	 * @param userID �û�ID
	 * @param sellerID �̼�ID
	 * @param requestID ����ID
	 * @param amount ��ֵ���
	 * @param state ����״̬
	 * @param timestamp ����ʱ�䣬��Ҫ�����ͣ�{@link java.sql.Date}
	 * @throws WriteException
	 */
	public TradeRequest(int userID, int sellerID, int requestID, float amount, boolean state, Date timestamp)
	{		
		super(userID, requestID, amount, state);
		
		this.sellerID = sellerID;
		this.timestamp = timestamp;
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
	 * �����̼�ID
	 * @return sellerID
	 */
	public int getSellerID()
	{
		return sellerID;
	}
	
	/**
	 * ��������ʱ�䣬���ص����ͣ�{@link java.sql.Date}
	 * @return timestamp
	 */
	public Date getTimestamp()
	{
		return timestamp;
	}
}
