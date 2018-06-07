package com.altale.service.impl;

import com.altale.service.CSException.*;
import com.altale.service.CSSystem;
import com.altale.util.*;
import com.altale.service.request.*;
import com.altale.service.connection.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Date;


@Service("CSSystem")
public class CSSystemImpl implements CSSystem{
    private Logger logger = LoggerFactory.getLogger(CSSystemImpl.class);
    /*
        logger.info("用户登录：[username:{}, password:{}]", username, password);
    */
    private SQLConnection sqlConnection;
    @Override
    public String Recharge(String requestID, String userID, double amount, boolean method, String requestTime) throws RequestException {
        if(!CheckInputUtil.checkInput(requestID,userID,amount)){
            throw new RequestException("充值参数错误");
        }
        try{
            sqlConnection= new SQLConnection
                    (
                        "jdbc:mysql://" +
                                CSSdbinfo.server +  ":" +
                                CSSdbinfo.port + "/" +
                                CSSdbinfo.database + "?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=GMT%2B8",
                                CSSdbinfo.username,
                                CSSdbinfo.password
                    );
        }catch (Exception ex){
            ex.printStackTrace();
            return "-1";
        }
        RechargeRequest rechargeRequest = new RechargeRequest(requestID,userID,amount,method,requestTime);
        logger.info("Recharge " +requestID+" "+userID);
        return sqlConnection.sendRequest(rechargeRequest);
    }

    @Override
    public String Withdraw(String requestID, String userID, double amount, boolean method, String requestTime) throws RequestException {
        if(!CheckInputUtil.checkInput(requestID,userID,amount)) {
            throw new RequestException("提现参数错误");
        }
        try{
            sqlConnection= new SQLConnection
                    (
                            "jdbc:mysql://" +
                                    CSSdbinfo.server +  ":" +
                                    CSSdbinfo.port + "/" +
                                    CSSdbinfo.database + "?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=GMT%2B8",
                            CSSdbinfo.username,
                            CSSdbinfo.password
                    );
        }catch (Exception ex){
            ex.printStackTrace();
            return "-1";
        }
        WithdrawRequest withdrawRequest=new WithdrawRequest(requestID,userID,amount,method,requestTime);
        logger.info("Withdraw " +requestID+" "+userID);
        return sqlConnection.sendRequest(withdrawRequest);
    }

    @Override
    public String Trade(String requestID, String userID, String merchantID, double amount, String requestTime) throws RequestException {
        if(!CheckInputUtil.checkInput(requestID,userID,merchantID,amount)) {
            throw new RequestException("转账参数错误");
        }
        try{
            sqlConnection= new SQLConnection
                    (
                            "jdbc:mysql://" +
                                    CSSdbinfo.server +  ":" +
                                    CSSdbinfo.port + "/" +
                                    CSSdbinfo.database + "?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=GMT%2B8",
                            CSSdbinfo.username,
                            CSSdbinfo.password
                    );
        }catch (Exception ex){
            ex.printStackTrace();
            return "-1";
        }
        TradeRequest tradeRequest=new TradeRequest(requestID,userID,merchantID,amount,false,requestTime);
        logger.info("Trade " +requestID+" "+userID);
        return sqlConnection.sendRequest(tradeRequest);
    }

    @Override
    public String QueryRecord(String startTime, String endTime, int operatorID) throws TimeOutOfRangeException, OperatorIdOutOfRangeException {
        try{
            sqlConnection= new SQLConnection
                    (
                            "jdbc:mysql://" +
                                    CSSdbinfo.server +  ":" +
                                    CSSdbinfo.port + "/" +
                                    CSSdbinfo.database + "?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=GMT%2B8",
                            CSSdbinfo.username,
                            CSSdbinfo.password
                    );
        }catch (Exception ex){
            ex.printStackTrace();
            return "";
        }
        return sqlConnection.findQueryRecord(DateUtil.strToDate(startTime),DateUtil.strToDate(endTime),operatorID).toString();
    }

    @Override
    public String DownloadFile(String requestTime) throws TimeOutOfRangeException {
        return JSONUtil.getClearingFromFile(DateUtil.strToDate(requestTime)).toString();
    }
}
