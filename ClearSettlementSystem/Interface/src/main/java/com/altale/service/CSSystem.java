package com.altale.service;
import java.util.Date;
import com.altale.service.CSException.*;
public interface CSSystem {
    /**
     * 充值接口
     * @param requestID 请求ID
     * @param userID 用户ID
     * @param amount 充值金额
     * @param method 充值方式：false-微信，true-支付宝
     * @param requestTime 请求时间 "YY-MM-DD HH:MM:SS"
     * @return 返回请求ID，若失败返回"-1"
     * @throws RequestException
     */
    public String Recharge(String requestID, String userID, double amount, boolean method, String requestTime) throws RequestException;

    /**
     *提现接口
     * @param requestID 请求ID
     * @param userID 用户ID
     * @param amount 提现金额
     * @param method 提现方式：false-微信，true-支付宝
     * @param requestTime 请求时间 "YY-MM-DD HH:MM:SS"
     * @return 返回请求ID，若失败返回"-1"
     * @throws RequestException
     */
    public String Withdraw(String requestID, String userID, double amount, boolean method, String requestTime) throws RequestException;
    
    /**
	 * 交易接口
	 * @param requestID 请求ID
	 * @param userID 用户ID
	 * @param merchantID 商家ID
	 * @param amount 交易金额
	 * @param operateStatus 操作状态
	 * @param requestTime 请求时间 "YY-MM-DD HH:MM:SS"
     * @return 返回请求ID，若失败返回"-1"
	 * @throws RequestException
	 */
    public String Trade(String requestID, String userID, String merchantID, double amount, boolean operateStatus, String requestTime) throws RequestException;
    //查询记录接口
    public String QueryRecord(String startTime,String endTime,int kind) throws TimeOutOfRangeException,OperatorIdOutOfRangeException;
}
