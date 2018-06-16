package com.altale.service.impl;

import com.altale.Launcher;
import com.altale.service.CSException.RequestException;
import com.altale.service.connection.CSSdbinfo;
import com.altale.service.connection.SQLConnection;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TradeTest {
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
    /*trade*/
    @Test
    public void trade1() throws Exception{
        CSSystemImpl css = new CSSystemImpl();
        try {
            css.Trade("", "1621", "2333", 99, "2018-06-06 01:00:30");
        }catch (Exception ex){
            assertTrue(ex instanceof RequestException);
        }
    }//requestID为空
    @Test
    public void trade2() throws Exception{
        CSSystemImpl css = new CSSystemImpl();
        try {
            css.Trade("abc", "1621", "2333", 99, "2018-06-06 01:00:30");
        }catch (Exception ex){
            assertTrue(ex instanceof RequestException);
        }
    }//requestID错误
    @Test
    public void trade3() throws Exception{
        CSSystemImpl css = new CSSystemImpl();
        try {
            css.Trade("404", "", "2333", 99, "2018-06-06 01:00:30");
        }catch (Exception ex){
            assertTrue(ex instanceof RequestException);
        }
    }//userID为空
    @Test
    public void trade4() throws Exception{
        CSSystemImpl css = new CSSystemImpl();
        try {
            css.Trade("404", "abc", "2333", 99, "2018-06-06 01:00:30");
        }catch (Exception ex){
            assertTrue(ex instanceof RequestException);
        }
    }//userID错误
    @Test
    public void trade5() throws Exception{
        CSSystemImpl css = new CSSystemImpl();
        try {
            css.Trade("404", "1621", "", 99, "2018-06-06 01:00:30");
        }catch (Exception ex){
            assertTrue(ex instanceof RequestException);
        }
    }//merchantID为空
    @Test
    public void trade6() throws Exception{
        CSSystemImpl css = new CSSystemImpl();
        try {
            css.Trade("404", "1621", "abc", 99, "2018-06-06 01:00:30");
        }catch (Exception ex){
            assertTrue(ex instanceof RequestException);
        }
    }//merchantID错误
    @Test
    public void trade7() throws Exception{
        CSSystemImpl css = new CSSystemImpl();
        try {
            css.Trade("404", "1621", "2333", -100, "2018-06-06 01:00:30");
        }catch (Exception ex){
            assertTrue(ex instanceof RequestException);
        }
    }//amount为负
    @Test
    public void trade8() throws Exception{
        CSSystemImpl css = new CSSystemImpl();
        try {
            css.Trade("404", "1621", "2333", 99, "2018-00-06 01:00:30");
        }catch (Exception ex){
            assertTrue(ex instanceof RequestException);
        }
    }//月错误1
    @Test
    public void trade9() throws Exception{
        CSSystemImpl css = new CSSystemImpl();
        try {
            css.Trade("404", "1621", "2333", 99, "2018-14-06 01:00:30");
        }catch (Exception ex){
            assertTrue(ex instanceof RequestException);
        }
    }//月错误2
    @Test
    public void trade10() throws Exception{
        CSSystemImpl css = new CSSystemImpl();
        try {
            css.Trade("404", "1621", "2333", 99, "2018-05-32 01:00:30");
        }catch (Exception ex){
            assertTrue(ex instanceof RequestException);
        }
    }//大月错误
    @Test
    public void trade11() throws Exception{
        CSSystemImpl css = new CSSystemImpl();
        try {
            css.Trade("404", "1621", "2333", 99, "2018-06-31 01:00:30");
        }catch (Exception ex){
            assertTrue(ex instanceof RequestException);
        }
    }//小月错误
    @Test
    public void trade12() throws Exception{
        CSSystemImpl css = new CSSystemImpl();
        try {
            css.Trade("404", "1621", "2333", 99, "2018-02-29 01:00:30");
        }catch (Exception ex){
            assertTrue(ex instanceof RequestException);
        }
    }//非闰年2月
    @Test
    public void trade13() throws Exception{
        CSSystemImpl css = new CSSystemImpl();
        try {
            css.Trade("404", "1621", "2333", 99, "2018-02-30 01:00:30");
        }catch (Exception ex){
            assertTrue(ex instanceof RequestException);
        }
    }//闰年2月
    @Test
    public void trade14() throws Exception{
        CSSystemImpl css = new CSSystemImpl();
        try {
            css.Trade("404", "1621", "2333", 99, "");
        }catch (Exception ex){
            assertTrue(ex instanceof RequestException);
        }
    }//时间为空
    @Test
    public void trade15() throws Exception{
        CSSystemImpl css = new CSSystemImpl();
        try {
            css.Trade("404", "1621", "2333", 99, "2018-06-06 24:00:30");
        }catch (Exception ex){
            assertTrue(ex instanceof RequestException);
        }
    }//时
    @Test
    public void trade16() throws Exception{
        CSSystemImpl css = new CSSystemImpl();
        try {
            css.Trade("404", "1621", "2333", 99, "2018-06-06 01:60:00");
        }catch (Exception ex){
            assertTrue(ex instanceof RequestException);
        }
    }//分
    @Test
    public void trade17() throws Exception{
        CSSystemImpl css = new CSSystemImpl();
        try {
            css.Trade("404", "1621", "2333", 99, "2018-06-06 01:00:60");
        }catch (Exception ex){
            assertTrue(ex instanceof RequestException);
        }
    }//秒
    @Test
    public void trade18() throws Exception{
        CSSystemImpl css = new CSSystemImpl();
        try {
            css.Trade("404", "1621", "2333", 99, "asdfghjkl");
        }catch (Exception ex){
            assertTrue(ex instanceof RequestException);
        }
    }//时间格式错误

    //有效等价类
    @Test
    public void tradeA() throws Exception{
        CSSystemImpl css = new CSSystemImpl();
        assertEquals("1001",
                css.Trade("1001","1621","2333",66.6,"2018-06-06 01:00:30"));
    }//
    @Test
    public void tradeB() throws Exception{
        CSSystemImpl css = new CSSystemImpl();
        assertEquals("1002",
                css.Trade("1002","1621","2333",99.8,"2018-06-06 01:00:30"));
    }//
    @Test
    public void tradeC() throws Exception{
        CSSystemImpl css = new CSSystemImpl();
        assertEquals("1003",
                css.Trade("1003","1621","2333",66.6,"2018-06-06 01:00:30"));
    }//
    @Test
    public void tradeD() throws Exception{
        CSSystemImpl css = new CSSystemImpl();
        assertEquals("1004",
                css.Trade("1004","1621","2333",66.6,"2018-06-06 01:00:30"));
    }//
/* */
}
