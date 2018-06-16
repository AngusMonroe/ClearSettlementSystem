package com.altale.service.impl;

import com.altale.Launcher;
import com.altale.service.clearing.ClearingThread;
import com.altale.service.connection.CSSdbinfo;
import com.altale.service.connection.SQLConnection;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertTrue;

public class ClearingTest {
    @Before
    public void init(){
        try
        {
            Launcher.sqlConnection = new SQLConnection
                    (
                            "jdbc:mysql://" +
                                    CSSdbinfo.server +  ":" +
                                    CSSdbinfo.port + "/" +
                                    "css" + "?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=GMT%2B8",
                            "ruangong",
                            "ruangong"
                    );
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    @Test
    public void clearing1(){
        try
        {
            SQLConnection sqlConnection = new SQLConnection
                    (
                            "jdbc:mysql://" +
                                    CSSdbinfo.server +  ":" +
                                    CSSdbinfo.port + "/" +
                                    "css" + "?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=GMT%2B8",
                            "ruangong",
                            "ruangong"
                    );
            assertTrue((0-sqlConnection.clearing())<0.00001);

        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    @Test
    public void clearing2(){
        CSSystemImpl cs=new CSSystemImpl();
        cs.Trade("4","20","21",2000,"2018-06-16 12:00:00");
        cs.Trade("5","20","21",200,"2018-06-16 12:01:00");
        cs.Trade("6","20","21",1000,"2018-06-16 12:02:00");
        cs.Trade("7","40","23",2000,"2018-06-16 12:03:00");


        try
        {
            SQLConnection sqlConnection = new SQLConnection
                    (
                            "jdbc:mysql://" +
                                    CSSdbinfo.server +  ":" +
                                    CSSdbinfo.port + "/" +
                                    "css" + "?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=GMT%2B8",
                            "ruangong",
                            "ruangong"
                    );
            assertTrue((520-sqlConnection.clearing())<0.00001);

        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    @Test
    public void clearing3()
    {
        CSSystemImpl cs=new CSSystemImpl();
        cs.Trade("8","41","23",200,"2018-06-16 12:04:00");
        cs.Trade("9","42","24",1000000,"2018-06-16 12:05:00");

        try
        {
            SQLConnection sqlConnection = new SQLConnection
                    (
                            "jdbc:mysql://" +
                                    CSSdbinfo.server +  ":" +
                                    CSSdbinfo.port + "/" +
                                    "css" + "?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=GMT%2B8",
                            "ruangong",
                            "ruangong"
                    );
            assertTrue((100020-sqlConnection.clearing())<0.00001);

        }catch (Exception e)
        {
            e.printStackTrace();
        }
        try
        {
            SQLConnection sqlConnection = new SQLConnection
                    (
                            "jdbc:mysql://" +
                                    CSSdbinfo.server +  ":" +
                                    CSSdbinfo.port + "/" +
                                    "css" + "?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=GMT%2B8",
                            "ruangong",
                            "ruangong"
                    );
            assertTrue((0-sqlConnection.clearing())<0.00001);

        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
