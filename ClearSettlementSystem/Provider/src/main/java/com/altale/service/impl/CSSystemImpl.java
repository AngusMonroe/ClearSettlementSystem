package com.altale.service.impl;

import com.altale.service.CSException.*;
import com.altale.service.CSSystem;
import com.altale.util.*;
import com.altale.service.request.*;
import com.altale.service.connection.SQLConnection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Date;


@Service("CSSystem")
public class CSSystemImpl implements CSSystem{
    private Logger logger = LoggerFactory.getLogger(CSSystemImpl.class);
    /*
    @Override
    public boolean login(String username, String password) {
        logger.info("用户登录：[username:{}, password:{}]", username, password);
        if (username != null && password != null && username.equals(password)) {
            logger.info("用户校验通过。[username:{}]", username);
            return true;
        }
        logger.info("用户校验失败！[username:{}]", username);
        return false;
    }
    */

    /**
     * 创建数据库连接
     * 服务器: localhost
     * 端口号: 3306
     * 账户: ruangong
     * 密码: ruangong
     * 数据库名: css
     * 充值请求表: recharge
     * 提现请求表: withdraw
     * 交易请求表: trade
     */
    private String server = "localhost";
    private String port = "3306";
    private String database = "css";
    private String username = "ruangong";
    private String password = "ruangong";
    private SQLConnection sqlConnection;
    @Override
    public String Recharge(String requestID, String userID, double amount, boolean method, String requestTime) throws RequestException {
        String ret="-1";
        try{
            sqlConnection= new SQLConnection
                    (
                        "jdbc:mysql://" +
                                server +  ":" +
                                port + "/" +
                                database + "?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=GMT%2B8",
                        username,
                        password
                    );
            RechargeRequest rechargeRequest = new RechargeRequest(requestID,userID,amount,method,requestTime);
            ret=sqlConnection.sendRequest(rechargeRequest);
            return ret;
        }catch (Exception ex){
            throw new RequestException("清结算系统无法连接到数据库");
        }
    }

    @Override
    public String Withdraw(String requestID, String userID, double amount, boolean method, String requestTime) throws RequestException {
        String ret="-1";
        try{
            sqlConnection= new SQLConnection
                    (
                            "jdbc:mysql://" +
                                    server +  ":" +
                                    port + "/" +
                                    database + "?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=GMT%2B8",
                            username,
                            password
                    );
            WithdrawRequest withdrawRequest=new WithdrawRequest(requestID,userID,amount,method,requestTime);
            ret=sqlConnection.sendRequest(withdrawRequest);
            return ret;
        }catch (Exception ex){
            throw new RequestException("清结算系统无法连接到数据库");
        }
    }

    @Override
    public String Trade(String requestID, String userID, String merchantID, double amount, String requestTime) throws RequestException {
        String ret="-1";
        try{
            sqlConnection= new SQLConnection
                    (
                            "jdbc:mysql://" +
                                    server +  ":" +
                                    port + "/" +
                                    database + "?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=GMT%2B8",
                            username,
                            password
                    );
            TradeRequest tradeRequest=new TradeRequest(requestID,userID,merchantID,amount,false,requestTime);
            ret=sqlConnection.sendRequest(tradeRequest);
            return ret;
        }catch (Exception ex){
            throw new RequestException("清结算系统无法连接到数据库");
        }
    }

    @Override
    public String QueryRecord(String startTime, String endTime, int operatorID) throws TimeOutOfRangeException, OperatorIdOutOfRangeException {
        String ret="";
        try{
            sqlConnection= new SQLConnection
                    (
                        "jdbc:mysql://" +
                                server +  ":" +
                                port + "/" +
                                database + "?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=GMT%2B8",
                        username,
                        password
                    );
            return sqlConnection.findQueryRecord(DateUtil.strToDate(startTime),DateUtil.strToDate(endTime),operatorID).toString();
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return ret;
    }

    @Override
    public String DownloadFile(String startTime, String endTime) throws TimeOutOfRangeException, OperatorIdOutOfRangeException {
        return JSONUtil.getClearingFromFile(DateUtil.strToDate(startTime)).toString();
    }
}
