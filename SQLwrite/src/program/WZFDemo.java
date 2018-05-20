package program;

import java.sql.Date;

import request.RechargeRequest;
import request.WithdrawRequest;
import request.TradeRequest;

import sql_connection.SQLConnection;

/**
 * @author 王正飞
 * @version 0.4
 * 
 * 2018/5/20
 * Eclipse Oxygen
 */

/**
 * 环境配置:
 * 
 * Java:
 * JavaSE-1.8
 * 
 * JDBC驱动:
 * mysql-connector-java-8.0.11.jar (下载地址: https://dev.mysql.com/downloads/file/?id=477058)
 * 
 * SQL:
 * MySQL (Community) Server 8.0 (下载地址: https://dev.mysql.com/downloads/file/?id=476477)
 */

public class WZFDemo
{
	public static void main(String[] args)
	{
		//一个发送充值请求的例子
		try
		{
			/**
			 * 创建数据库连接
			 * 服务器: localhost
			 * 端口号: 3306
			 * 账户: ruangong
			 * 密码: ruangong
			 * 数据库名: css
			 * 充值请求表: recharge
			 * 提现请求表: withdraw
			 * 交易请求表: trade
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
			 * 充值请求：RechargeRequest
			 * 提现请求：WithdrawRequest
			 * 交易请求：TradeRequest
			 */
			
			//创建请求
			RechargeRequest rechargeRequest = new RechargeRequest(123, 456, 7.89f, true, true, new Date(0));
			WithdrawRequest withdrawRequest = new WithdrawRequest(987, 654, 3.21f, true, false, new Date(1));
			TradeRequest tradeRequest = new TradeRequest(111, 222, 333, 4.44f, true, new Date(2));
			
			//发送请求，并返回请求ID，若失败返回-1
			int rechargeRequestID = sqlConnection.sendRequest(rechargeRequest);
			int withdrawRequestID = sqlConnection.sendRequest(withdrawRequest);
			int tradeRequestID = sqlConnection.sendRequest(tradeRequest);
			
			//至此，账户"ruangong"的数据库"css"的表"recharge"、"withdraw"和"trade"中已经分别添加了上述请求账单
			System.out.println("Recharge Request ID: " + rechargeRequestID);
			System.out.println("Withdraw Request ID: " + withdrawRequestID);
			System.out.println("Trade Request ID: " + tradeRequestID);
		}
		catch (Exception e)
		{
			//异常处理
			e.printStackTrace();
		}
	}
}