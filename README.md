ClearSettlementSystem
=====================

### Divide the work

- xjx 文档
- zzh dubbo、缓存
- lzx 测试
- zzx 清分、查询、下载对账文件
- wzf 充值、提现、消费

### Requirement

- java
- jdk 1.8
- 开发工具：Intellij IDEA
- 缓存框架：Redis
- 日志管理：SLF4J、Log4j2
- 数据库：MySQL

### API

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
	| request_id | 请求ID | int | 10 | 否 | 主键 |
	| user_id | 用户账户ID | int | 10 | 否 | |
	| request_time | 请求时间 | date | 14 | 否 | |
	| amount | 金额 | float | 11 | 否 |  单位：元<br>保留两位小数 |
	| method | 充值/提现方式 | bool | 1 | 否 |  0-微信<br>1-支付宝 |
	| operate_status | 操作状态 | bool | 1 | 否 | 0-待清分<br>1-已清分 |
	| request_status | 请求状态 | bool | 1 | 否 | 若为false，进行缓存 |

- 提现表

	表名：withdraw
	
	字段：
	
	| 字段名 | 中文描述 | 类型 | 长度 | 是否可以为空 | 备注 |
	|-----|------|----|----|--------|----|
	| request_id | 请求ID | int | 10 | 否 | 主键 |
	| user_id | 用户账户ID | int | 10 | 否 | |
	| request_time | 请求时间 | date | 14 | 否 | |
	| amount | 金额 | float | 11 | 否 |  单位：元<br>保留两位小数 |
	| method | 充值/提现方式 | bool | 1 | 否 |  0-微信<br>1-支付宝 |
	| operate_status | 操作状态 | bool | 1 | 否 | 0-待清分<br>1-已清分 |
	| request_status | 请求状态 | bool | 1 | 否 | 若为false，进行缓存 |

- 消费表

	表名：trade
	
	字段：
	
	| 字段名 | 中文描述 | 类型 | 长度 | 是否可以为空 | 备注 |
	|-----|------|----|----|--------|----|
	| request_id | 请求ID | int | 10 | 否 | 主键 |
	| user_id | 用户账户ID | int | 10 | 否 | |
	| merchant_id | 商户账户ID | int | 10 | 否 | |
	| request_time | 请求时间 | date | 14 | 否 | |
	| amount | 金额 | float | 11 | 否 |  单位：元<br>保留两位小数 |
	| operate_status | 操作状态 | bool | 1 | 否 | 0-待清分<br>1-已清分 |
	| request_status | 请求状态 | bool | 1 | 否 | 若为false，进行缓存 |