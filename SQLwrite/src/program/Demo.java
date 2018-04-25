package program;

import java.sql.Date;

import request.Result;
import request.RechargeRequest;
//import request.TradeRequest;
//import request.WithdrawRequest;

import sql_connection.SQLConnection;

/**
 * @author ������
 * @version 0.2
 * 
 * 2018/4/25
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
 * �˻�: ruangong
 * ����: ruangong
 * ���ݿ���: bill
 * ��: record
 */

public class Demo
{
	public static void main(String[] args)
	{
		//һ�����ͳ�ֵ���������
		try
		{
			//�������ݿ�����
			SQLConnection sqlConnection = new SQLConnection(
					"jdbc:mysql://localhost:3306/bill?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=GMT%2B8",
					"ruangong",
					"ruangong",
					"record"
					);
			
			/**
			 * ��ֵ����RechargeRequest
			 * ��������WithdrawRequest
			 * ��������TradeRequest
			 */
			
			//������ֵ����ע��˴�Date��Ϊjava.sql.Date
			RechargeRequest rechargeRequest = new RechargeRequest(123, 456, 7.89f, true, new Date(0), "֧����");
			
			//��������
			sqlConnection.insert(rechargeRequest);
			
			//��ȡ�����Ϣ
			Result result = rechargeRequest.getResult();
			
			//���ˣ��˻�"ruangong"�����ݿ�"bill"�еı�"record"�Ѿ������������ֵ�����˵�
			System.out.println(result.toString());
		}
		catch (Exception e)
		{
			//�쳣����
			e.printStackTrace();
		}
		//���֡����������˵�ͬ��
	}
}