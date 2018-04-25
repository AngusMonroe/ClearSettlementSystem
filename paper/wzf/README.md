开放的接口：
创建数据库连接
SQLConnection(String url, String user, String password, String table)
充值请求创建
RechargeRequest(int requestID, int userID, float amount, boolean state, Date timestamp, String method)
提现请求创建
WithdrawRequest(int requestID, int userID, float amount, boolean state, Date timestamp, String method)
交易请求创建
TradeRequest(int requestID, int userID, int sellerID, float amount, boolean state, Date timestamp)
发送请求
void SQLConnection.insert(Request request)
获取结果信息
Result Request.getResult()

实现的内容：
将请求录入已连接的数据库，并返回结果信息

开发计划：
即将完成
