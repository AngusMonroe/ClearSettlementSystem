package program;

import java.util.ArrayList;

import org.json.JSONArray;

import sql_connection.SQLConnection;
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
			
			JSONArray jsArray = sqlConnection.clearing();
			JSONUtil.writeFile(jsArray);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}