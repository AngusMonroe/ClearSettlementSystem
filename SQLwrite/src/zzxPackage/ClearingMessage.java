package zzxPackage;

import org.json.JSONException;
import org.json.JSONObject;

// 初始化为放入平台模式
public class ClearingMessage implements Message{
	public int merchantID;
	public float amount; //商家得到的
	public float fee; // 手续费
	
	public ClearingMessage(int merchantID) {
		this(merchantID, 0, 0);
	}
	
	public ClearingMessage(int merchantID, float amount, float fee) {
		this.merchantID = merchantID;
		this.amount = amount;
		this.fee = fee;
	}
	
	/**
	 * 格式：
	 * {
	 *	"merchantID": 1,
	 *  "amount": 10,
	 *	"fee": 1,
	 * }
	 */
	@Override
	public JSONObject toJSONObject() {
		JSONObject jsObject = new JSONObject();
		try {
			jsObject.put("merchantID", this.merchantID);
			jsObject.put("amount", this.amount);
			jsObject.put("fee", this.fee);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return jsObject;
	}
	
}


