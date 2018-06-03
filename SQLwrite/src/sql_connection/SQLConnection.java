package sql_connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;


import org.json.JSONArray;

import exception.TimeOutOfRangeException;
import request.RechargeRequest;
import request.WithdrawRequest;
import request.TradeRequest;

import zzxPackage.Constant;
import zzxPackage.DateUtil;
import zzxPackage.JSONUtil;
import zzxPackage.Message;
import zzxPackage.RechargeMessage;
import zzxPackage.TradeMessage;
import zzxPackage.WithdrawMessage;
import zzxPackage.ClearingMessage;

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
	public String sendRequest(RechargeRequest r)
	{
		try
		{
			String sql = "INSERT INTO recharge VALUES(?,?,?,?,?)";
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, r.getRequestID() + "");
			pstmt.setString(2, r.getUserID() + "");
			pstmt.setString(3, r.getRequestTime() + "");
			pstmt.setString(4, r.getAmount() + "");
			pstmt.setString(5, (r.getMethod() == true ? 1 : 0) + "");
			pstmt.execute();
			return r.getRequestID();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return "-1";
		}
	}
	
	/**
	 * �����ݿⷢ����������
	 * @param r ��������
	 * @return ����ID
	 */
	public String sendRequest(WithdrawRequest r)
	{
		try
		{
			String sql = "INSERT INTO withdraw VALUES(?,?,?,?,?)";
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, r.getRequestID() + "");
			pstmt.setString(2, r.getUserID() + "");
			pstmt.setString(3, r.getRequestTime() + "");
			pstmt.setString(4, r.getAmount() + "");
			pstmt.setString(5, (r.getMethod() == true ? 1 : 0) + "");
			pstmt.execute();
			return r.getRequestID();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return "-1";
		}
	}
	
	/**
	 * �����ݿⷢ�ͽ�������
	 * @param r ��������
	 * @return ����ID
	 */
	public String sendRequest(TradeRequest r)
	{
		try
		{
			String sql = "INSERT INTO trade VALUES(?,?,?,?,?,?)";
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, r.getRequestID() + "");
			pstmt.setString(2, r.getUserID() + "");
			pstmt.setString(3, r.getMerchantID() + "");
			pstmt.setString(4, r.getRequestTime() + "");
			pstmt.setString(5, r.getAmount() + "");
			pstmt.setString(6, (r.getOperateStatus() == true ? 1 : 0) + "");
			pstmt.execute();
			return r.getRequestID();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return "-1";
		}
	}
	
	// ------- ������zzx�����ݿ⺯��������������ȫ��ע�͵����� -----------//
	
	/**
	 * �Ե�ǰʱ��ǰһ��������
	 * @throws SQLException
	 */
	public void clearing() throws SQLException {
		JSONArray jsArray = getClearing(new Date());
		JSONUtil.writeFile(jsArray);
	}

	/**
	 * 
	 * @param date ���dateǰһ���ڵ����ݿ�
	 * @return ��ȡ��һ����������
	 * @throws SQLException
	 */
	private JSONArray getClearing(Date date) {
		
		Date before15day = DateUtil.toDayBefore(new Date(), 15);
		if (date.before(before15day)) {
			throw new TimeOutOfRangeException();
		}
		
		// ��ȡ����б�
		ArrayList<ClearingMessage> clearingMessages = new ArrayList<ClearingMessage>();
		try{
			Date start = DateUtil.toDayBefore(date, 1);
			Date end = date;
			String startTime = DateUtil.dateToString(start, 0);
			String endTime = DateUtil.dateToString(end, 0);
			String selectSQL = "SELECT merchantID, userID, amount "
					+ "FROM trade "
					+ "WHERE requestTime > '" + startTime + "' AND requestTime < '" + endTime + "' "
					+ "GROUP BY merchantID";
			Statement statement1 = connection.createStatement();
			ResultSet rs = statement1.executeQuery(selectSQL);
			
			String notExist = "";
			ClearingMessage currClearingMessage = new ClearingMessage(notExist); // ������seller
			while(rs.next()){
	            // ͨ���ֶμ���
	            String merchantID  = rs.getString("merchantID");
	            double amount = rs.getDouble("amount");
	            
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
			
			// �������ת��
			for(ClearingMessage message : clearingMessages) {
				String merchantID = message.merchantID; // ת�����̻�
				double amount = message.amount; //�̼ҵõ���
				double fee = message.fee; // ������
//				int	pay_user_id	����û�ID	��
//				int	get_user_id	�տ�û�ID	��
//				double	amount	ת�˶�	��
//				boolean	trade_type	��������	falseת�ˣ�true����
//				TODO: transferConsumer(pay_user_id��ƽ̨id�ǹ̶�ֵ��get_user_id��merchantID��
//					amount��amount��trade_type��false)
			}
	
			// �������ݿ����״̬
			String updateSQL = "UPDATE trade "
					+ "SET operateStatus = 1 "
					+ "WHERE requestTime > '" + startTime + "' AND requestTime < '" + endTime + "' ";
			Statement statement2 = connection.createStatement();
			statement2.executeUpdate(updateSQL);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		JSONArray ans = JSONUtil.MessagesToArray(clearingMessages);
		return ans;
	}

	/**
	 * 
	 * @param startTime
	 * @param endTime
	 * @param kind: 0-��ֵ, 1-����, 2-����
	 * @return
	 * @throws SQLException 
	 */
	public JSONArray findQueryRecord(Date startTime, Date endTime, int kind) {
		
		Date before15day = DateUtil.toDayBefore(new Date(), 15);
		if(!startTime.before(endTime) 
				|| !startTime.after(before15day) 
				|| !endTime.before(new Date())) {
			throw new TimeOutOfRangeException();
		}
		
		ArrayList<Message> messages = null;
		try{
			if (kind == 0) {
				String sql = "SELECT requestID, userID, requestTime, amount, method "
						+ " FROM recharge "
						+ " WHERE requestTime > '" + startTime + "' AND requestTime < '" + endTime + "'";
				Statement statement = connection.createStatement();
				ResultSet rs = statement.executeQuery(sql);
				messages = new ArrayList<Message>();
				
				while (rs.next()) {
					String requestID = rs.getString("requestID");
					String userID = rs.getString("userID");
					Date requestTime = rs.getDate("requestTime"); // TODO:������
					double amount = rs.getDouble("amount");
					int method = rs.getInt("method");
					Message message = new RechargeMessage(
							requestID, userID, requestTime, amount, method);
					messages.add(message);
				}
				
			} else if (kind == 1) {
				String sql = "SELECT requestID, userID, requestTime, amount, method "
						+ " FROM withdraw "
						+ " WHERE requestTime > '" + startTime + "' AND requestTime < '" + endTime + "'";
				Statement statement = connection.createStatement();
				ResultSet rs = statement.executeQuery(sql);
				messages = new ArrayList<Message>();
				
				while (rs.next()) {
					String requestID = rs.getString("requestID");
					String userID = rs.getString("userID");
					Date requestTime = rs.getDate("requestTime"); // TODO:������
					double amount = rs.getDouble("amount");
					int method = rs.getInt("method");
					Message message = new WithdrawMessage(
							requestID, userID, requestTime, amount, method);
					messages.add(message);
				}
				
			} else if (kind == 2) {
				String sql = "SELECT requestID, userID, mrechantID, requestTime, amount, operateStatus"
						+ " FROM trade "
						+ " WHERE requestTime > '" + startTime + "' AND requestTime < '" + endTime + "'";
				Statement statement = connection.createStatement();
				ResultSet rs = statement.executeQuery(sql);
				messages = new ArrayList<Message>();
				
				while (rs.next()) {
					String requestID = rs.getString("requestID");
					String userID = rs.getString("userID");
					String mrechantID = rs.getString("mrechantID");
					Date requestTime = rs.getDate("requestTime"); // TODO:������
					double amount = rs.getDouble("amount");
					int operateStatus = rs.getInt("operateStatus");
					Message message = new TradeMessage(
							requestID, userID, mrechantID, requestTime, amount, operateStatus);
					messages.add(message);
				}
				
			} else {
				throw new TimeOutOfRangeException();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		JSONArray ans = JSONUtil.MessagesToArray(messages);
		return ans;
	}

}
