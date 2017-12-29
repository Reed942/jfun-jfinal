
package org.fun.jfinal.common;

import java.security.InvalidParameterException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

	static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	static SimpleDateFormat dateSdf = new SimpleDateFormat("yyyyMMdd");

	public static String now() {
		return sdf.format(new Date());
	}

	public static String dateString() {
		return dateSdf.format(new Date());
	}

	public static String format(Date date) {
		if (null == date)
			return null;

		return sdf.format(date);
	}

	/**
	 * 统计两个日期之间包含的天数。
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static int getDayDiff(Date date1, Date date2) {
		if (date1 == null || date2 == null) {
			throw new InvalidParameterException("date1 and date2 cannot be null!");
		}
		long millSecondsInOneDay = 24 * 60 * 60 * 1000;
		return (int) ((date1.getTime() - date2.getTime()) / millSecondsInOneDay);
	}

	
	
	public static void main(String[] args) throws ParseException {
		Date date = sdf.parse("2016-7-20 00:00:00");
		System.out.println(getDayDiff(new Date(), date));
	}

}
