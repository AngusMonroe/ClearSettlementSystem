package com.altale.service;
import java.util.Date;
public interface IUserService {
    public int Recharge(int UserID,Date RequestTime,int RequestID,
                        float Money,boolean Way,boolean RequestState);
    public int Withdraw(int UserID,Date RequestTime,int RequestID,
                        float Money,boolean Way,boolean RequestState);
    public int Consume(int UserID,int MerchantId,Date RequestTime,int RequestID,
                       float Money,boolean RequestState);
}
