package program;

import java.util.ArrayList;

import org.json.JSONArray;

import sql_connection.SQLConnection;
import zzxPackage.JSONUtil;;

public class ZZXDemo {
	public static void main(String[] args) {
		try {
			SQLConnection sqlConnection = new SQLConnection(
					"jdbc:mysql://localhost:3306/bill?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=GMT%2B8",
					"ruangong",
					"record"
					);
			JSONArray jsArray = sqlConnection.clearing();
			JSONUtil.writeFile(jsArray);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}