package program;

import request.RechargeRequest;
import request.WithdrawRequest;
import request.TradeRequest;

import sql_connection.SQLConnection;

/**
 * @author ������
 * @version 0.5
 * 
 * 2018/5/28
 * Eclipse Oxygen
 */

/**
 * ��������:
 * 
 * Java:
 * JavaSE-1.8
 * 
 * JDBC����:
 * mysql-connector-java-8.0.11.jar (���ص�ַ: https://dev.mysql.com/downloads/file/?id=477058)
 * 
 * SQL:
 * MySQL (Community) Server 8.0 (���ص�ַ: https://dev.mysql.com/downloads/file/?id=476477)
 */

public class WZFDemo
{
	public static void main(String[] args)
	{
		//һ�����ͳ�ֵ���������
		try
		{
			/**
			 * �������ݿ�����
			 * ������: localhost
			 * �˿ں�: 3306
			 * �˻�: ruangong
			 * ����: ruangong
			 * ���ݿ���: css
			 * ��ֵ�����: recharge
			 * ���������: withdraw
			 * ���������: trade
			 */
			String server = "localhost";
			String port = "3306";
			String database = "css";
			String username = "ruangong";
			String password = "ruangong";
			
			SQLConnection sqlConnection = new SQLConnection
			(
				"jdbc:mysql://" + 
				server +  ":" +
				port + "/" + 
				database + "?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=GMT%2B8", 
				username, 
				password
			);
			
			/**
			 * ��ֵ����RechargeRequest
			 * ��������WithdrawRequest
			 * ��������TradeRequest
			 */
			
			//��������
			RechargeRequest rechargeRequest = new RechargeRequest("123", "456", 7.89f, true, "2018-5-28 15:42:00");
			WithdrawRequest withdrawRequest = new WithdrawRequest("987", "654", 3.21f, true, "2019-6-29 16:43:01");
			TradeRequest tradeRequest = new TradeRequest("111", "222", "333", 4.44f, true, "2020-7-30 17:44:02");
			
			//�������󣬲���������ID����ʧ�ܷ���"-1"
			String rechargeRequestID = sqlConnection.sendRequest(rechargeRequest);
			String withdrawRequestID = sqlConnection.sendRequest(withdrawRequest);
			String tradeRequestID = sqlConnection.sendRequest(tradeRequest);
			
			//���ˣ��˻�"ruangong"�����ݿ�"css"�ı�"recharge"��"withdraw"��"trade"���Ѿ��ֱ���������������˵�
			System.out.println("Recharge Request ID: " + rechargeRequestID);
			System.out.println("Withdraw Request ID: " + withdrawRequestID);
			System.out.println("Trade Request ID: " + tradeRequestID);
		}
		catch (Exception e)
		{
			//�쳣����
			e.printStackTrace();
		}
	}
}