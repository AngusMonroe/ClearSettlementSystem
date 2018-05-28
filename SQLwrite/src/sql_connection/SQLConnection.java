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
 * 数据库连接类
 */
public class SQLConnection
{
	private Connection connection;
	private PreparedStatement pstmt;
	
	/**
	 * 数据库连接类构造函数
	 * @param url URL
	 * @param user 用户名
	 * @param password 密码
	 */
	public SQLConnection(String url, String user, String password) throws Exception
	{
		connection = DriverManager.getConnection(url,user,password);
	}
	
	/**
	 * 向数据库发送充值请求
	 * @param r 充值请求
	 * @return 请求ID
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
	 * 向数据库发送提现请求
	 * @param r 提现请求
	 * @return 请求ID
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
	 * 向数据库发送交易请求
	 * @param r 交易请求
	 * @return 请求ID
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
	
	// ------- 已下是zzx的数据库函数操作，若报错全部注释掉就行 -----------//
	
	// 返回merchantID,userID，amount，fee
	public JSONArray clearing() throws SQLException {
		
		Date adayBefore = DateUtil.toDayBefore(new Date(), 1);
		Date currDate = new Date();
		String before = DateUtil.dateToString(adayBefore, 0);
		String now = DateUtil.dateToString(currDate, 0);
		String sql = "SELECT merchantID, userID, amount "
				+ "FROM trade "
				+ "WHERE requestTime > '" + before + "' AND requestTime < '" + now + "' "
				+ "GROUP BY merchantID";
		Statement statement = connection.createStatement();
		ResultSet rs = statement.executeQuery(sql);
		
		ArrayList<ClearingMessage> clearingMessages = new ArrayList<ClearingMessage>();
		int notExist = -1;
		ClearingMessage currClearingMessage = new ClearingMessage(notExist); // 不存在seller
		while(rs.next()){
            // 通过字段检索
            int merchantID  = rs.getInt("merchantID");
            float amount = rs.getFloat("amount");
            
            // 添加信息
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
		
		JSONArray ans = JSONUtil.MessagesToArray(clearingMessages);
		return ans;
	}
	
	/**
	 * 
	 * @param startTime
	 * @param endTime
	 * @param kind: 0-充值, 1-提现, 2-消费
	 * @return
	 * @throws SQLException 
	 */
	public JSONArray findQueryRecord(Date startTime, Date endTime, int kind) throws SQLException {
		
		Date before15day = DateUtil.toDayBefore(new Date(), 15);
		if(!startTime.before(endTime) 
				|| !startTime.after(before15day) 
				|| !endTime.before(new Date())) {
			throw new TimeOutOfRangeException();
		}
		
		ArrayList<Message> messages;
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
				Date requestTime = rs.getDate("requestTime"); // TODO:可以吗
				float amount = rs.getFloat("amount");
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
				Date requestTime = rs.getDate("requestTime"); // TODO:可以吗
				float amount = rs.getFloat("amount");
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
				Date requestTime = rs.getDate("requestTime"); // TODO:可以吗
				float amount = rs.getFloat("amount");
				int operateStatus = rs.getInt("operateStatus");
				Message message = new TradeMessage(
						requestID, userID, mrechantID, requestTime, amount, operateStatus);
				messages.add(message);
			}
			
		} else {
			throw new TimeOutOfRangeException();
		}	
		
		JSONArray ans = JSONUtil.MessagesToArray(messages);
		return ans;
	}

}
