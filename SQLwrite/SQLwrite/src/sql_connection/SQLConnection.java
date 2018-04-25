package sql_connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import request.RechargeRequest;
import request.WithdrawRequest;
import request.TradeRequest;

/**
 * 数据库连接类
 */
public class SQLConnection
{
	private String url;
	private String user;
	private String password;
	
	/**
	 * 数据库连接类构造函数
	 * @param url URL
	 * @param user 用户名
	 * @param password 密码
	 */
	public SQLConnection(String url, String user, String password) throws Exception
	{
		this.url = url;
		this.user = user;
		this.password = password;
	}
	
	/**
	 * 向数据库插入充值账单
	 * @param r 充值请求
	 * @throws Exception
	 */
	public void insert(RechargeRequest r) throws Exception
	{
		Connection conn = DriverManager.getConnection(url,user,password);
		String sql = "INSERT INTO record values(?,?,?,?,?,?,?,?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, r.getRequestID() + "");
		pstmt.setString(2, "recharge");
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
	 * @param r 充值请求
	 * @throws Exception
	 */
	public void insert(WithdrawRequest r) throws Exception
	{
		Connection conn = DriverManager.getConnection(url,user,password);
		String sql = "INSERT INTO record values(?,?,?,?,?,?,?,?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, r.getRequestID() + "");
		pstmt.setString(2, "withdraw");
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
	 * @param r 充值请求
	 * @throws Exception
	 */
	public void insert(TradeRequest r) throws Exception
	{
		Connection conn = DriverManager.getConnection(url,user,password);
		String sql = "INSERT INTO record values(?,?,?,?,?,?,?,?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, r.getRequestID() + "");
		pstmt.setString(2, "trade");
		pstmt.setString(3, r.getUserID() + "");
		pstmt.setString(4, r.getSellerID() + "");
		pstmt.setString(5, r.getAmount() + "");
		pstmt.setString(6, r.getTimestamp() + "");
		pstmt.setString(7, null);
		pstmt.setString(8, (r.getState() == true ? 1 : 0) + "");
		pstmt.execute();
	}
	
	/**
	 * 加载JDBC驱动程序
	 */
	public static void loadDriver() throws Exception
	{
		Class.forName("com.mysql.jdbc.Driver");
	}
}
