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

public class RechargeTest {
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

    /*recharge*/
    @Test
    public void recharge1() throws Exception{
        CSSystemImpl css = new CSSystemImpl();
        try {
            css.Recharge("", "1621", 233, true, "2018-06-06 12:00:30");
        }catch (Exception ex){
            assertTrue(ex instanceof RequestException);
        }

    }//requestID为空

    @Test
    public void recharge2() throws Exception{
        CSSystemImpl css = new CSSystemImpl();
        try {
            css.Recharge("abc", "1621", 233, true, "2018-06-06 12:00:30");
        }catch (Exception ex){
            assertTrue(ex instanceof RequestException);
        }
    }//requestID错误

    @Test
    public void recharge3() throws Exception{
        CSSystemImpl css = new CSSystemImpl();
        try{
            css.Recharge("23456","",233,true,"2018-06-06 12:00:30");
        }catch (Exception ex){
            assertTrue(ex instanceof RequestException);
        }
    }//userID为空
    @Test
    public void recharge4() throws Exception{
        CSSystemImpl css = new CSSystemImpl();
        try {
            css.Recharge("23456", "abc", 233, true, "2018-06-06 12:00:30");
        }catch (Exception ex){
            assertTrue(ex instanceof RequestException);
        }
    }//userID错误
    @Test
    public void recharge5() throws Exception{
        CSSystemImpl css = new CSSystemImpl();
        try{
            css.Recharge("23456","1621",-100,true,"2018-06-06 12:00:30");
        }catch (Exception ex){
            assertTrue(ex instanceof RequestException);
        }
    }//amount为负
    @Test
    public void recharge6() throws Exception{
        CSSystemImpl css = new CSSystemImpl();
        try{
            css.Recharge("104","1621",21,true,"2018-00-01 12:00:30");
        }catch (Exception ex){
            assertTrue(ex instanceof RequestException);
        }
    }//月份错误1
    @Test
    public void recharge7() throws Exception{
        CSSystemImpl css = new CSSystemImpl();
        try{
            css.Recharge("1001","1621",21,true,"2018-13-01 12:00:30");
        }catch (Exception ex){
            assertTrue(ex instanceof RequestException);
        }
    }//月份错误2
    @Test
    public void recharge7_day() throws Exception{
        CSSystemImpl css = new CSSystemImpl();
        try{
            css.Recharge("505","1621",21,true,"2018-11-00 12:00:30");
        }catch (Exception ex){
            assertTrue(ex instanceof RequestException);
        }
    }//日期为0
    @Test
    public void recharge8() throws Exception{
        CSSystemImpl css = new CSSystemImpl();
        try{
            css.Recharge("1001","1621",21,true,"2018-05-32 12:00:30");
        }catch (Exception ex){
            assertTrue(ex instanceof RequestException);
        }
    }//大月错误
    @Test
    public void recharge9() throws Exception{
        CSSystemImpl css = new CSSystemImpl();
        try{
            css.Recharge("1001","1621",21,true,"2018-06-31 12:00:30");
        }catch (Exception ex){
            assertTrue(ex instanceof RequestException);
        }
    }//小月错误
    @Test
    public void recharge10() throws Exception{
        CSSystemImpl css = new CSSystemImpl();
        try{
            css.Recharge("1001","1621",21,true,"2018-02-29 12:00:30");
        }catch (Exception ex){
            assertTrue(ex instanceof RequestException);
        }
    }//非闰年2月
    @Test
    public void recharge11() throws Exception{
        CSSystemImpl css = new CSSystemImpl();
        try {
            css.Recharge("1001", "1621", 21, true, "2008-02-30 12:00:30");
        }catch (Exception ex){
            assertTrue(ex instanceof RequestException);
        }
    }//闰年2月
    @Test
    public void recharge12() throws Exception{
        CSSystemImpl css = new CSSystemImpl();
        try {
            css.Recharge("1001", "1621", 21, true, "-2018-06-06-");
        }catch (Exception ex){
            assertTrue(ex instanceof RequestException);
        }
    }//时间格式有误
    @Test
    public void recharge13() throws Exception{
        CSSystemImpl css = new CSSystemImpl();
        try {
            css.Recharge("1001", "1621", 21, true, "2018-06-06 24:00:00");
        }catch (Exception ex){
            assertTrue(ex instanceof RequestException);
        }
    }//小时错误
    @Test
    public void recharge14() throws Exception{
        CSSystemImpl css = new CSSystemImpl();
        try {
            css.Recharge("1001", "1621", 21, true, "2018-06-06 12:60:00");
        }catch (Exception ex){
            assertTrue(ex instanceof RequestException);
        }
    }//分错误
    @Test
    public void recharge15() throws Exception{
        CSSystemImpl css = new CSSystemImpl();
        try {
            css.Recharge("1001", "1621", 21, true, "2018-06-06 12:00:60");
        }catch (Exception ex){
            assertTrue(ex instanceof RequestException);
        }
    }//秒错误
    @Test
    public void recharge16() throws Exception{
        CSSystemImpl css = new CSSystemImpl();
        try {
            css.Recharge("1001", "1621", 21, true, "");
        }catch (Exception ex){
            assertTrue(ex instanceof RequestException);
        }
    }//时间空


    //正确参数
    @Test
    public void rechargeA() throws Exception{
        CSSystemImpl css = new CSSystemImpl();
        assertEquals("1001",
                css.Recharge("1001","1621",21,true,"2018-05-30 00:00:00"));
    }
    @Test
    public void rechargeB() throws Exception{
        CSSystemImpl css = new CSSystemImpl();
        assertEquals("1002",
                css.Recharge("1002","1621",21,true,"2018-06-30 00:00:00"));
    }
    @Test
    public void rechargeC() throws Exception{
        CSSystemImpl css = new CSSystemImpl();
        assertEquals("1003",
                css.Recharge("1003","1621",21,true,"2018-02-28 00:00:00"));
    }
    @Test
    public void rechargeD() throws Exception{
        CSSystemImpl css = new CSSystemImpl();
        assertEquals("1004",
                css.Recharge("1004","1621",21,true,"2008-02-29 00:00:00"));
    }

}
