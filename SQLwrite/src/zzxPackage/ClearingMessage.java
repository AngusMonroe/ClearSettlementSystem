package zzxPackage;

import org.json.JSONException;
import org.json.JSONObject;

// ��ʼ��Ϊ����ƽ̨ģʽ
public class ClearingMessage implements Message{
	public String merchantID;
	public double amount; //�̼ҵõ���
	public double fee; // ������
	
	public ClearingMessage(String merchantID) {
		this(merchantID, 0, 0);
	}
	
	public ClearingMessage(String merchantID, double amount, double fee) {
		this.merchantID = merchantID;
		this.amount = amount;
		this.fee = fee;
	}
	
	/**
	 * ��ʽ��
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


