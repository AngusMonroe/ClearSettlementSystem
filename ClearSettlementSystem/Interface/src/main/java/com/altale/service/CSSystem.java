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
     * @throws RequestException 处理请求发送时出现的异常
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
     * @throws RequestException 处理请求发送时出现的异常
     */
    public String Withdraw(String requestID, String userID, double amount, boolean method, String requestTime) throws RequestException;
    
    /**
	 * 交易接口
	 * @param requestID 请求ID
	 * @param userID 用户ID
	 * @param merchantID 商家ID
	 * @param amount 交易金额
	 * @param requestTime 请求时间 "YY-MM-DD HH:MM:SS"
     * @return 返回请求ID，若失败返回"-1"
	 * @throws RequestException 处理请求发送时出现的异常
	 */
    public String Trade(String requestID, String userID, String merchantID, double amount,String requestTime) throws RequestException;

	/**
	 * 查询对账信息接口，要求15天内
	 * @param startTime 起始时间 "YY-MM-DD HH:MM:SS"
	 * @param endTime 终止时间 "YY-MM-DD HH:MM:SS"
	 * @param operatorID 操作类型：0-充值，1-提现，2-消费
     * @return JSON格式字符串
	 * @throws TimeOutOfRangeException 函数调用时间超出范围
	 * @throws OperatorIdOutOfRangeException 操作类型有误
	 */
    public String QueryRecord(String startTime,String endTime,int operatorID) throws TimeOutOfRangeException,OperatorIdOutOfRangeException;

    /**
     * 下载对账文件，要求15天内
     * @param startTime 起始时间 "YY-MM-DD HH:MM:SS"
     * @param endTime 终止时间 "YY-MM-DD HH:MM:SS"
     * @return JSON格式对账数据字符串
     * @throws TimeOutOfRangeException 函数调用时间超出范围
     * @throws OperatorIdOutOfRangeException 操作类型有误
     */
    public String DownloadFile(String startTime,String endTime) throws TimeOutOfRangeException,OperatorIdOutOfRangeException;
}
