###充值：
RechargeRequest构造方法

| 参数类型 | 参数名 | 参数描述 | 备注 |
| ---- | ---- | ---- | ---- |
| int | requestID | 请求ID | 无 |
| int | userID | 用户ID | 无 |
| float | amount | 充值金额 | 无 |
| boolean | requestStatus | 请求状态 | 无 |
| boolean | method | 充值方式 | false-微信 true-支付宝 |
| Date | requestTime | 请求时间 | 类型: java.sql.Date |

###提现：
WithdrawRequest构造方法

| 参数类型 | 参数名 | 参数描述 | 备注 |
| ---- | ---- | ---- | ---- |
| int | requestID | 请求ID | 无 |
| int | userID | 用户ID | 无 |
| float | amount | 提现金额 | 无 |
| boolean | requestStatus | 请求状态 | 无 |
| boolean | method | 提现方式 | false-微信 true-支付宝 |
| Date | requestTime | 请求时间 | 类型: java.sql.Date |

###交易：
TradeRequest构造方法

| 参数类型 | 参数名 | 参数描述 | 备注 |
| ---- | ---- | ---- | ---- |
| int | requestID | 请求ID | 无 |
| int | userID | 用户ID | 无 |
| int | merchantID | 商户ID | 无 |
| float | amount | 提现金额 | 无 |
| boolean | requestStatus | 请求状态 | 无 |
| Date | requestTime | 请求时间 | 类型: java.sql.Date |

###连接数据库：
SQLConnection构造方法

| 参数类型 | 参数名 | 参数描述 | 备注 |
| ---- | ---- | ---- | ---- |
| String | url | URL | 例："jdbc:mysql://服务器:端口号/数据库名称?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=GMT%2B8" |
| String | username | 用户名 | 无 |
| String | password | 密码 | 无 |

###发送请求：
sendRequest方法

说明：类SQLConnection中有sendRequest方法以发送请求。
成功返回请求ID，失败返回-1.

| 参数类型 | 参数名 | 参数描述 | 备注 |
| ---- | ---- | ---- | ---- |
| Request | request | 请求(RechargeRequest、WithdrawRequest或TradeRequest) | 无 |