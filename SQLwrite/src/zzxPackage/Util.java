package zzxPackage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Calendar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Util {
	
	// 相对路径
	private static final String path = "account/";
	
	public static void writeFile(ArrayList<ClearingMessage> clearingMessages) {
		JSONArray jsArray = CMessagesToArray(clearingMessages);
		writeFile(jsArray);
	}

//	格式：
//	{
//		"merchantID": 1,
//		"amount": 10,
//		"fee": 1,
//	}
	public static JSONObject CMessageToObject(ClearingMessage clearingMessage) {
		if (clearingMessage == null)
			throw new NullPointerException();
		JSONObject jsObject = new JSONObject();
		try {
			jsObject.put("merchantID", clearingMessage.merchantID);
			jsObject.put("amount", clearingMessage.amount);
			jsObject.put("fee", clearingMessage.fee);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return jsObject;
	}

//	格式：
//	[{
//		"merchantID": 1,
//		"amount": 10,
//		"fee": 1,
//	}, {
//		"merchantID": 2,
//		"amount": 20,
//		"fee": 2,
//	}]
//	空数组文件为[]
	public static JSONArray CMessagesToArray(ArrayList<ClearingMessage> clearingMessages) {
		if(clearingMessages == null)
			throw new NullPointerException();
		
		int index = 0;
		JSONArray jsArray = new JSONArray();
		for(ClearingMessage clearingMessage : clearingMessages) {
			JSONObject jsObject = CMessageToObject(clearingMessage);
			try {
				jsArray.put(index++, jsObject);
			} catch (JSONException e) {
				e.printStackTrace();
			}	
		}
		return jsArray;
	}
	
	/** @return eg: 2018_9_13 */
	public static String getDate(){
		Calendar cal = Calendar.getInstance();
		String date, day, month, year;
		year = String.valueOf(cal.get(Calendar.YEAR));
		month = String.valueOf(cal.get(Calendar.MONTH) + 1);
		day = String.valueOf(cal.get(Calendar.DATE));
		date = year + "_" + month + "_" + day;
		return date;
	}
	
	/** 将jsObject的字符串写入path下的文件，文件名为日期*/
	public static void writeFile(JSONArray jsArray) {
		if (jsArray == null)
			throw new NullPointerException();
		String text = jsArray.toString();
		String fileName = getDate() + ".json";
		String filePath = path + fileName;
		
		File file = new File(filePath);  
        PrintStream ps = null;
		try {
			ps = new PrintStream(new FileOutputStream(file));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}  
        ps.print(text);			   // 往文件里写入字符串  
        // ps.append("something"); // 在已有的基础上添加字符串 
	}
	
	public static void main(String[] args) throws JSONException {
		
		JSONObject jsObject = CMessageToObject(new ClearingMessage(1, 10, 1));
		JSONArray jsArray = new JSONArray();
		jsArray.put(0, jsObject);
		jsArray.put(1, jsObject);
		jsArray.put(2, jsObject);
//		writeFile(jsObject);
		System.out.println(jsArray.toString());
	}
	
}
