package zzxPackage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import exception.TimeOutOfRangeException;

public class JSONUtil {

   public static JSONArray getClearingFromFile() throws JSONException{
        String ans = "";
        for(int i = 15; i >= 1; i--) {
            Date date = DateUtil.toDayBefore(new Date(), i);
            JSONArray dateArray = getClearingFromFile(date);
            ans += dateArray.toString();
        }
        return new JSONArray(ans);
    }
	   
	public static JSONArray getClearingFromFile(Date date) {
		
		// ���ʱ�䷶Χ
		Date before15day = DateUtil.toDayBefore(new Date(), 15);
		if (date.before(before15day)) {
			throw new TimeOutOfRangeException();
		}
		
		// ���ļ���ȡ�ַ���
		String filename = DateUtil.dateToString(date, 1) + ".json";
        StringBuilder sBuilder = new StringBuilder();
        try{
        	FileReader fileReader = new FileReader(Constant.jspath + filename);
            BufferedReader br = new BufferedReader(fileReader);
            String s = null;
            while((s = br.readLine())!=null){//ʹ��readLine������һ�ζ�һ��
            	sBuilder.append(System.lineSeparator()+s);
            }
            br.close();    
        }catch(Exception e){
            e.printStackTrace();
        }
        
        // ת��
        String source = sBuilder.toString();
        JSONArray jsArray = null;
		try {
			jsArray = new JSONArray(source);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return jsArray;
	}
	
	/**
 	 * ��ʽ��
 	 * [{
 	 * 	"merchantID": 1,
 	 *	"amount": 10,
 	 *	"fee": 1,
 	 * }, {
 	 *	"merchantID": 2,
 	 *	"amount": 20,
 	 *	"fee": 2,
 	 * }]
 	 * �������ļ�Ϊ[]
	 */
	public static <T extends Message> JSONArray MessagesToArray(ArrayList<T> messages) {
		if(messages == null)
			throw new NullPointerException();
		
		int index = 0;
		JSONArray jsArray = new JSONArray();
		for(Message message : messages) {
			JSONObject jsObject = message.toJSONObject();
			try {
				jsArray.put(index++, jsObject);
			} catch (JSONException e) {
				e.printStackTrace();
			}	
		}
		return jsArray;
	}
	
	/** ��jsObject���ַ���д��path�µ��ļ����ļ���Ϊ����*/
	public static void writeFile(JSONArray jsArray) {
		if (jsArray == null)
			throw new NullPointerException();
		String text = jsArray.toString();
		String fileName = DateUtil.getCurrDate() + ".json";
		String filePath = Constant.jspath + fileName;
        File testdir=new File(Constant.jspath);
        if(!testdir.exists()){
            testdir.mkdirs();
        }
		
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
	
	public static void main(String[] args) throws JSONException {
//		JSONObject jsObject = new JSONObject();
//		jsObject.put("merchantID", 1);
//		jsObject.put("amount", 2);
//		jsObject.put("fee", 3);
//		JSONObject jsObject2 = new JSONObject();
//		jsObject2.put("merchantID", 4);
//		jsObject2.put("amount", 5);
//		jsObject2.put("fee", 6);
//		JSONArray jsArray = new JSONArray();
//		jsArray.put(0, jsObject);
//		jsArray.put(1, jsObject2);
//		System.out.println(jsArray.toString());
		
//		JSONObject jsObject = CMessageToObject(new ClearingMessage(1, 10, 1));
//		JSONArray jsArray = new JSONArray();
//		jsArray.put(0, jsObject);
//		jsArray.put(1, jsObject);
//		jsArray.put(2, jsObject);
////		writeFile(jsObject);
//		System.out.println(jsArray.toString());
	}
	
}
