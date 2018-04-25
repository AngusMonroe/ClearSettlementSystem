package zzxPackage;

// ��ʼ��Ϊ����ƽ̨ģʽ
public class Message {
	public int sellerID;
	public float moneyAmount; 
	public int operator; // 1:����ƽ̨	0: ��ƽ̨ȡ
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
