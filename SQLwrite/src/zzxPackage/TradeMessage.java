package zzxPackage;

import java.sql.Date;

public class TradeMessage {
	private int requestID;
	private int userID;
	private int merchantID;
	private Date requestTime;
	private float amount;
	private int operateStatus;
	private int requestStatus;
	
	public TradeMessage(int requestID, int userID, int merchantID, Date requestTime,
			float amount, int operateStatus, int requestStatus) {
		
		this.requestID = requestID;
		this.userID = userID;
		this.merchantID = merchantID;
		this.requestTime = requestTime;
		this.amount = amount;
		this.operateStatus = operateStatus;
		this.requestStatus = requestStatus;
	}
}
