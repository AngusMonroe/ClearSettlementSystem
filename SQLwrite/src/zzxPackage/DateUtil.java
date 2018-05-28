package zzxPackage;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	
	private static final String[] dateFormat = {
			"yyyy-MM-dd HH:mm:ss", 
			"yyyy-MM-dd",
	};
	
	private static boolean isLdateStr(String date) {
		SimpleDateFormat format = new SimpleDateFormat(dateFormat[0]);
		try {
			format.setLenient(false);
			format.parse(date);
			return true;
		} catch (ParseException e) {
			return false;
		}
	}
	
	private static boolean isSdateStr(String date) {
		SimpleDateFormat format = new SimpleDateFormat(dateFormat[1]);
		try {
			format.setLenient(false);
			format.parse(date);
			return true;
		} catch (ParseException e) {
			return false;
		}
	}
	
	public static Date strToDate(String strDate) {
		int kind;
		if (isLdateStr(strDate)) {
			kind = 0;
		} else if (isSdateStr(strDate)) {
			kind = 1;
		} else {
			throw new IllegalArgumentException();
		}
		SimpleDateFormat formatter = new SimpleDateFormat(dateFormat[kind]);  
	    ParsePosition pos = new ParsePosition(0);  
	    Date strtodate = formatter.parse(strDate, pos);  
	    return strtodate;  
	}  
	
	/** @return eg: 2018-9-13 */
	public static String getCurrDate() {
		DateFormat dateTimeformat = new SimpleDateFormat(dateFormat[0]);
		return dateTimeformat.format(new Date());
	}
	
	public static String getCurrDateTime() {
		DateFormat dateTimeformat = new SimpleDateFormat(dateFormat[1]);
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
	
	public static Date to15DayBefore(Date date) {
//		final long ONE_MINUTE = 60000L;
//	    final long ONE_HOUR = 3600000L;
	    final long ONE_DAY = 86400000L;
//	    final long ONE_WEEK = 604800000L;
		Date ans = new Date(date.getTime() - 15 * ONE_DAY);
		return ans;
	}
	
	public static void main(String[] args) {
		//Date date = strToDate("2018-9-2 23:12:12");
		Date date = strToDate("2018-9-2");
		Date before = to15DayBefore(date);
		System.out.println(dateToString(before, 1));
	}
}
