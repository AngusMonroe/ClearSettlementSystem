package com.altale.service.impl;

import com.altale.Launcher;
import com.altale.service.CSException.*;
import com.altale.service.CSSystem;
import com.altale.util.*;
import com.altale.service.request.*;
import com.altale.service.connection.*;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;


@Service("CSSystem")
public class CSSystemImpl implements CSSystem{
    private Logger logger = LoggerFactory.getLogger(CSSystemImpl.class);
    /*
        logger.info("用户登录：[username:{}, password:{}]", username, password);
    */
    @Override
    public String Recharge(String requestID, String userID, double amount, boolean method, String requestTime) throws RequestException {
        logger.info("Recharge from " +requestID+"to "+userID+"for "+amount);
        if(!CheckInputUtil.checkInput(requestID,userID,amount)){
            throw new RequestException("充值参数错误");
        }
        if(Launcher.sqlConnection==null) return"-1";
        RechargeRequest rechargeRequest = new RechargeRequest(requestID,userID,amount,method,requestTime);
        return Launcher.sqlConnection.sendRequest(rechargeRequest);
    }

    @Override
    public String Withdraw(String requestID, String userID, double amount, boolean method, String requestTime) throws RequestException {
        logger.info("Withdraw from " +requestID+"to "+userID+"for "+amount);
        if(!CheckInputUtil.checkInput(requestID,userID,amount)) {
            throw new RequestException("提现参数错误");
        }
        if(Launcher.sqlConnection==null) return"-1";
        WithdrawRequest withdrawRequest=new WithdrawRequest(requestID,userID,amount,method,requestTime);
        return Launcher.sqlConnection.sendRequest(withdrawRequest);
    }

    @Override
    public String Trade(String requestID, String userID, String merchantID, double amount, String requestTime) throws RequestException {
        logger.info("Trade from " +requestID+"to  "+userID+"for  "+amount);
        if(!CheckInputUtil.checkInput(requestID,userID,merchantID,amount)) {
            throw new RequestException("转账参数错误");
        }
        if(Launcher.sqlConnection==null) return"-1";
        TradeRequest tradeRequest=new TradeRequest(requestID,userID,merchantID,amount,false,requestTime);
        return Launcher.sqlConnection.sendRequest(tradeRequest);
    }

    @Override
    public String QueryRecord(String startTime, String endTime, int operatorID) throws TimeOutOfRangeException, OperatorIdOutOfRangeException {
        logger.info("QueryRecord from "+startTime+"to  "+endTime);
        if(Launcher.sqlConnection==null) return"-1";
        if(startTime.equals("") || endTime.equals(""))
            return  Launcher.sqlConnection.findQueryRecord(operatorID).toString();
        else
            return Launcher.sqlConnection.findQueryRecord(DateUtil.strToDate(startTime),DateUtil.strToDate(endTime),operatorID).toString();
    }

    @Override
    public String DownloadFile(String requestTime) throws TimeOutOfRangeException {
        logger.info("DownloadFile on "+requestTime);
        Date date,before15day;
        try{
            if(requestTime.equals(""))
                date=DateUtil.toDayBefore(new Date(), 15);
            else
                date = DateUtil.strToDate(requestTime);
            before15day = DateUtil.toDayBefore(new Date(), 15);
        }catch (Exception ex){
            throw new TimeOutOfRangeException();
        }
        if (date.before(before15day)) {
            throw new TimeOutOfRangeException();
        }
        ArrayList<JSONObject> al=new ArrayList<JSONObject>();
        while(date.before(new Date())) {
            JSONObject json=new JSONObject();
            File file=new File(Constant.jspath+DateUtil.dateToString(date, 1) + ".json");
            if(file.exists()) {
                json.put("Name", DateUtil.dateToString(date, 1) + ".json");
                json.put("Address", Constant.urlpath + DateUtil.dateToString(date, 1) + ".json");
                al.add(json);
            }
            date = DateUtil.toDayBefore(date, -1);
        }
        return al.toString();
    }
}
