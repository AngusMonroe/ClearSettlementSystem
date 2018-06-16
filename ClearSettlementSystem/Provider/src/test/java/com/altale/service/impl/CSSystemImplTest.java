package com.altale.service.impl;

import com.altale.Launcher;
import com.altale.service.CSException.OperatorIdOutOfRangeException;
import com.altale.service.CSException.RequestException;
import com.altale.service.CSException.TimeOutOfRangeException;
import com.altale.service.connection.CSSdbinfo;
import com.altale.service.connection.SQLConnection;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CSSystemImplTest {
    //连接数据库
    @Before
    public void bef1()
    {
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
}