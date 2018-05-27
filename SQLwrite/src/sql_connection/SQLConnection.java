package sql_connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.Statement;
//import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import request.RechargeRequest;
import request.WithdrawRequest;
import zzxPackage.Constant;
import zzxPackage.ClearingMessage;
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
	
	// ����merchantID,userID��amount��fee
	public ArrayList<ClearingMessage> clearing() throws SQLException {
		
		String sql = "SELECT merchantID, userID, amount "
				+ "FROM trade "
				+ "GROUP BY merchantID";
		Statement statement = connection.createStatement();
		ResultSet rs = statement.executeQuery(sql);
		
		ArrayList<ClearingMessage> clearingMessages = new ArrayList<ClearingMessage>();
		int notExist = -1;
		ClearingMessage currClearingMessage = new ClearingMessage(notExist); // ������seller
		while(rs.next()){
            // ͨ���ֶμ���
            int merchantID  = rs.getInt("merchantID");
            // int userID = rs.getInt("userID");
            float amount = rs.getFloat("amount");
            
            // �����Ϣ
            if (currClearingMessage.merchantID == notExist && currClearingMessage.merchantID != merchantID) {
            	currClearingMessage = new ClearingMessage(merchantID);
            } else if(currClearingMessage.merchantID != notExist && currClearingMessage.merchantID != merchantID) {
            	clearingMessages.add(currClearingMessage);
            	currClearingMessage = new ClearingMessage(merchantID);
            }
            currClearingMessage.amount += amount * (1 - Constant.texRatio);
            currClearingMessage.fee += amount * Constant.texRatio;
        }
		if (currClearingMessage.merchantID != notExist) {
			clearingMessages.add(currClearingMessage);
		}
		return clearingMessages;
	}
	
	
	
}
