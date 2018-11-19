package com.zzqx.mvc.entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Text {
	public static void main(String[] args) throws ParseException {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat smf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = smf.parse("2017-06-22 17:34:11");
		Calendar calendarOn = Calendar.getInstance();
        calendarOn.setTime(date); 
	    Calendar calendarOff = Calendar.getInstance();
	    calendarOff.setTime(new Date());
		int time = dateDiff('s', calendarOn, calendarOff);
		System.out.println(time);
	}
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
	private static final long DAY_IN_MILLIS = 24 * 3600 * 1000;
 	private static final long HOUR_IN_MILLIS = 3600 * 1000;
 	private static final long MINUTE_IN_MILLIS = 60 * 1000;
 	private static final long SECOND_IN_MILLIS = 1000;
	public static long getMillis(Calendar cal) {
		return cal.getTime().getTime();
	}
}
