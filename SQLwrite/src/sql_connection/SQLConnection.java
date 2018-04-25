package sql_connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import request.Request;
import request.RechargeRequest;
import request.WithdrawRequest;
import request.TradeRequest;
import zzxPackage.Message;

/**
 * 数据库连接类
 */
public class SQLConnection
{
	private String table;
	private Connection connection;
	private PreparedStatement pstmt;
	
	/**
	 * 数据库连接类构造函数
	 * @param url URL
	 * @param user 用户名
	 * @param password 密码
	 * @param database 数据库
	 * @param table 表
	 */
	public SQLConnection(String url, String user, String password, String table) throws Exception
	{
		this.table = table;

		connection = DriverManager.getConnection(url,user,password);
	}
	
	/**
	 * 向数据库插入充值账单
	 * @param r 充值请求
	 * @throws Exception
	 */
	public void insert(RechargeRequest r) throws Exception
	{
		String sql = "INSERT INTO " + table + " VALUES(?,?,?,?,?,?,?,?)";
		pstmt = connection.prepareStatement(sql);
		pstmt.setString(1, r.getRequestID() + "");
		pstmt.setString(2, Request.RecordType.RECHARGE.name());
		pstmt.setString(3, r.getUserID() + "");
		pstmt.setString(4, null);
		pstmt.setString(5, r.getAmount() + "");
		pstmt.setString(6, r.getTimestamp() + "");
		pstmt.setString(7, r.getMethod());
		pstmt.setString(8, (r.getState() == true ? 1 : 0) + "");
		pstmt.execute();
	}
	
	/**
	 * 向数据库插入提现账单
	 * @param r 提现请求
	 * @throws Exception
	 */
	public void insert(WithdrawRequest r) throws Exception
	{
		String sql = "INSERT INTO " + table + " VALUES(?,?,?,?,?,?,?,?)";
		pstmt = connection.prepareStatement(sql);
		pstmt.setString(1, r.getRequestID() + "");
		pstmt.setString(2, Request.RecordType.WITHDRAW.name());
		pstmt.setString(3, r.getUserID() + "");
		pstmt.setString(4, null);
		pstmt.setString(5, r.getAmount() + "");
		pstmt.setString(6, r.getTimestamp() + "");
		pstmt.setString(7, r.getMethod());
		pstmt.setString(8, (r.getState() == true ? 1 : 0) + "");
		pstmt.execute();
	}
	
	/**
	 * 向数据库插入交易账单
	 * @param r 交易请求
	 * @throws Exception
	 */
	public void insert(TradeRequest r) throws Exception
	{
		String sql = "INSERT INTO " + table + " VALUES(?,?,?,?,?,?,?,?)";
		pstmt = connection.prepareStatement(sql);
		pstmt.setString(1, r.getRequestID() + "");
		pstmt.setString(2, Request.RecordType.TRADE.name());
		pstmt.setString(3, r.getUserID() + "");
		pstmt.setString(4, r.getSellerID() + "");
		pstmt.setString(5, r.getAmount() + "");
		pstmt.setString(6, r.getTimestamp() + "");
		pstmt.setString(7, null);
		pstmt.setString(8, (r.getState() == true ? 1 : 0) + "");
		pstmt.execute();
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
