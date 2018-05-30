package com.altale.util;

import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;

public class TradeMessage implements Message{
    private String requestID;
    private String userID;
    private String merchantID;
    private Date requestTime;
    private double amount;
    private int operateStatus;

    public TradeMessage(String requestID, String userID, String merchantID,
                        Date requestTime, double amount, int operateStatus) {

        this.requestID = requestID;
        this.userID = userID;
        this.merchantID = merchantID;
        this.requestTime = requestTime;
        this.amount = amount;
        this.operateStatus = operateStatus;
    }

    @Override
    public JSONObject toJSONObject() {
        JSONObject jsObject = new JSONObject();
        try {
            jsObject.put("requestID", this.requestID);
            jsObject.put("userID", this.userID);
            jsObject.put("merchantID", this.merchantID);
            jsObject.put("requestTime", DateUtil.dateToString(this.requestTime, 0));
            jsObject.put("amount", this.amount);
            jsObject.put("operateStatus", this.operateStatus);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsObject;
    }
}
