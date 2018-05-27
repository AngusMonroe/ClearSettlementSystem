package program;

import java.util.ArrayList;

import sql_connection.SQLConnection;
import zzxPackage.ClearingMessage;
import zzxPackage.Util;;

public class ZZXDemo {
	public static void main(String[] args) {
		try {
			SQLConnection sqlConnection = new SQLConnection(
					"jdbc:mysql://localhost:3306/bill?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=GMT%2B8",
					"ruangong",
					"record"
					);
			ArrayList<ClearingMessage> clearingMessages = sqlConnection.clearing();
			Util.writeFile(clearingMessages);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}