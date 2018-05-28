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

import com.mysql.cj.util.DataTypeUtil;

public class JSONUtil {

	public static JSONArray getJSArrayFromFile(Date date) {
		String filename = DateUtil.dateToString(date, 1) + ".json";
        StringBuilder sBuilder = new StringBuilder();
        try{
        	FileReader fileReader = new FileReader(Constant.jspath + filename);
            BufferedReader br = new BufferedReader(fileReader);
            String s = null;
            while((s = br.readLine())!=null){//使用readLine方法，一次读一行
                result.append(System.lineSeparator()+s);
            }
            br.close();    
        }catch(Exception e){
            e.printStackTrace();
        }
        
        String source = sBuilder.toString();
        JSONArray jsArray = new JSONArray(source);
	}
	
	/**
 	 * 格式：
 	 * [{
 	 * 	"merchantID": 1,
 	 *	"amount": 10,
 	 *	"fee": 1,
 	 * }, {
 	 *	"merchantID": 2,
 	 *	"amount": 20,
 	 *	"fee": 2,
 	 * }]
 	 * 空数组文件为[]
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
	
	/** 将jsObject的字符串写入path下的文件，文件名为日期*/
	public static void writeFile(JSONArray jsArray) {
		if (jsArray == null)
			throw new NullPointerException();
		String text = jsArray.toString();
		String fileName = DateUtil.getCurrDate() + ".json";
		String filePath = Constant.jspath + fileName;
		
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
