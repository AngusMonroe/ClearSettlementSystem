package zzxPackage;

import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;

public class WithdrawMessage implements Message{
	private String requestID;
	private String userID;
	private Date requestTime;
	private float amount;
	private int method;
	
	public WithdrawMessage(String requestID, String userID, 
			Date requestTime, float amount, int method) {
		
		this.requestID = requestID;
		this.userID = userID;
		this.requestTime = requestTime;
		this.amount = amount;
		this.method = method;
	}
	
	@Override
	public JSONObject toJSONObject() {
		JSONObject jsObject = new JSONObject();
		try {
			jsObject.put("requestID", this.requestID);
			jsObject.put("userID", this.userID);
			jsObject.put("requestTime", DateUtil.dateToString(this.requestTime, 0));
			jsObject.put("amount", this.amount);
			jsObject.put("method", this.method);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return jsObject;
	}
}
