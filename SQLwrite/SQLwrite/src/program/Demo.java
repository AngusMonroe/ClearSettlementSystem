package program;

import java.sql.Date;

import request.WriteException;
import request.Result;
import request.RechargeRequest;
//import request.TradeRequest;
//import request.WithdrawRequest;

/**
 * @author 王正飞
 * @version 0.1
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

public class Demo
{
	public static void main(String[] args)
	{
		//一个发送充值请求的例子
		try
		{
			/**
			 * 此处可优化:
			 * 多条请求暂时先多次调用构造与sendRequest
			 */
			
			//注意此处Date类为java.sql.Date
			RechargeRequest rechargeRequest = new RechargeRequest(123, 456, 7.89f, true, new Date(0), "支付宝");
			
			//输出结果保存在result中
			Result result;
			
			//此处返回请求是否发送成功
			boolean ok = rechargeRequest.sendRequest();
			
			if (ok)
			{
				//请求发送成功
				result = rechargeRequest.getResult();

				//至此，账户"ruangong"的数据库"bill"中的表"record"已经添加了上述充值请求账单
				System.out.println(result.toString());
			}
			else
			{
				//请求发送失败
				System.out.println("失败");
			}
		}
		catch (WriteException e)
		{
			//WriteException 异常处理
			e.printStackTrace();
		}
		//提现、交易请求账单同理
	}
}