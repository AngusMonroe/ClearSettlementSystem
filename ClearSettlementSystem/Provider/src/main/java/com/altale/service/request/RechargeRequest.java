package com.altale.service.request;

import com.altale.service.CSException.RequestException;

/**
 * 充值请求类
 */
public final class RechargeRequest extends Request
{
    /**
     * 充值请求类构造函数
     * @param requestID 请求ID
     * @param userID 用户ID
     * @param amount 充值金额
     * @param method 方式:false-微信 或 true-支付宝
     * @param requestTime 请求时间
     * @throws RequestException
     */
    public RechargeRequest(String requestID, String userID, double amount, boolean method, String requestTime) throws RequestException
    {
        super(requestID, userID, "0", amount, false, method, requestTime);
    }
}