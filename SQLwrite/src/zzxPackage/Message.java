package zzxPackage;

// 初始化为放入平台模式
public class Message {
	public int sellerID;
	public float moneyAmount; 
	public int operator; // 1:放入平台	0: 从平台取
	public boolean status;
	
	public Message(int sellerID) {
		this(sellerID, 0, true);
	}
	
	public Message(int sellerID, float moneyCount, boolean status) {
		this.sellerID = sellerID;
		this.moneyAmount = moneyCount;
		this.status = status;
		this.operator = 0;  
	}
	
	public void fromPlatform(double d) {
		moneyAmount += d;
	}
	
}
