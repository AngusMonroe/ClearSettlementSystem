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
			
			/* 1 */
			RechargeRequest rechargeRequest = new RechargeRequest("123", "456", 7.89, false, "2018-6-10 15:42:00");
			sqlConnection.sendRequest(rechargeRequest);
			
			/* 2 */
			WithdrawRequest withdrawRequest = new WithdrawRequest("987", "654", 3.21, false, "2018-6-10 16:43:01");
			sqlConnection.sendRequest(withdrawRequest);
			
			/* 3 */
			TradeRequest tradeRequest_1 = new TradeRequest("001", "111", "555", 1.01, false, "2018-6-10 17:44:02");
			TradeRequest tradeRequest_2 = new TradeRequest("002", "222", "555", 2.02, false, "2018-6-10 18:45:03");
			TradeRequest tradeRequest_3 = new TradeRequest("003", "333", "555", 3.03, false, "2018-6-10 19:46:04");
			sqlConnection.sendRequest(tradeRequest_1);
			sqlConnection.sendRequest(tradeRequest_2);
			sqlConnection.sendRequest(tradeRequest_3);
			
			/* 4 */
			sqlConnection.getClearing(DateUtil.strToDate("2018-06-10"));
			sqlConnection.getClearing(DateUtil.strToDate("2018-06-11"));
			sqlConnection.getClearing(DateUtil.strToDate("2018-06-12"));
			sqlConnection.getClearing(DateUtil.strToDate("2018-06-13"));
			sqlConnection.getClearing(DateUtil.strToDate("2018-06-14"));
			sqlConnection.getClearing(DateUtil.strToDate("2018-06-15"));
			sqlConnection.getClearing(DateUtil.strToDate("2018-06-16"));
			
			/* 5 */
			JSONArray jsonArray = JSONUtil.getClearingFromFile(DateUtil.strToDate("2018-06-11"));
			System.out.println(jsonArray.toString());
			
			/* 6 */
			JSONArray jsArray2 = sqlConnection.findQueryRecord(2);
			System.out.println(jsArray2.toString());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}