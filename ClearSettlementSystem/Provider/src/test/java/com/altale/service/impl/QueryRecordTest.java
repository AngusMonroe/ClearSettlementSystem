package com.altale.service.impl;

import com.altale.Launcher;
import com.altale.service.CSException.OperatorIdOutOfRangeException;
import com.altale.service.CSException.TimeOutOfRangeException;
import com.altale.service.connection.CSSdbinfo;
import com.altale.service.connection.SQLConnection;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class QueryRecordTest {
    /*queryRecord*/
    //注意：此函数的输出会随着数据库中的内容变化，而期望输出的字符串是常量
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
    @Test
    public void queryRecord1() throws Exception{
        CSSystemImpl css = new CSSystemImpl();
        assertEquals("[]",
                css.QueryRecord("","2018-06-08 23:59:59",2));
    }//开始为空
    @Test
    public void queryRecord2() throws Exception{
        CSSystemImpl css = new CSSystemImpl();
        try {
            css.QueryRecord("aa2018-06-01 00:00:00", "2018-06-08 23:59:59", 2);
        }catch (Exception ex){
            assertTrue(ex instanceof TimeOutOfRangeException);
        }
    }//开始时间非法
    @Test
    public void queryRecord3() throws Exception{
        CSSystemImpl css = new CSSystemImpl();
        assertEquals("[]",
                css.QueryRecord("2018-06-01 00:00:00","",2));
    }//结束时间为空
    @Test
    public void queryRecord4() throws Exception{
        CSSystemImpl css = new CSSystemImpl();
        try {
            css.QueryRecord("2018-06-02 00:00:00", "bb2018-03-08 23:59:59", 2);
        }catch (Exception ex){
            assertTrue(ex instanceof TimeOutOfRangeException);
        }
    }//结束时间非法
    @Test
    public void queryRecord3_null() throws Exception{
        CSSystemImpl css = new CSSystemImpl();
        assertEquals("[]",
                css.QueryRecord("","",2));
    }//时间为空
    @Test
    public void queryRecord5() throws Exception{
        CSSystemImpl css = new CSSystemImpl();
        try {
            css.QueryRecord("2018-06-01 00:00:00", "2018-03-08 23:59:59", 2);
        }catch (Exception ex){
            assertTrue(ex instanceof TimeOutOfRangeException);
        }
    }//结束时间在开始之前
    @Test
    public void queryRecord6() throws Exception{
        CSSystemImpl css = new CSSystemImpl();
        try {
            css.QueryRecord("2018-00-01 00:00:00", "2018-03-08 23:59:59", 2);
        }catch (Exception ex){
            assertTrue(ex instanceof TimeOutOfRangeException);
        }
    }//月1
    @Test
    public void queryRecord7() throws Exception{
        CSSystemImpl css = new CSSystemImpl();
        try {
            css.QueryRecord("2018-13-01 00:00:00", "2019-03-08 23:59:59", 2);
        }catch (Exception ex){
            assertTrue(ex instanceof TimeOutOfRangeException);
        }
    }//月2
    @Test
    public void queryRecord8() throws Exception{
        CSSystemImpl css = new CSSystemImpl();
        try {
            css.QueryRecord("2018-05-32 00:00:00", "2018-06-08 23:59:59", 2);
        }catch (Exception ex){
            assertTrue(ex instanceof TimeOutOfRangeException);
        }
    }//大月
    @Test
    public void queryRecord9() throws Exception{
        CSSystemImpl css = new CSSystemImpl();
        try {
            css.QueryRecord("2018-06-31 00:00:00", "2018-07-08 23:59:59", 2);
        }catch (Exception ex){
            assertTrue(ex instanceof TimeOutOfRangeException);
        }
    }//小月
    @Test
    public void queryRecord10() throws Exception{
        CSSystemImpl css = new CSSystemImpl();
        try {
            css.QueryRecord("2018-02-29 00:00:00", "2018-03-08 23:59:59", 2);
        }catch (Exception ex){
            assertTrue(ex instanceof TimeOutOfRangeException);
        }
    }//非闰年2月
    @Test
    public void queryRecord11() throws Exception{
        CSSystemImpl css = new CSSystemImpl();
        try {
            css.QueryRecord("2008-02-30 00:00:00", "2018-03-08 23:59:59", 2);
        }catch (Exception ex){
            assertTrue(ex instanceof TimeOutOfRangeException);
        }
    }//闰年2月
    @Test
    public void queryRecord12() throws Exception{
        CSSystemImpl css = new CSSystemImpl();
        try {
            css.QueryRecord("2018-06-01 24:00:00", "2018-06-08 00:59:59", 2);
        }catch (Exception ex){
            assertTrue(ex instanceof TimeOutOfRangeException);
        }
    }//时
    @Test
    public void queryRecord13() throws Exception{
        CSSystemImpl css = new CSSystemImpl();
        try {
            css.QueryRecord("2018-06-01 00:0:00", "2018-06-08 23:60:59", 2);
        }catch (Exception ex){
            assertTrue(ex instanceof TimeOutOfRangeException);
        }
    }//分
    @Test
    public void queryRecord14() throws Exception{
        CSSystemImpl css = new CSSystemImpl();
        try {
            css.QueryRecord("2018-06-01 00:00:00", "2018-06-08 23:59:60", 2);
        }catch (Exception ex){
            assertTrue(ex instanceof TimeOutOfRangeException);
        }
    }//秒
    @Test
    public void queryRecord15() throws Exception{
        CSSystemImpl css = new CSSystemImpl();
        try {
            css.QueryRecord("2008-06-01 00:00:00", "2008-06-08 23:59:59", 2);
        }catch (Exception ex){
            assertTrue(ex instanceof TimeOutOfRangeException);
        }
    }//不在15天时间范围
    @Test
    public void queryRecord16() throws Exception{
        CSSystemImpl css = new CSSystemImpl();
        try {
            css.QueryRecord("2018-06-01 00:00:00", "2018-06-08 23:59:59", 3);
        }catch (Exception ex){
            assertTrue(ex instanceof OperatorIdOutOfRangeException);
        }
    }//参数错误
    //有效
    @Test
    public void queryRecordA() throws Exception{
        CSSystemImpl css = new CSSystemImpl();
        assertEquals("[]",
                css.QueryRecord("2018-06-02 00:00:00","2018-06-08 23:59:59",2));
    }

}
