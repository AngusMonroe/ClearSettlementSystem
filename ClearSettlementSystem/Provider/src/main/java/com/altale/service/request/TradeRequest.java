package com.altale.service.request;

import com.altale.service.CSException.RequestException;

/**
 * 交易请求类
 */
public final class TradeRequest extends Request
{
    /**
     * 交易请求类构造函数
     * @param requestID 请求ID
     * @param userID 用户ID
     * @param merchantID 商家ID
     * @param amount 交易金额
     * @param operateStatus 操作状态
     * @param requestTime 请求时间
     * @throws RequestException
     */
    public TradeRequest(String requestID, String userID, String merchantID, double amount, boolean operateStatus, String requestTime) throws RequestException
    {
        super(requestID, userID, merchantID, amount, operateStatus, false, requestTime);
    }
}