package zzxPackage;

// ��ʼ��Ϊ����ƽ̨ģʽ
public class ClearingMessage {
	public int merchantID;
	public float amount; //�̼ҵõ���
	public float fee; // ������
	
	public ClearingMessage(int merchantID) {
		this(merchantID, 0, 0);
	}
	
	public ClearingMessage(int merchantID, float amount, float fee) {
		this.merchantID = merchantID;
		this.amount = amount;
		this.fee = fee;
	}
	
}


