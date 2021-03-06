package com.altale.service.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Logger;


import buaa.jj.accountservice.api.AccountService;
import com.altale.Launcher;
import org.json.JSONArray;

import com.altale.service.CSException.*;
import com.altale.util.*;
import com.altale.service.request.*;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
/**
 * 数据库连接类
 */
public class SQLConnection
{
	private Connection connection;
	private PreparedStatement pstmt;
	private static Log logger = LogFactory.getLog(SQLConnection.class);
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
	
	/**
	 * 对当前时间前一天进行清分
	 * @throws SQLException
	 */
	public double clearing() throws SQLException {
		ArrayList<ClearingMessage> messages = getClearing(new Date());
		double total = 0;
		for(ClearingMessage message : messages) {
			total += message.fee;
		}
		JSONUtil.writeFile(JSONUtil.MessagesToArray(messages));
		return  total;
	}

	/**
	 * 
	 * @param date 清分date前一天内的数据库
	 * @return 获取那一天的清分内容
	 * @throws SQLException
	 */
	private ArrayList<ClearingMessage> getClearing(Date date) {
		
		Date before15day = DateUtil.toDayBefore(new Date(), 15);
		if (date.before(before15day)) {
			throw new TimeOutOfRangeException();
		}
		
		// 获取清分列表
		ArrayList<ClearingMessage> clearingMessages = new ArrayList<ClearingMessage>();
		try{
			Date start = DateUtil.toDayBefore(date, 1);
			Date end = date;
			String startTime = DateUtil.dateToString(start, 0);
			String endTime = DateUtil.dateToString(end, 0);
			String selectSQL = "SELECT merchantID, SUM(amount) as amo "
					+ "FROM trade "
					+ "WHERE requestTime > '" + startTime + "' AND requestTime < '" + endTime + "' AND operateStatus = 0 "
					+ "GROUP BY merchantID";
			Statement statement1 = connection.createStatement();
			ResultSet rs = statement1.executeQuery(selectSQL);
			
//			String notExist = "";
//			ClearingMessage currClearingMessage = new ClearingMessage(notExist); // 不存在seller
			while(rs.next()){
	            // 通过字段检索
	            String merchantID  = rs.getString("merchantID");
	            double amount = rs.getDouble("amo");
	            ClearingMessage message = new ClearingMessage(
	            		merchantID,
						amount * (1 - Constant.texRatio),
						amount * Constant.texRatio
				);
	            clearingMessages.add(message);
	            // 添加信息
//	            if (currClearingMessage.merchantID == notExist && currClearingMessage.merchantID != merchantID) {
//	            	currClearingMessage = new ClearingMessage(merchantID);
//	            } else if(currClearingMessage.merchantID != notExist && currClearingMessage.merchantID != merchantID) {
//	            	clearingMessages.add(currClearingMessage);
//	            	currClearingMessage = new ClearingMessage(merchantID);
//	            }
//	            currClearingMessage.amount += amount * (1 - Constant.texRatio);
//	            currClearingMessage.fee += amount * Constant.texRatio;
	        }
//			if (currClearingMessage.merchantID != notExist) {
//				clearingMessages.add(currClearingMessage);
//			}
			double sumearning=0;
			// 进行清分转账
			for(ClearingMessage message : clearingMessages) {
				String merchantID = message.merchantID; // 转给的商户
				double amount = message.amount; //商家得到的
				double fee = message.fee; // 手续费
//				int	pay_user_id	付款方用户ID	无
//				int	get_user_id	收款方用户ID	无
//				double	amount	转账额	无
//				boolean	trade_type	交易类型	false转账，true消费
				sumearning+=fee;
				//待清算账户2转账给平台账户1
				Launcher.accountService.transferConsume(2, 1, fee, false);
				//待清算账户2转账给商户
				Launcher.accountService.transferConsume(2, Integer.valueOf(merchantID), amount, false);
			}

			logger.info("Today's earning is "+sumearning+" yuan");
	
			// 更新数据库清分状态
			String updateSQL = "UPDATE trade "
					+ "SET operateStatus = 1 "
					+ "WHERE requestTime > '" + startTime + "' AND requestTime < '" + endTime + "' ";
			Statement statement2 = connection.createStatement();
			statement2.executeUpdate(updateSQL);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return clearingMessages;
	}

	public JSONArray findQueryRecord(int kind) {
		Date date = DateUtil.toDayBefore(new Date(), 15);
		return  findQueryRecord(date, new Date(), kind);
	}

	/**
	 * 
	 * @param start
	 * @param end
	 * @param kind: 0-充值, 1-提现, 2-消费
	 * @return
	 * @throws SQLException 
	 */
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
					Date requestTime = rs.getDate("requestTime"); // TODO:可以吗
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
					Date requestTime = rs.getDate("requestTime"); // TODO:可以吗
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
					Date requestTime = rs.getDate("requestTime"); // TODO:可以吗
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
