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
 * ���ݿ�������
 */
public class SQLConnection
{
	private String table;
	private Connection connection;
	private PreparedStatement pstmt;
	
	/**
	 * ���ݿ������๹�캯��
	 * @param url URL
	 * @param user �û���
	 * @param password ����
	 * @param database ���ݿ�
	 * @param table ��
	 */
	public SQLConnection(String url, String user, String password, String table) throws Exception
	{
		this.table = table;

		connection = DriverManager.getConnection(url,user,password);
	}
	
	/**
	 * �����ݿ�����ֵ�˵�
	 * @param r ��ֵ����
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
	 * �����ݿ���������˵�
	 * @param r ��������
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
	 * �����ݿ���뽻���˵�
	 * @param r ��������
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
	
	// ����sellerID,count��record_type��status
	public ArrayList<Message> clearing() throws Exception {
		
		String sql = "SELECT seller_id, user_id, amount "
				+ "FROM record "
				+ "WHERE seller_id IS NOT null and user_id IS NOT null " // ��ȷ��ת��
				+ "GROUP BY seller_id";
		Statement statement = connection.createStatement();
		ResultSet rs = statement.executeQuery(sql);
		
		ArrayList<Message> messages = new ArrayList<Message>();
		int notExist = -1;
		Message currMessage = new Message(notExist); // ������seller
		while(rs.next()){
            // ͨ���ֶμ���
            int sellerID  = rs.getInt("seller_id");
            int userID = rs.getInt("user_id");
            float amount = rs.getFloat("amount");
            
            // �����Ϣ
            if (currMessage.sellerID == notExist && currMessage.sellerID != sellerID) {
            	currMessage = new Message(sellerID);
            } else if(currMessage.sellerID != notExist && currMessage.sellerID != sellerID) {
            	messages.add(currMessage);
            	currMessage = new Message(sellerID);
            }
            final double texRatio = 0.1; 	// TODO:finish��˰
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
