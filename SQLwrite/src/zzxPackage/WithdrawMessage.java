package zzxPackage;

public class WithdrawMessage {
	private String requestID;
	private String userID;
	private String requestTime;
	private float amount;
	private int method;
	
	public WithdrawMessage(String requestID, String userID, 
			String requestTime, float amount, int method) {
		
		this.requestID = requestID;
		this.userID = userID;
		this.requestTime = requestTime;
		this.amount = amount;
		this.method = method;
	}
}
