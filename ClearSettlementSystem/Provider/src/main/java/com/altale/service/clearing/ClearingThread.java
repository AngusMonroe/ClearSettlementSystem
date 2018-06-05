package com.altale.service.clearing;

import com.alibaba.dubbo.common.logger.Logger;
import com.alibaba.dubbo.common.logger.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import com.altale.service.connection.SQLConnection;
import java.util.Date;
import java.text.SimpleDateFormat;
public class ClearingThread {
    //private static final Logger logger = LoggerFactory.getLogger(ClearingThread.class);
    //@Scheduled(cron = "0/5 * * * * ?") //<-从0秒开始每5秒执行一次
    @Scheduled(cron = "0 0 23 * * ?") //<-每天23点的cron表达式大概是这样
    public void Clearing(){
        /*
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        System.out.println(df.format(new Date())+"：完成清分");// new Date()为获取当前系统时间
        */
        String server = "localhost";
        String port = "3306";
        String database = "css";
        String username = "ruangong";
        String password = "ruangong";
        try {
            SQLConnection sqlConnection = new SQLConnection
                    (
                            "jdbc:mysql://" +
                                    server + ":" +
                                    port + "/" +
                                    database + "?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=GMT%2B8",
                            username,
                            password
                    );

            sqlConnection.clearing();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
