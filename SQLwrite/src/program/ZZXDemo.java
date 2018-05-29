package program;

import java.util.ArrayList;
import java.util.Date;

import org.json.JSONArray;

import sql_connection.SQLConnection;
import zzxPackage.DateUtil;
import zzxPackage.JSONUtil;;

public class ZZXDemo {
	public static void main(String[] args) {
		try {
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
			
			sqlConnection.clearing();
			
			Date startTime = DateUtil.strToDate("2018-05-26");
			Date endTime = DateUtil.strToDate("2018-05-28");
			int kind = 1;
			JSONArray jsonArray = sqlConnection.findQueryRecord(startTime, endTime, kind);
			System.out.println(jsonArray.toString());
			
			Date date  = DateUtil.strToDate("2018-05-29");
			JSONArray clearingArray = JSONUtil.getClearingFromFile(date);
			System.out.println(clearingArray.toString());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}