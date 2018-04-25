package program;

import java.sql.Date;

import request.WriteException;
import request.Result;
import request.RechargeRequest;
//import request.TradeRequest;
//import request.WithdrawRequest;

/**
 * @author ������
 * @version 0.1
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
			/**
			 * �˴����Ż�:
			 * ����������ʱ�ȶ�ε��ù�����sendRequest
			 */
			
			//ע��˴�Date��Ϊjava.sql.Date
			RechargeRequest rechargeRequest = new RechargeRequest(123, 456, 7.89f, true, new Date(0), "֧����");
			
			//������������result��
			Result result;
			
			//�˴����������Ƿ��ͳɹ�
			boolean ok = rechargeRequest.sendRequest();
			
			if (ok)
			{
				//�����ͳɹ�
				result = rechargeRequest.getResult();

				//���ˣ��˻�"ruangong"�����ݿ�"bill"�еı�"record"�Ѿ������������ֵ�����˵�
				System.out.println(result.toString());
			}
			else
			{
				//������ʧ��
				System.out.println("ʧ��");
			}
		}
		catch (WriteException e)
		{
			//WriteException �쳣����
			e.printStackTrace();
		}
		//���֡����������˵�ͬ��
	}
}