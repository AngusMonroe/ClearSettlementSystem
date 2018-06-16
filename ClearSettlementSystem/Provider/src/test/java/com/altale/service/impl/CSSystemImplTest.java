package com.altale.service.impl;

import com.altale.Launcher;
import com.altale.service.connection.CSSdbinfo;
import com.altale.service.connection.SQLConnection;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CSSystemImplTest {

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
                                    "test" + "?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=GMT%2B8",
                            "root",
                            ""
                    );

        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    /*recharge*/
/*   @Test
    public void recharge1() throws Exception{
        CSSystemImpl css = new CSSystemImpl();
        assertEquals("-1",
                css.Recharge("","1621",233,true,"2018-06-06 12:00:30"));
    }//requestID为空
    @Test
    public void recharge2() throws Exception{
        CSSystemImpl css = new CSSystemImpl();
        assertEquals("-1",
                css.Recharge("abc","1621",233,true,"2018-06-06 12:00:30"));
    }//requestID错误
    @Test
    public void recharge3() throws Exception{
        CSSystemImpl css = new CSSystemImpl();
        assertEquals("-1",
                css.Recharge("23456","",233,true,"2018-06-06 12:00:30"));
    }//userID为空
    @Test
    public void recharge4() throws Exception{
        CSSystemImpl css = new CSSystemImpl();
        assertEquals("-1",
                css.Recharge("23456","abc",233,true,"2018-06-06 12:00:30"));
    }//userID错误
    @Test
    public void recharge5() throws Exception{
        CSSystemImpl css = new CSSystemImpl();
        assertEquals("-1",
                css.Recharge("23456","1621",-100,true,"2018-06-06 12:00:30"));
    }//amount为负
    @Test
    public void recharge6() throws Exception{
        CSSystemImpl css = new CSSystemImpl();
        assertEquals("-1",
                css.Recharge("104","1621",21,true,"2018-00-01 12:00:30"));
    }//月份错误1
    @Test
    public void recharge7() throws Exception{
        CSSystemImpl css = new CSSystemImpl();
        assertEquals("-1",
                css.Recharge("1001","1621",21,true,"2018-13-01 12:00:30"));
    }//月份错误2
    @Test
    public void recharge7_day() throws Exception{
        CSSystemImpl css = new CSSystemImpl();
        assertEquals("505",
                css.Recharge("505","1621",21,true,"2018-11-00 12:00:30"));
    }//日期为0
    @Test
    public void recharge8() throws Exception{
        CSSystemImpl css = new CSSystemImpl();
        assertEquals("-1",
                css.Recharge("1001","1621",21,true,"2018-05-32 12:00:30"));
    }//大月错误
    @Test
    public void recharge9() throws Exception{
        CSSystemImpl css = new CSSystemImpl();
        assertEquals("-1",
                css.Recharge("1001","1621",21,true,"2018-06-31 12:00:30"));
    }//小月错误
    @Test
    public void recharge10() throws Exception{
        CSSystemImpl css = new CSSystemImpl();
        assertEquals("-1",
                css.Recharge("1001","1621",21,true,"2018-02-29 12:00:30"));
    }//非闰年2月
    @Test
    public void recharge11() throws Exception{
        CSSystemImpl css = new CSSystemImpl();
        assertEquals("-1",
                css.Recharge("1001","1621",21,true,"2008-02-30 12:00:30"));
    }//闰年2月
    @Test
    public void recharge12() throws Exception{
        CSSystemImpl css = new CSSystemImpl();
        assertEquals("-1",
                css.Recharge("1001","1621",21,true,"-2018-06-06-"));
    }//时间格式有误
    @Test
    public void recharge13() throws Exception{
        CSSystemImpl css = new CSSystemImpl();
        assertEquals("-1",
                css.Recharge("1001","1621",21,true,"2018-06-06 24:00:00"));
    }//小时错误
    @Test
    public void recharge14() throws Exception{
        CSSystemImpl css = new CSSystemImpl();
        assertEquals("-1",
                css.Recharge("1001","1621",21,true,"2018-06-06 12:60:00"));
    }//分错误
    @Test
    public void recharge15() throws Exception{
        CSSystemImpl css = new CSSystemImpl();
        assertEquals("-1",
                css.Recharge("1001","1621",21,true,"2018-06-06 12:00:60"));
    }//秒错误
    @Test
    public void recharge16() throws Exception{
        CSSystemImpl css = new CSSystemImpl();
        assertEquals("-1",
                css.Recharge("1001","1621",21,true,""));
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
*/

/*withdraw*/
/*
    @Test
    public void withdraw1() throws Exception{
        CSSystemImpl css = new CSSystemImpl();
        assertEquals("-1",
                css.Withdraw("","1621",233,true,"2018-06-06 12:00:30"));
    }//requestID为空
    @Test
    public void withdraw2() throws Exception{
        CSSystemImpl css = new CSSystemImpl();
        assertEquals("-1",
                css.Withdraw("abc","1621",233,true,"2018-06-06 12:00:30"));
    }//requestID错误
    @Test
    public void withdraw3() throws Exception{
        CSSystemImpl css = new CSSystemImpl();
        assertEquals("-1",
                css.Withdraw("23456","",233,true,"2018-06-06 12:00:30"));
    }//userID为空
    @Test
    public void withdraw4() throws Exception{
        CSSystemImpl css = new CSSystemImpl();
        assertEquals("-1",
                css.Withdraw("23456","abc",233,true,"2018-06-06 12:00:30"));
    }//userID错误
    @Test
    public void withdraw5() throws Exception{
        CSSystemImpl css = new CSSystemImpl();
        assertEquals("-1",
                css.Withdraw("23456","1621",-100,true,"2018-06-06 12:00:30"));
    }//amount为负
    @Test
    public void withdraw6() throws Exception{
        CSSystemImpl css = new CSSystemImpl();
        assertEquals("-1",
                css.Withdraw("104","1621",21,true,"2018-00-01 12:00:30"));
    }//月份错误1
    @Test
    public void withdraw7() throws Exception{
        CSSystemImpl css = new CSSystemImpl();
        assertEquals("-1",
                css.Withdraw("1001","1621",21,true,"2018-13-01 12:00:30"));
    }//月份错误2
    @Test
    public void withdraw7_day) throws Exception{
        CSSystemImpl css = new CSSystemImpl();
        assertEquals("505",
                css.Withdraw("505","1621",21,true,"2018-10-00 12:00:30"));
    }//日期为0
    @Test
    public void withdraw8() throws Exception{
        CSSystemImpl css = new CSSystemImpl();
        assertEquals("-1",
                css.Withdraw("1001","1621",21,true,"2018-05-32 12:00:30"));
    }//大月错误
    @Test
    public void withdraw9() throws Exception{
        CSSystemImpl css = new CSSystemImpl();
        assertEquals("-1",
                css.Withdraw("1001","1621",21,true,"2018-06-31 12:00:30"));
    }//小月错误
    @Test
    public void withdraw10() throws Exception{
        CSSystemImpl css = new CSSystemImpl();
        assertEquals("-1",
                css.Withdraw("1001","1621",21,true,"2018-02-29 12:00:30"));
    }//非闰年2月
    @Test
    public void withdraw11() throws Exception{
        CSSystemImpl css = new CSSystemImpl();
        assertEquals("-1",
                css.Withdraw("1001","1621",21,true,"2008-02-30 12:00:30"));
    }//闰年2月
    @Test
    public void withdraw12() throws Exception{
        CSSystemImpl css = new CSSystemImpl();
        assertEquals("-1",
                css.Withdraw("1001","1621",21,true,"-2018-06-06-"));
    }//时间格式有误
    @Test
    public void withdraw13() throws Exception{
        CSSystemImpl css = new CSSystemImpl();
        assertEquals("-1",
                css.Withdraw("1001","1621",21,true,"2018-06-06 24:00:00"));
    }//小时错误
    @Test
    public void withdraw14() throws Exception{
        CSSystemImpl css = new CSSystemImpl();
        assertEquals("-1",
                css.Withdraw("1001","1621",21,true,"2018-06-06 12:60:00"));
    }//分错误
    @Test
    public void withdraw15() throws Exception{
        CSSystemImpl css = new CSSystemImpl();
        assertEquals("-1",
                css.Withdraw("1001","1621",21,true,"2018-06-06 12:00:60"));
    }//秒错误
     @Test
    public void withdraw16() throws Exception{
        CSSystemImpl css = new CSSystemImpl();
        assertEquals("-1",
                css.Withdraw("1001","1621",21,true,""));
    }//时间空


    //正确参数
    @Test
    public void withdrawA() throws Exception{
        CSSystemImpl css = new CSSystemImpl();
        assertEquals("1001",
                css.Withdraw("1001","1621",21,true,"2018-05-30 00:00:00"));
    }
    @Test
    public void withdrawB() throws Exception{
        CSSystemImpl css = new CSSystemImpl();
        assertEquals("1002",
                css.Withdraw("1002","1621",21,true,"2018-06-30 00:00:00"));
    }
    @Test
    public void withdrawC() throws Exception{
        CSSystemImpl css = new CSSystemImpl();
        assertEquals("1003",
                css.Withdraw("1003","1621",21,true,"2018-02-28 00:00:00"));
    }
    @Test
    public void withdrawD() throws Exception{
        CSSystemImpl css = new CSSystemImpl();
        assertEquals("1004",
                css.Withdraw("1004","1621",21,true,"2008-02-29 00:00:00"));
    }

*/
    /*trade*/
   @Test
    public void trade1() throws Exception{
        CSSystemImpl css = new CSSystemImpl();
        assertEquals("-1",
                css.Trade("","1621","2333",99,"2018-06-06 01:00:30"));
    }//requestID为空
    @Test
    public void trade2() throws Exception{
        CSSystemImpl css = new CSSystemImpl();
        assertEquals("-1",
                css.Trade("abc","1621","2333",99,"2018-06-06 01:00:30"));
    }//requestID错误
    @Test
    public void trade3() throws Exception{
        CSSystemImpl css = new CSSystemImpl();
        assertEquals("-1",
                css.Trade("404","","2333",99,"2018-06-06 01:00:30"));
    }//userID为空
    @Test
    public void trade4() throws Exception{
        CSSystemImpl css = new CSSystemImpl();
        assertEquals("-1",
                css.Trade("404","abc","2333",99,"2018-06-06 01:00:30"));
    }//userID错误
    @Test
    public void trade5() throws Exception{
        CSSystemImpl css = new CSSystemImpl();
        assertEquals("-1",
                css.Trade("404","1621","",99,"2018-06-06 01:00:30"));
    }//merchantID为空
    @Test
    public void trade6() throws Exception{
        CSSystemImpl css = new CSSystemImpl();
        assertEquals("-1",
                css.Trade("404","1621","abc",99,"2018-06-06 01:00:30"));
    }//merchantID错误
    @Test
    public void trade7() throws Exception{
        CSSystemImpl css = new CSSystemImpl();
        assertEquals("-1",
                css.Trade("404","1621","2333",-100,"2018-06-06 01:00:30"));
    }//amount为负
    @Test
    public void trade8() throws Exception{
        CSSystemImpl css = new CSSystemImpl();
        assertEquals("-1",
                css.Trade("404","1621","2333",99,"2018-00-06 01:00:30"));
    }//月错误1
    @Test
    public void trade9() throws Exception{
        CSSystemImpl css = new CSSystemImpl();
        assertEquals("-1",
                css.Trade("404","1621","2333",99,"2018-14-06 01:00:30"));
    }//月错误2
    @Test
    public void trade10() throws Exception{
        CSSystemImpl css = new CSSystemImpl();
        assertEquals("-1",
                css.Trade("404","1621","2333",99,"2018-05-32 01:00:30"));
    }//大月错误
    @Test
    public void trade11() throws Exception{
        CSSystemImpl css = new CSSystemImpl();
        assertEquals("-1",
                css.Trade("404","1621","2333",99,"2018-06-31 01:00:30"));
    }//小月错误
    @Test
    public void trade12() throws Exception{
        CSSystemImpl css = new CSSystemImpl();
        assertEquals("-1",
                css.Trade("404","1621","2333",99,"2018-02-29 01:00:30"));
    }//非闰年2月
    @Test
    public void trade13() throws Exception{
        CSSystemImpl css = new CSSystemImpl();
        assertEquals("-1",
                css.Trade("404","1621","2333",99,"2018-02-30 01:00:30"));
    }//闰年2月
    @Test
    public void trade14() throws Exception{
        CSSystemImpl css = new CSSystemImpl();
        assertEquals("-1",
                css.Trade("404","1621","2333",99,""));
    }//时间为空
    @Test
    public void trade15() throws Exception{
        CSSystemImpl css = new CSSystemImpl();
        assertEquals("-1",
                css.Trade("404","1621","2333",99,"2018-06-06 24:00:30"));
    }//时
    @Test
    public void trade16() throws Exception{
        CSSystemImpl css = new CSSystemImpl();
        assertEquals("-1",
                css.Trade("404","1621","2333",99,"2018-06-06 01:60:00"));
    }//分
    @Test
    public void trade17() throws Exception{
        CSSystemImpl css = new CSSystemImpl();
        assertEquals("-1",
                css.Trade("404","1621","2333",99,"2018-06-06 01:00:60"));
    }//秒
    @Test
    public void trade18() throws Exception{
        CSSystemImpl css = new CSSystemImpl();
        assertEquals("-1",
                css.Trade("404","1621","2333",99,"asdfghjkl"));
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

/*queryRecord*/


/*    @Test
    public void queryRecord1() throws Exception{
        CSSystemImpl css = new CSSystemImpl();
        assertEquals("[]",
                css.QueryRecord("","2018-06-08 23:59:59",2));
    }//开始为空
    @Test
    public void queryRecord2() throws Exception{
        CSSystemImpl css = new CSSystemImpl();
        assertEquals("[]",
                css.QueryRecord("aa2018-06-01 00:00:00","2018-06-08 23:59:59",2));
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
        assertEquals("-1",
                css.QueryRecord("2018-06-02 00:00:00","bb2018-03-08 23:59:59",2));
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
        assertEquals("-1",
                css.QueryRecord("2018-06-01 00:00:00","2018-03-08 23:59:59",2));
    }//结束时间在开始之前
    @Test
    public void queryRecord6() throws Exception{
        CSSystemImpl css = new CSSystemImpl();
        assertEquals("-1",
                css.QueryRecord("2018-00-01 00:00:00","2018-03-08 23:59:59",2));
    }//月1
    @Test
    public void queryRecord7() throws Exception{
        CSSystemImpl css = new CSSystemImpl();
        assertEquals("-1",
                css.QueryRecord("2018-13-01 00:00:00","2019-03-08 23:59:59",2));
    }//月2
    @Test
    public void queryRecord8() throws Exception{
        CSSystemImpl css = new CSSystemImpl();
        assertEquals("-1",
                css.QueryRecord("2018-05-32 00:00:00","2018-06-08 23:59:59",2));
    }//大月
    @Test
    public void queryRecord9() throws Exception{
        CSSystemImpl css = new CSSystemImpl();
        assertEquals("-1",
                css.QueryRecord("2018-06-31 00:00:00","2018-07-08 23:59:59",2));
    }//小月
    @Test
    public void queryRecord10() throws Exception{
        CSSystemImpl css = new CSSystemImpl();
        assertEquals("-1",
                css.QueryRecord("2018-02-29 00:00:00","2018-03-08 23:59:59",2));
    }//非闰年2月
    @Test
    public void queryRecord11() throws Exception{
        CSSystemImpl css = new CSSystemImpl();
        assertEquals("-1",
                css.QueryRecord("2008-02-30 00:00:00","2018-03-08 23:59:59",2));
    }//闰年2月
    @Test
    public void queryRecord12() throws Exception{
        CSSystemImpl css = new CSSystemImpl();
        assertEquals("-1",
                css.QueryRecord("2018-06-01 24:00:00","2018-06-08 00:59:59",2));
    }//时
    @Test
    public void queryRecord13() throws Exception{
        CSSystemImpl css = new CSSystemImpl();
        assertEquals("-1",
                css.QueryRecord("2018-06-01 00:0:00","2018-06-08 23:60:59",2));
    }//分
    @Test
    public void queryRecord14() throws Exception{
        CSSystemImpl css = new CSSystemImpl();
        assertEquals("-1",
                css.QueryRecord("2018-06-01 00:00:00","2018-06-08 23:59:60",2));
    }//秒
    @Test
    public void queryRecord15() throws Exception{
        CSSystemImpl css = new CSSystemImpl();
        assertEquals("-1",
                css.QueryRecord("2008-06-01 00:00:00","2008-06-08 23:59:59",2));
    }//不在15天时间范围
    @Test
    public void queryRecord16() throws Exception{
        CSSystemImpl css = new CSSystemImpl();
        assertEquals("[]",
                css.QueryRecord("2018-06-01 00:00:00","2018-06-08 23:59:59",3));
    }//参数错误
//有效
    @Test
    public void queryRecordA() throws Exception{
        CSSystemImpl css = new CSSystemImpl();
        assertEquals("[]",
                css.QueryRecord("2018-06-02 00:00:00","2018-06-08 23:59:59",2));
    }
*/




/*downloadFile*/

/*    @Test
     public void downloadFile1() throws Exception{
        CSSystemImpl css = new CSSystemImpl();
        assertEquals("",
                css.DownloadFile(""));
    }//空
    @Test
    public void downloadFile2() throws Exception{
        CSSystemImpl css = new CSSystemImpl();
        assertEquals("-1",
                css.DownloadFile("2018-00-01 00:00:00"));
    }//月1
    @Test
    public void downloadFile3() throws Exception{
        CSSystemImpl css = new CSSystemImpl();
        assertEquals("-1",
                css.DownloadFile("2018-13-01 00:00:00"));
    }//月2
    @Test
    public void downloadFile4() throws Exception{
        CSSystemImpl css = new CSSystemImpl();
        assertEquals("-1",
                css.DownloadFile("2018-06-00 00:00:00"));
    }//日
    @Test
    public void downloadFile5() throws Exception{
        CSSystemImpl css = new CSSystemImpl();
        assertEquals("-1",
                css.DownloadFile("2018-05-32 00:00:00"));
    }//大月
    @Test
    public void downloadFile6() throws Exception{
        CSSystemImpl css = new CSSystemImpl();
        assertEquals("-1",
                css.DownloadFile("2018-06-31 00:00:00"));
    }//小月
    @Test
    public void downloadFile7() throws Exception{
        CSSystemImpl css = new CSSystemImpl();
        assertEquals("-1",
                css.DownloadFile("2018-02-29 00:00:00"));
    }//非闰年2月
    @Test
    public void downloadFile8() throws Exception{
        CSSystemImpl css = new CSSystemImpl();
        assertEquals("-1",
                css.DownloadFile("2008-02-30 00:00:00"));
    }//闰年2月
    @Test
    public void downloadFile9() throws Exception{
        CSSystemImpl css = new CSSystemImpl();
        assertEquals("-1",
                css.DownloadFile("asdfghjkl"));
    }//格式非法
    @Test
    public void downloadFile10() throws Exception{
        CSSystemImpl css = new CSSystemImpl();
        assertEquals("-1",
                css.DownloadFile("2018-05-01 00:00:00"));
    }//不在范围内

    @Test
    public void downloadFile11() throws Exception{
        CSSystemImpl css = new CSSystemImpl();
        assertEquals("-1",
                css.DownloadFile("2018-06-06 00:00:00"));
    }
*/
}