package com.altale.service.impl;

import com.altale.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service("userService")
public class UserServiceImpl implements IUserService{
    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
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
    public int Recharge(int UserID, Date RequestTime, int RequestID,
                        float Money, boolean Way, boolean RequestState)
    {
        return -1;
    }
    public int Withdraw(int UserID,Date RequestTime,int RequestID,
                        float Money,boolean Way,boolean RequestState)
    {
        return -1;
    }
    public int Consume(int UserID,int MerchantId,Date RequestTime,int RequestID,
                       float Money,boolean RequestState)
    {
        return -1;
    }
}
