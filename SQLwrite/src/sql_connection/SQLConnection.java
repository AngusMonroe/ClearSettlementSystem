package sql_connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.Statement;
//import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import request.RechargeRequest;
import request.WithdrawRequest;
import zzxPackage.Constant;
import zzxPackage.Message;
import request.TradeRequest;

/**
 * ���ݿ�������
 */
public class SQLConnection
{
	private Connection connection;
	private PreparedStatement pstmt;
	
	/**
	 * ���ݿ������๹�캯��
	 * @param url URL
	 * @param user �û���
	 * @param password ����
	 */
	public SQLConnection(String url, String user, String password) throws Exception
	{
		connection = DriverManager.getConnection(url,user,password);
	}
	
	/**
	 * �����ݿⷢ�ͳ�ֵ����
	 * @param r ��ֵ����
	 * @return ����ID
	 */
	public int sendRequest(RechargeRequest r)
	{
		try
		{
			String sql = "INSERT INTO recharge VALUES(?,?,?,?,?,?,?)";
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, r.getRequestID() + "");
			pstmt.setString(2, r.getUserID() + "");
			pstmt.setString(3, r.getRequestTime() + "");
			pstmt.setString(4, r.getAmount() + "");
			pstmt.setString(5, (r.getMethod() == true ? 1 : 0) + "");
			pstmt.setString(6, (r.getOperateStatus() == true ? 1 : 0) + "");
			pstmt.setString(7, (r.getRequestStatus() == true ? 1 : 0) + "");
			pstmt.execute();
			return r.getRequestID();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return -1;
		}
	}
	
	/**
	 * �����ݿⷢ����������
	 * @param r ��������
	 * @return ����ID
	 */
	public int sendRequest(WithdrawRequest r)
	{
		try
		{
			String sql = "INSERT INTO withdraw VALUES(?,?,?,?,?,?,?)";
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, r.getRequestID() + "");
			pstmt.setString(2, r.getUserID() + "");
			pstmt.setString(3, r.getRequestTime() + "");
			pstmt.setString(4, r.getAmount() + "");
			pstmt.setString(5, (r.getMethod() == true ? 1 : 0) + "");
			pstmt.setString(6, (r.getOperateStatus() == true ? 1 : 0) + "");
			pstmt.setString(7, (r.getRequestStatus() == true ? 1 : 0) + "");
			pstmt.execute();
			return r.getRequestID();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return -1;
		}
	}
	
	/**
	 * �����ݿⷢ�ͽ�������
	 * @param r ��������
	 * @return ����ID
	 */
	public int sendRequest(TradeRequest r)
	{
		try
		{
			String sql = "INSERT INTO trade VALUES(?,?,?,?,?,?,?)";
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, r.getRequestID() + "");
			pstmt.setString(2, r.getUserID() + "");
			pstmt.setString(3, r.getMerchantID() + "");
			pstmt.setString(4, r.getRequestTime() + "");
			pstmt.setString(5, r.getAmount() + "");
			pstmt.setString(6, (r.getOperateStatus() == true ? 1 : 0) + "");
			pstmt.setString(7, (r.getRequestStatus() == true ? 1 : 0) + "");
			pstmt.execute();
			return r.getRequestID();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return -1;
		}
	}
	
	// ------- ������zzx�����ݿ⺯��������������ȫ��ע�͵����� -----------//
	
	// ����sellerID,count��record_type��status
	public ArrayList<Message> clearing() throws Exception {
		
		String sql = "SELECT merchantID, userID, amount "
				+ "FROM trade "
				+ "GROUP BY merchantID";
		Statement statement = connection.createStatement();
		ResultSet rs = statement.executeQuery(sql);
		
		ArrayList<Message> messages = new ArrayList<Message>();
		int notExist = -1;
		Message currMessage = new Message(notExist); // ������seller
		while(rs.next()){
            // ͨ���ֶμ���
            int merchantID  = rs.getInt("merchantID");
            // int userID = rs.getInt("userID");
            float amount = rs.getFloat("amount");
            
            // �����Ϣ
            if (currMessage.merchantID == notExist && currMessage.merchantID != merchantID) {
            	currMessage = new Message(merchantID);
            } else if(currMessage.merchantID != notExist && currMessage.merchantID != merchantID) {
            	messages.add(currMessage);
            	currMessage = new Message(merchantID);
            }
            currMessage.amount += amount * (1 - Constant.texRatio);
            currMessage.fee += amount * Constant.texRatio;
        }
		if (currMessage.merchantID != notExist) {
			messages.add(currMessage);
		}
		return messages;
	}
		
}
