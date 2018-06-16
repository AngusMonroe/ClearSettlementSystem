package com.altale.service.impl;

import com.altale.Launcher;
import com.altale.service.CSException.TimeOutOfRangeException;
import com.altale.service.connection.CSSdbinfo;
import com.altale.service.connection.SQLConnection;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DownloadFileTest {
    //注意：此函数的输出会随着系统时间按天变化，而期望输出的字符串是常量
    /*downloadFile*/
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
        CreateTestFileUtil.create();
    }
    @Test
    public void downloadFile1() throws Exception{
        CSSystemImpl css = new CSSystemImpl();
        try {
            css.DownloadFile("");
        }catch (Exception ex){
            assertTrue(ex instanceof TimeOutOfRangeException);
        }
    }//空
    @Test
    public void downloadFile2() throws Exception{
        CSSystemImpl css = new CSSystemImpl();
        try {
            css.DownloadFile("2018-00-01 00:00:00");
        }catch (Exception ex){
            assertTrue(ex instanceof TimeOutOfRangeException);
        }
    }//月1
    @Test
    public void downloadFile3() throws Exception{
        CSSystemImpl css = new CSSystemImpl();
        try {
            css.DownloadFile("2018-13-01 00:00:00");
        }catch (Exception ex){
            assertTrue(ex instanceof TimeOutOfRangeException);
        }
    }//月2
    @Test
    public void downloadFile4() throws Exception{
        CSSystemImpl css = new CSSystemImpl();
        try {
            css.DownloadFile("2018-06-00 00:00:00");
        }catch (Exception ex){
            assertTrue(ex instanceof TimeOutOfRangeException);
        }
    }//日
    @Test
    public void downloadFile5() throws Exception{
        CSSystemImpl css = new CSSystemImpl();
        try {
            css.DownloadFile("2018-05-32 00:00:00");
        }catch (Exception ex){
            assertTrue(ex instanceof TimeOutOfRangeException);
        }
    }//大月
    @Test
    public void downloadFile6() throws Exception{
        CSSystemImpl css = new CSSystemImpl();
        try {
            css.DownloadFile("2018-06-31 00:00:00");
        }catch (Exception ex){
            assertTrue(ex instanceof TimeOutOfRangeException);
        }
    }//小月
    @Test
    public void downloadFile7() throws Exception{
        CSSystemImpl css = new CSSystemImpl();
        try {
            css.DownloadFile("2018-02-29 00:00:00");
        }catch (Exception ex){
            assertTrue(ex instanceof TimeOutOfRangeException);
        }
    }//非闰年2月
    @Test
    public void downloadFile8() throws Exception{
        CSSystemImpl css = new CSSystemImpl();
        try {
            css.DownloadFile("2008-02-30 00:00:00");
        }catch (Exception ex){
            assertTrue(ex instanceof TimeOutOfRangeException);
        }
    }//闰年2月
    @Test
    public void downloadFile9() throws Exception{
        CSSystemImpl css = new CSSystemImpl();
        try {
            css.DownloadFile("asdfghjkl");
        }catch (Exception ex){
            assertTrue(ex instanceof TimeOutOfRangeException);
        }
    }//格式非法
    @Test
    public void downloadFile10() throws Exception{
        CSSystemImpl css = new CSSystemImpl();
        try {
            css.DownloadFile("2018-05-01 00:00:00");
        }catch (Exception ex){
            assertTrue(ex instanceof TimeOutOfRangeException);
        }
    }//不在范围内


    //注意：这个函数的输出会随着系统时间而变化
    @Test
    public void downloadFile11() throws Exception{
        CSSystemImpl css = new CSSystemImpl();
        assertEquals("[{\"Address\":\"http://buaa-jj.cn/account/2018-06-06.json\",\"Name\":\"2018-06-06.json\"}, {\"Address\":\"http://buaa-jj.cn/account/2018-06-07.json\",\"Name\":\"2018-06-07.json\"}, {\"Address\":\"http://buaa-jj.cn/account/2018-06-08.json\",\"Name\":\"2018-06-08.json\"}, {\"Address\":\"http://buaa-jj.cn/account/2018-06-09.json\",\"Name\":\"2018-06-09.json\"}, {\"Address\":\"http://buaa-jj.cn/account/2018-06-10.json\",\"Name\":\"2018-06-10.json\"}, {\"Address\":\"http://buaa-jj.cn/account/2018-06-11.json\",\"Name\":\"2018-06-11.json\"}, {\"Address\":\"http://buaa-jj.cn/account/2018-06-12.json\",\"Name\":\"2018-06-12.json\"}, {\"Address\":\"http://buaa-jj.cn/account/2018-06-13.json\",\"Name\":\"2018-06-13.json\"}, {\"Address\":\"http://buaa-jj.cn/account/2018-06-14.json\",\"Name\":\"2018-06-14.json\"}, {\"Address\":\"http://buaa-jj.cn/account/2018-06-15.json\",\"Name\":\"2018-06-15.json\"}]",
                css.DownloadFile("2018-06-06 00:00:00"));

    }

}
