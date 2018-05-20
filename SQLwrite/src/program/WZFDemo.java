package program;

import java.sql.Date;

import request.RechargeRequest;
import request.WithdrawRequest;
import request.TradeRequest;

import sql_connection.SQLConnection;

/**
 * @author ������
 * @version 0.4
 * 
 * 2018/5/20
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
			RechargeRequest rechargeRequest = new RechargeRequest(123, 456, 7.89f, true, true, new Date(0));
			WithdrawRequest withdrawRequest = new WithdrawRequest(987, 654, 3.21f, true, false, new Date(1));
			TradeRequest tradeRequest = new TradeRequest(111, 222, 333, 4.44f, true, new Date(2));
			
			//�������󣬲���������ID����ʧ�ܷ���-1
			int rechargeRequestID = sqlConnection.sendRequest(rechargeRequest);
			int withdrawRequestID = sqlConnection.sendRequest(withdrawRequest);
			int tradeRequestID = sqlConnection.sendRequest(tradeRequest);
			
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