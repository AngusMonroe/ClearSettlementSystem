package sql_connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import request.RechargeRequest;
import request.WithdrawRequest;
import request.TradeRequest;

/**
 * ���ݿ�������
 */
public class SQLConnection
{
	private String url;
	private String user;
	private String password;
	
	/**
	 * ���ݿ������๹�캯��
	 * @param url URL
	 * @param user �û���
	 * @param password ����
	 */
	public SQLConnection(String url, String user, String password) throws Exception
	{
		this.url = url;
		this.user = user;
		this.password = password;
	}
	
	/**
	 * �����ݿ�����ֵ�˵�
	 * @param r ��ֵ����
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
	 * �����ݿ���������˵�
	 * @param r ��ֵ����
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
	 * �����ݿ���뽻���˵�
	 * @param r ��ֵ����
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
	 * ����JDBC��������
	 */
	public static void loadDriver() throws Exception
	{
		Class.forName("com.mysql.jdbc.Driver");
	}
}
