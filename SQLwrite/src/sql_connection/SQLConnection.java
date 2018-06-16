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

import exception.OperatorIdOutOfRangeException;
import exception.TimeOutOfRangeException;

import request.RechargeRequest;
import request.TradeRequest;
import request.WithdrawRequest;

import zzxPackage.ClearingMessage;
import zzxPackage.Constant;
import zzxPackage.DateUtil;
import zzxPackage.JSONUtil;
import zzxPackage.Message;
import zzxPackage.RechargeMessage;
import zzxPackage.TradeMessage;
import zzxPackage.WithdrawMessage;

public class SQLConnection
{
	private Connection connection;
	private PreparedStatement pstmt;
	
	public SQLConnection(String url, String user, String password) throws Exception
	{
		connection = DriverManager.getConnection(url,user,password);
	}
	
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
	
	public double clearing() throws SQLException {
		ArrayList<ClearingMessage> messages = getClearing(new Date());
		double total = 0;
		for(ClearingMessage message : messages) {
			total += message.fee;
		}
		return  total;
	}

	public ArrayList<ClearingMessage> getClearing(Date date) {
		
		Date before15day = DateUtil.toDayBefore(new Date(), 15);
		if (date.before(before15day)) {
			throw new TimeOutOfRangeException();
		}
		
		ArrayList<ClearingMessage> clearingMessages = new ArrayList<ClearingMessage>();
		try{
			Date start = DateUtil.toDayBefore(date, 1);
			Date end = date;
			String startTime = DateUtil.dateToString(start, 0);
			String endTime = DateUtil.dateToString(end, 0);
			String selectSQL = "SELECT merchantID, SUM(amount) as amo "
					+ "FROM trade "
					+ "WHERE requestTime > '" + startTime + "' AND requestTime < '" + endTime + "' "
					+ "GROUP BY merchantID";
			Statement statement1 = connection.createStatement();
			ResultSet rs = statement1.executeQuery(selectSQL);
			System.out.println(selectSQL);
			
			while(rs.next()){
	            String merchantID  = rs.getString("merchantID");
	            double amount = rs.getDouble("amo");
	            ClearingMessage message = new ClearingMessage(
	            		merchantID,
						amount * (1 - Constant.texRatio),
						amount * Constant.texRatio
				);
	            System.out.println(message.toJSONObject().toString());
	            clearingMessages.add(message);
	        }
			double sumearning=0;
			for(ClearingMessage message : clearingMessages) {
				String merchantID = message.merchantID;
				double amount = message.amount;
				double fee = message.fee;
				sumearning+=fee;
			}
			String updateSQL = "UPDATE trade "
					+ "SET operateStatus = 1 "
					+ "WHERE requestTime > '" + startTime + "' AND requestTime < '" + endTime + "' ";
			Statement statement2 = connection.createStatement();
			statement2.executeUpdate(updateSQL);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		JSONUtil.writeFile(JSONUtil.MessagesToArray(clearingMessages), DateUtil.dateToString(date, 1));
		return clearingMessages;
	}

	public JSONArray findQueryRecord(int kind) {
		Date date = DateUtil.toDayBefore(new Date(), 15);
		return  findQueryRecord(date, new Date(), kind);
	}

	public JSONArray findQueryRecord(Date start, Date end, int kind) {

		Date before15day = DateUtil.toDayBefore(new Date(), 15);
		if(!start.before(end) 
				|| !end.after(before15day) 
				|| end.after(new Date())) {
			throw new TimeOutOfRangeException();
		}
		
		String startTime = DateUtil.dateToString(start, 1);
		String endTime = DateUtil.dateToString(end, 1);
		
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
					Date requestTime = rs.getDate("requestTime");
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
					Date requestTime = rs.getDate("requestTime");
					double amount = rs.getDouble("amount");
					int method = rs.getInt("method");
					Message message = new WithdrawMessage(
							requestID, userID, requestTime, amount, method);
					messages.add(message);
				}
				
			} else if (kind == 2) {
				String sql = "SELECT requestID, userID, merchantID, requestTime, amount, operateStatus"
						+ " FROM trade "
						+ " WHERE requestTime > '" + startTime + "' AND requestTime < '" + endTime + "'";
				Statement statement = connection.createStatement();
				ResultSet rs = statement.executeQuery(sql);
				messages = new ArrayList<Message>();
				
				while (rs.next()) {
					String requestID = rs.getString("requestID");
					String userID = rs.getString("userID");
					String merchantID = rs.getString("merchantID");
					Date requestTime = rs.getDate("requestTime");
					double amount = rs.getDouble("amount");
					int operateStatus = rs.getInt("operateStatus");
					Message message = new TradeMessage(
							requestID, userID, merchantID, requestTime, amount, operateStatus);
					messages.add(message);
				}
				
			} else {
				throw new OperatorIdOutOfRangeException();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		JSONArray ans = JSONUtil.MessagesToArray(messages);
		return ans;
	}

}
