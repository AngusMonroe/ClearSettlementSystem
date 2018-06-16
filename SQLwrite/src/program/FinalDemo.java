package program;

import org.json.JSONArray;

import request.RechargeRequest;
import request.WithdrawRequest;
import request.TradeRequest;

import sql_connection.SQLConnection;
import zzxPackage.DateUtil;
import zzxPackage.JSONUtil;

public class FinalDemo
{
	public static void main(String[] args)
	{
		try
		{
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
			
			/* 1 recharge */
			System.out.println("---- 1.Recharge ----");
			RechargeRequest rechargeRequest = new RechargeRequest("123", "456", 7.89, false, "2018-6-13 15:42:00");
			sqlConnection.sendRequest(rechargeRequest);
			rechargeRequest.display();
			System.out.println("\n\n");
			
			/* 2 withdraw */
			System.out.println("---- 2.Withdraw ----");
			WithdrawRequest withdrawRequest = new WithdrawRequest("987", "654", 3.21, false, "2018-6-13 16:43:01");
			sqlConnection.sendRequest(withdrawRequest);
			withdrawRequest.display();
			System.out.println("\n\n");
			
			/* 3 trade */
			System.out.println("---- 3.Trade ----");
			TradeRequest tradeRequest = new TradeRequest("001", "111", "555", 1.01, false, "2018-6-13 17:44:02");
			sqlConnection.sendRequest(tradeRequest);
			tradeRequest.display();
			System.out.println("\n\n");
			
			/* 4 clearing */
			System.out.println("---- 4.Clearing ----");
			sqlConnection.getClearing(DateUtil.strToDate("2018-06-13"));
			sqlConnection.getClearing(DateUtil.strToDate("2018-06-14"));
			sqlConnection.getClearing(DateUtil.strToDate("2018-06-15"));
			sqlConnection.getClearing(DateUtil.strToDate("2018-06-16"));
			sqlConnection.getClearing(DateUtil.strToDate("2018-06-17"));
			System.out.println("\n\n");
			
			/* 5 download file */
			System.out.println("---- 5.Download File ----");
			JSONArray jsonArray = JSONUtil.getClearingFromFile(DateUtil.strToDate("2018-06-14"));
			System.out.println(jsonArray.toString());
			System.out.println("\n\n");
			
			/* 6 query record */
			System.out.println("---- 6.Query Record ----");
			JSONArray jsArray2 = sqlConnection.findQueryRecord(2);
			System.out.println(jsArray2.toString());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}