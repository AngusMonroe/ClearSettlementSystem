package sql_connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
//import java.sql.ResultSet;
//import java.sql.Statement;
//import java.util.ArrayList;
import java.util.ArrayList;

import request.RechargeRequest;
import request.WithdrawRequest;
import zzxPackage.Message;
import request.TradeRequest;
//import zzxPackage.Message;

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
	 * 向数据库发送提现请求
	 * @param r 提现请求
	 * @return 请求ID
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
	 * 向数据库发送交易请求
	 * @param r 交易请求
	 * @return 请求ID
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
	
	// 返回sellerID,count，record_type，status
	public ArrayList<Message> clearing() throws Exception {
		
		String sql = "SELECT seller_id, user_id, amount "
				+ "FROM record "
				+ "WHERE seller_id IS NOT null and user_id IS NOT null " // 可确定转账
				+ "GROUP BY seller_id";
		Statement statement = connection.createStatement();
		ResultSet rs = statement.executeQuery(sql);
		
		ArrayList<Message> messages = new ArrayList<Message>();
		int notExist = -1;
		Message currMessage = new Message(notExist); // 不存在seller
		while(rs.next()){
            // 通过字段检索
            int sellerID  = rs.getInt("seller_id");
            int userID = rs.getInt("user_id");
            float amount = rs.getFloat("amount");
            
            // 添加信息
            if (currMessage.sellerID == notExist && currMessage.sellerID != sellerID) {
            	currMessage = new Message(sellerID);
            } else if(currMessage.sellerID != notExist && currMessage.sellerID != sellerID) {
            	messages.add(currMessage);
            	currMessage = new Message(sellerID);
            }
            final double texRatio = 0.1; 	// TODO:finish交税
            currMessage.fromPlatform(amount * (1-texRatio));
            
//            System.out.println("seller_id: " + sellerID);
//            System.out.println("user_id: " + userID);
//            System.out.println("amount: " + amount);
        }
		if (currMessage.sellerID != notExist) {
			messages.add(currMessage);
		}
		return messages;
	}
	
}
