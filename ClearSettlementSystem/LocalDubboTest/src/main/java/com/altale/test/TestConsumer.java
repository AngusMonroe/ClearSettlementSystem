package com.altale.test;

import com.altale.service.CSSystem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestConsumer {
    private static Logger logger = LoggerFactory.getLogger(TestConsumer.class);

    public static void main(String args[]){
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        CSSystem csSystem=(CSSystem)ctx.getBean("csSystem");
        logger.info("执行结果：" + csSystem.Withdraw("12", "3456", 7.89, true, "2018-5-28 15:42:00"));
    }
}
