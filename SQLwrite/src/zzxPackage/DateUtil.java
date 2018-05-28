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
	
	public static String dateToString(Date date, int kind){
		DateFormat dateTimeformat = new SimpleDateFormat(dateFormat[kind]);
		return dateTimeformat.format(date);
	}

	
//	public static void main(String[] args) {
//		//Date date = strToDate("2018-9-2 23:12:12");
//		Date date = strToDate("2018-9-2");
//		System.out.println(dateToString(date, 0));
//	}
}
