package com.zzqx.support.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

public class DateManager {
	
	// 各种时间格式
	public static final SimpleDateFormat date_sdf = new SimpleDateFormat(
				"yyyy-MM-dd");
	public static final  SimpleDateFormat datetimeFormat = new SimpleDateFormat(
	"yyyy-MM-dd HH:mm:ss");
    /**
     * 以毫秒表示的时间
     */
 	private static final long DAY_IN_MILLIS = 24 * 3600 * 1000;
 	private static final long HOUR_IN_MILLIS = 3600 * 1000;
 	private static final long MINUTE_IN_MILLIS = 60 * 1000;
 	private static final long SECOND_IN_MILLIS = 1000;
	
	private DateManager() {}
	
	public static String getTime(String style) {
		if(StringUtils.isEmpty(style)) {
			style = "yyyy-MM-dd HH:mm:ss";
		}
		SimpleDateFormat df = new SimpleDateFormat(style);
		return df.format(new Date());
	}
	
	/**
	 * 字符串转换成日期
	 * @param str
	 * @param sdf
	 * @return
	 */
	public static synchronized Date str2Date(String str, SimpleDateFormat sdf){
		if (null == str || "".equals(str)) {
			return null;
		}
		Date date = null;
		try {
			date = sdf.parse(str);
			return date;
		} catch (ParseException e) {
			e.printStackTrace();
			System.out.println("异常数据："+str);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("异常数据："+str);
		}
		return null;
	}
	
	/**
	 * 日期转换为字符串
	 * 
	 * @param date
	 *            日期
	 * @param format
	 *            日期格式
	 * @return 字符串
	 */
	public static String date2Str(SimpleDateFormat date_sdf) {
		Date date=getDate();
		if (null == date) {
			return null;
		}
		return date_sdf.format(date);
	}
	
	/**
	 * 计算两个时间之间的差值，根据标志的不同而不同
	 * 
	 * @param flag
	 *            计算标志，表示按照年/月/日/时/分/秒等计算
	 * @param calSrc
	 *            减数
	 * @param calDes
	 *            被减数
	 * @return 两个日期之间的差值
	 */
	public static int dateDiff(char flag, Calendar calSrc, Calendar calDes) {

		long millisDiff = getMillis(calSrc) - getMillis(calDes);

		if (flag == 'y') {
			return (calSrc.get(calSrc.YEAR) - calDes.get(calDes.YEAR));
		}

		if (flag == 'd') {
			return (int) (millisDiff / DAY_IN_MILLIS);
		}

		if (flag == 'h') {
			return (int) (millisDiff / HOUR_IN_MILLIS);
		}

		if (flag == 'm') {
			return (int) (millisDiff / MINUTE_IN_MILLIS);
		}

		if (flag == 's') {
			return (int) (millisDiff / SECOND_IN_MILLIS);
		}

		return 0;
	}
	
	/**
	 * 系统时间的毫秒数
	 * 
	 * @return 系统时间的毫秒数
	 */
	public static long getMillis() {
		return new Date().getTime();
	}
	
	/**
	 * 指定日历的毫秒数
	 * 
	 * @param cal
	 *            指定日历
	 * @return 指定日历的毫秒数
	 */
	public static long getMillis(Calendar cal) {
		return cal.getTime().getTime();
	}
	
	public static Calendar DateToCalendar(Date date){
		Calendar cal=Calendar.getInstance();
		cal.setTime(date);
		return cal;
	}
	
	/**
	 * 日期转换为字符串
	 * 
	 * @param date
	 *            日期
	 * @param format
	 *            日期格式
	 * @return 字符串
	 */
	public static String date2Str(Date date, SimpleDateFormat date_sdf) {
		if (null == date) {
			return null;
		}
		return date_sdf.format(date);
	}
	
	/**
	 * 当前日期
	 * 
	 * @return 系统当前时间
	 */
	public static Date getDate() {
		return new Date();
	}
}