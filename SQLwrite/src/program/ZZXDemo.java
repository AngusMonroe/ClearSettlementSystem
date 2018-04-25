package program;

import java.util.ArrayList;

import sql_connection.SQLConnection;
import zzxPackage.Message;
import zzxPackage.Util;;

/**
 * @author 张政勋
 * @version 0.1
 * 
 * 2018/4/25
 * Eclipse Oxygen
 */

public class ZZXDemo {
	public static void main(String[] args) {
		//创建数据库连接
		try {
			SQLConnection sqlConnection = new SQLConnection(
					"jdbc:mysql://localhost:3306/bill?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=GMT%2B8",
					"ruangong",
					"ruangong",
					"record"
					);
			ArrayList<Message> messages = sqlConnection.clearing();
			Util.writeFile(messages);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
