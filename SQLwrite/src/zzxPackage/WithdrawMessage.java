package zzxPackage;

import java.sql.Date;

public class WithdrawMessage {
	private int requestID;
	private int userID;
	private Date requestTime;
	private float amount;
	private int method;
	private int requestStatus;
	
	public WithdrawMessage(int requestID, int userID, Date requestTime,
			float amount, int method, int requestStatus) {
		
		this.requestID = requestID;
		this.userID = userID;
		this.requestTime = requestTime;
		this.amount = amount;
		this.method = method;
		this.requestStatus = requestStatus;
	}
}
