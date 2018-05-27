package zzxPackage;

public class RechargeMessage {
	private int requestID;
	private int userID;
	private int requestTime;
	private int amount;
	private int method;
	private int requestStatus;
	
	public RechargeMessage(int requestID, int userID, int requestTime,
			int amount, int method, int requestStatus) {
		
		this.requestID = requestID;
		this.userID = userID;
		this.requestTime = requestTime;
		this.amount = amount;
		this.method = method;
		this.requestStatus = requestStatus;
	}
}
