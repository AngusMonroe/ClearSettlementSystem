package zzxPackage;

// 初始化为放入平台模式
public class Message {
	public int merchantID;
	public float amount; //商家得到的
	public float fee; // 手续费
	
	public Message(int merchantID) {
		this(merchantID, 0, 0);
	}
	
	public Message(int merchantID, float amount, float fee) {
		this.merchantID = merchantID;
		this.amount = amount;
		this.fee = fee;
	}
	
}
