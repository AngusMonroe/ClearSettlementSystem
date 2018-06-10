package com.altale;

import buaa.jj.accountservice.api.*;
import buaa.jj.accountservice.exceptions.*;
import com.altale.service.CSSystem;
import com.altale.service.connection.CSSdbinfo;
import com.altale.service.connection.SQLConnection;
import com.altale.util.BeanFactoryUtil;
import com.altale.util.SystemDetails;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Launcher {

    private static Log logger = LogFactory.getLog(Launcher.class);
    private static ApplicationContext ctx;
    public static AccountService accountService;
    public static SQLConnection sqlConnection;
    /**
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("=======================");
        System.out.println("        Core包启动          ");
        SystemDetails.outputDetails();
        System.out.println("=======================");

        getLocalip();
        // 初始化spring
        logger.info("开始初始化core服务");
        BeanFactoryUtil.init();

        ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        accountService=(AccountService)ctx.getBean("accountService");

        accountService.CSSystemReady();
        logger.info("清结算系统启动");

        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                accountService.CSSystemClosing();
                logger.info("清结算系统结束运行");
            }
        }));

        try {
            sqlConnection= new SQLConnection
                    (
                            "jdbc:mysql://" +
                                    CSSdbinfo.server +  ":" +
                                    CSSdbinfo.port + "/" +
                                    CSSdbinfo.database + "?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=GMT%2B8",
                            CSSdbinfo.username,
                            CSSdbinfo.password
                    );
            logger.info("数据库已连接");
        }catch (Exception ex){
            logger.error("数据库连接失败");
            sqlConnection=null;
            ex.printStackTrace();
        }

        try{
            System.in.read();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 取得本机ip地址 注意：Spring RmiServiceExporter取得本机ip的方法：InetAddress.getLocalHost()
     */
    private static void getLocalip() {
        try {
            System.out.println("服务暴露的ip: "
                    + java.net.InetAddress.getLocalHost().getHostAddress());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}