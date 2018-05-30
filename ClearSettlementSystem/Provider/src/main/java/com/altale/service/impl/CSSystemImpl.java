package com.altale.service.impl;

import com.altale.service.CSException.OperatorIdOutOfRangeException;
import com.altale.service.CSException.RequestException;
import com.altale.service.CSException.TimeOutOfRangeException;
import com.altale.service.CSSystem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Date;

import com.altale.util.*;

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

    @Override
    public String Recharge(String requestID, String userID, double amount, boolean method, String requestTime) throws RequestException {
        return null;
    }

    @Override
    public String Withdraw(String requestID, String userID, double amount, boolean method, String requestTime) throws RequestException {
        return null;
    }

    @Override
    public String Trade(String requestID, String userID, String merchantID, double amount, String requestTime) throws RequestException {
        return null;
    }

    @Override
    public String QueryRecord(String startTime, String endTime, int operatorID) throws TimeOutOfRangeException, OperatorIdOutOfRangeException {
        return null;
    }

    @Override
    public String DownloadFile(String startTime, String endTime) throws TimeOutOfRangeException, OperatorIdOutOfRangeException {
        return null;
    }
}
