package com.altale.util;

import com.altale.service.CSException.TimeOutOfRangeException;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DateUtil {

    private static final String[] dateFormat = {
            "yyyy-MM-dd HH:mm:ss",
            "yyyy-MM-dd",
    };

    private static boolean isLdateStr(String date) {
        return date.length() == 19;
    }

    private static boolean isSdateStr(String date) {
        return date.length() == 10;
    }

    public static Date strToDate(String strDate) {
        int kind;
        if (isLdateStr(strDate)) {
            kind = 0;
            // 检验格式1
            SimpleDateFormat format = new SimpleDateFormat(dateFormat[1]);
            format.setLenient(false);
            try {
                format.parse(strDate);
            } catch (ParseException e) {
                throw new TimeOutOfRangeException("错误时间");
            }
            // 检验格式2
            if(!checkLTime(strDate)) { // 对60分检测
                throw new TimeOutOfRangeException("错误时间");
            }
        } else if (isSdateStr(strDate)) {
            kind = 1;
            SimpleDateFormat format = new SimpleDateFormat(dateFormat[1]);
            format.setLenient(false);
            try {
                format.parse(strDate);
            } catch (ParseException e) {
                throw new TimeOutOfRangeException("错误时间");
            }
            if(!checkSTime(strDate)) { // 对60分检测
                throw new TimeOutOfRangeException("错误时间");
            }
        } else {
            throw new TimeOutOfRangeException("错误时间");
        }
        SimpleDateFormat formatter = new SimpleDateFormat(dateFormat[kind]);
        ParsePosition pos = new ParsePosition(0);
        Date strtodate = formatter.parse(strDate, pos);
        return strtodate;
    }

    /** @return eg: 2018-9-13 */
    public static String getCurrDate() {
        DateFormat dateTimeformat = new SimpleDateFormat(dateFormat[1]);
        return dateTimeformat.format(new Date());
    }

    public static String getCurrDateTime() {
        DateFormat dateTimeformat = new SimpleDateFormat(dateFormat[0]);
        return dateTimeformat.format(new Date());
    }

    /**
     *
     * @param date
     * @param kind 0:长格式  1:短格式
     * @return
     */
    public static String dateToString(Date date, int kind){
        DateFormat dateTimeformat = new SimpleDateFormat(dateFormat[kind]);
        return dateTimeformat.format(date);
    }

    public static Date toDayBefore(Date date, int dayNumber) {
//		final long ONE_MINUTE = 60000L;
//	    final long ONE_HOUR = 3600000L;
        final long ONE_DAY = 86400000L;
//	    final long ONE_WEEK = 604800000L;
        Date ans = new Date(date.getTime() - dayNumber * ONE_DAY);
        return ans;
    }

    private static boolean checkLTime(String date) {
        assert date != null;
        ArrayList<String> ans =
                getPartialString("(\\d+)-(\\d+)-(\\d+) (\\d+):(\\d+):(\\d+)", date, 1,2,3,4,5,6);
        if(ans == null)
            return false; // 失配
        int year = Integer.parseInt(ans.get(0));
        int month = Integer.parseInt(ans.get(1));
        int day = Integer.parseInt(ans.get(2));
        int hour = Integer.parseInt(ans.get(3));
        int minute = Integer.parseInt(ans.get(4));
        int second = Integer.parseInt(ans.get(5));
        return (0 < month && month <= 12) && (1 <= day && day <= 31)
                && (0 <= hour && hour < 24) && (0 <= minute && minute < 60)
                && (0 <= second && second < 60);
    }

    private static boolean checkSTime(String date) {
        assert date != null;
        ArrayList<String> ans =
                getPartialString("(\\d+)-(\\d+)-(\\d+)", date, 1,2,3);
        if(ans == null)
            return false; // 失配
        int year = Integer.parseInt(ans.get(0));
        int month = Integer.parseInt(ans.get(1));
        int day = Integer.parseInt(ans.get(2));
        return (0 < month && month <= 12) && (1 <= day && day <= 31);
    }

    /**
     *
     * @param regex
     * @param input
     * @param groupID 0代表本身
     * @return 未找到:null
     */
    private static ArrayList<String> getPartialString(String regex, String input, int... groupID) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        boolean hasfound = matcher.find();
        if(hasfound) {
            ArrayList<String> ans = new ArrayList<String>();
            for (int i : groupID) {
                String s = matcher.group(i);
                ans.add(s);
            }
            return ans;
        } else {
            return null;
        }
    }

    public static void main(String[] args) {
        Date date = strToDate("1234567890");
        System.out.println(date.toString());
        //System.out.println(DateUtil.isLdateStr("2016-13-08"));
    }
}

