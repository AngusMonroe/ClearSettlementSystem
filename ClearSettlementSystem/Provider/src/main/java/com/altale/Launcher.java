package com.altale;

import buaa.jj.accountservice.api.*;
import buaa.jj.accountservice.exceptions.*;
import com.altale.service.CSSystem;
import com.altale.util.BeanFactoryUtil;
import com.altale.util.SystemDetails;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Launcher {

    private static Log logger = LogFactory.getLog(Launcher.class);

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


        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        AccountService accountService = (AccountService)ctx.getBean("accountService");
        accountService.CSSystemReady();


        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                ApplicationContext endctx = new ClassPathXmlApplicationContext("applicationContext.xml");
                AccountService accountService = (AccountService)endctx.getBean("accountService");
                accountService.CSSystemClosing();
                logger.info("清结算系统结束运行");
            }
        }));

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