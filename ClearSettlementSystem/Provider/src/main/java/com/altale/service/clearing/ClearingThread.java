package com.altale.service.clearing;

import com.alibaba.dubbo.common.logger.Logger;
import com.alibaba.dubbo.common.logger.LoggerFactory;
import com.altale.service.connection.CSSdbinfo;
import org.springframework.scheduling.annotation.Scheduled;
import com.altale.service.connection.SQLConnection;
import java.util.Date;
import java.text.SimpleDateFormat;
public class ClearingThread {
    private static final Logger logger = LoggerFactory.getLogger(ClearingThread.class);
    //@Scheduled(cron = "0/5 * * * * ?") //<-从0秒开始每5秒执行一次
    @Scheduled(cron = "0 0 23 * * ?") //<-每天23点的cron表达式大概是这样
    public static void Clearing(){
        try {
            SQLConnection sqlConnection= new SQLConnection
                    (
                            "jdbc:mysql://" +
                                    CSSdbinfo.server +  ":" +
                                    CSSdbinfo.port + "/" +
                                    CSSdbinfo.database + "?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=GMT%2B8",
                            CSSdbinfo.username,
                            CSSdbinfo.password
                    );
            sqlConnection.clearing();
            logger.info("清分成功");
        }catch (Exception ex){
            ex.printStackTrace();
            logger.error("清分失败");
        }
    }
}
