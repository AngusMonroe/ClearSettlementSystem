ClearSettlementSystem
=====================

### Requirement

- java

- jdk 1.8

- 开发工具：Intellij IDEA

- 日志管理：SLF4J、Log4j2

- 数据库：MySQL 8.0.11.0

建库sql参见根目录下的`css.sql`

### API

```java
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
     * 下载某一天的对账文件，要求15天内
     * @param requestTime 对账文件的时间 "YY-MM-DD"
     * @return JSON格式对账数据字符串
     * @throws TimeOutOfRangeException 函数调用时间超出范围
     */
    public String DownloadFile(String requestTime) throws TimeOutOfRangeException;
}
```

- 充值（虚拟账户->清结算）

	![屏幕快照 2018-04-25 下午11.33.07.png](https://i.loli.net/2018/04/25/5ae0a3512fa00.png)
	
- 提现（虚拟账户->清结算）

	![屏幕快照 2018-04-25 下午11.33.22.png](https://i.loli.net/2018/04/25/5ae0a35117b4b.png)
	
- 消费（虚拟账户->清结算）

	![屏幕快照 2018-04-25 下午11.33.40.png](https://i.loli.net/2018/04/25/5ae0a3515434f.png)
	
- 清分（清结算->虚拟账户）

	![屏幕快照 2018-04-25 下午11.47.13.png](https://i.loli.net/2018/04/25/5ae0a3526d909.png)
	
- 查询（综合运营平台->清结算）

	![屏幕快照 2018-04-25 下午11.47.25.png](https://i.loli.net/2018/04/25/5ae0a349770e8.png)
	
- 下载对账文件（综合运营平台->清结算）

	![屏幕快照 2018-04-25 下午11.47.35.png](https://i.loli.net/2018/04/25/5ae0a3489409b.png)
	
### 数据库设计

- 充值表

	表名：recharge
	
	字段：
	
	| 字段名 | 中文描述 | 类型 | 长度 | 是否可以为空 | 备注 |
	|-----|------|----|----|--------|----|
	| requestID | 请求ID | int | 11 | 否 | 主键 |
	| userID | 用户账户ID | int | 11 | 否 | |
	| requestTime | 请求时间 | date |  | 否 | |
	| amount | 金额 | float |  | 否 |  单位：元<br>保留两位小数 |
	| method | 充值/提现方式 | tinyint | 4 | 否 |  0-微信<br>1-支付宝 |
	| operateStatus | 操作状态 | tinyint | 4 | 否 | 0-待清分<br>1-已清分 |
	| requestStatus | 请求状态 | tinyint | 4 | 否 | 若为0，进行缓存 |

- 提现表

	表名：withdraw
	
	字段：
	
	| 字段名 | 中文描述 | 类型 | 长度 | 是否可以为空 | 备注 |
	|-----|------|----|----|--------|----|
	| requestID | 请求ID | int | 11 | 否 | 主键 |
	| userID | 用户账户ID | int | 11 | 否 | |
	| requestTime | 请求时间 | date |  | 否 | |
	| amount | 金额 | float |  | 否 |  单位：元<br>保留两位小数 |
	| method | 充值/提现方式 | tinyint | 4 | 否 |  0-微信<br>1-支付宝 |
	| operateStatus | 操作状态 | tinyint | 4 | 否 | 0-待清分<br>1-已清分 |
	| requestStatus | 请求状态 | tinyint | 4 | 否 | 若为0，进行缓存 |

- 消费表

	表名：trade
	
	字段：
	
	| 字段名 | 中文描述 | 类型 | 长度 | 是否可以为空 | 备注 |
	|-----|------|----|----|--------|----|
	| requestID | 请求ID | int | 11 | 否 | 主键 |
	| userID | 用户账户ID | int | 11 | 否 | |
	| merchantID | 商户账户ID | int | 11 | 否 | |
	| requestTime | 请求时间 | date |  | 否 | |
	| amount | 金额 | float |  | 否 |  单位：元<br>保留两位小数 |
	| operateStatus | 操作状态 | tinyint | 4 | 否 | 0-待清分<br>1-已清分 |
	| requestStatus | 请求状态 | tinyint | 4 | 否 | 若为0，进行缓存 |