package zzxPackage;

// ��ʼ��Ϊ����ƽ̨ģʽ
public class Message {
	public int merchantID;
	public float amount; //�̼ҵõ���
	public float fee; // ������
	
	public Message(int merchantID) {
		this(merchantID, 0, 0);
	}
	
	public Message(int merchantID, float amount, float fee) {
		this.merchantID = merchantID;
		this.amount = amount;
		this.fee = fee;
	}
	
}
