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
	
	// ���·��
	private static final String path = "account/";
	
	public static void writeFile(ArrayList<ClearingMessage> clearingMessages) {
		JSONArray jsArray = transform(clearingMessages);
		writeFile(jsArray);
	}
	
//	��ʽ��
//	{
//		"merchantID": 1,
//		"amount": 10,
//		"fee": 1,
//	}
	private static JSONObject transform(ClearingMessage clearingMessage) {
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

//	��ʽ��
//	[{
//		"merchantID": 1,
//		"amount": 10,
//		"fee": 1,
//	}, {
//		"merchantID": 2,
//		"amount": 20,
//		"fee": 2,
//	}]
//	�������ļ�Ϊ[]
	private static JSONArray transform(ArrayList<ClearingMessage> clearingMessages) {
		if(clearingMessages == null)
			throw new NullPointerException();
		
		int index = 0;
		JSONArray jsArray = new JSONArray();
		for(ClearingMessage clearingMessage : clearingMessages) {
			JSONObject jsObject = transform(clearingMessage);
			try {
				jsArray.put(index++, jsObject);
			} catch (JSONException e) {
				e.printStackTrace();
			}	
		}
		return jsArray;
	}
	
	/** ��jsObject���ַ���д��path�µ��ļ����ļ���Ϊ����*/
	private static void writeFile(JSONArray jsArray) {
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
        ps.print(text);			   // ���ļ���д���ַ���  
        // ps.append("something"); // �����еĻ���������ַ��� 
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
	
	public static void main(String[] args) throws JSONException {
		
		JSONObject jsObject = transform(new ClearingMessage(1, 10, 1));
		JSONArray jsArray = new JSONArray();
		jsArray.put(0, jsObject);
		jsArray.put(1, jsObject);
		jsArray.put(2, jsObject);
//		writeFile(jsObject);
		System.out.println(jsArray.toString());
	}
	
}
