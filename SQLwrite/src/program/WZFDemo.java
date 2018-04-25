package program;

import java.sql.Date;

import request.Result;
import request.RechargeRequest;
import request.WithdrawRequest;
import request.TradeRequest;

import sql_connection.SQLConnection;

/**
 * @author 王正飞
 * @version 0.3
 * 
 * 2018/4/25
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
 * 账户: ruangong
 * 密码: ruangong
 * 数据库名: bill
 * 表: record
 */

public class WZFDemo
{
	public static void main(String[] args)
	{
		//一个发送充值请求的例子
		try
		{
			//创建数据库连接
			SQLConnection sqlConnection = new SQLConnection(
					"jdbc:mysql://localhost:3306/bill?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=GMT%2B8",
					"ruangong",
					"ruangong",
					"record"
					);
			
			/**
			 * 充值请求：RechargeRequest
			 * 提现请求：WithdrawRequest
			 * 交易请求：TradeRequest
			 */
			
			//创建请求
			RechargeRequest rechargeRequest = new RechargeRequest(123, 456, 7.89f, true, new Date(0), "支付宝");
			WithdrawRequest withdrawRequest = new WithdrawRequest(987, 654, 3.21f, true, new Date(0), "微信");
			TradeRequest tradeRequest = new TradeRequest(111, 222, 333, 4.44f, true, new Date(0));
			
			//发送请求
			sqlConnection.insert(rechargeRequest);
			sqlConnection.insert(withdrawRequest);
			sqlConnection.insert(tradeRequest);
			
			//获取结果信息
			Result rechargeResult = rechargeRequest.getResult();
			Result wiResultResult = withdrawRequest.getResult();
			Result tradeResult = tradeRequest.getResult();
			
			//至此，账户"ruangong"的数据库"bill"中的表"record"已经添加了上述充值请求账单
			System.out.println(rechargeResult.toString());
			System.out.println(wiResultResult.toString());
			System.out.println(tradeResult.toString());
		}
		catch (Exception e)
		{
			//异常处理
			e.printStackTrace();
		}
	}
}