package com.cwjcsu.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * 
 * @author atlas
 * @date 2012-12-28
 */
public class DateUtil {

	public static String formatDate(long datetime) {
		return formatDate(datetime, "yyyy-MM-dd HH:mm:ss");
	}

	public static String formatDate(long datetime, String pattern) {
		return formatDate(new Date(datetime), pattern);
	}

	public static String formatDate(Date datetime) {
		return formatDate(datetime, "yyyy-MM-dd HH:mm:ss");
	}

	public static String formatDate(Date datetime, String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(datetime);
	}

	public static Date getDate(String datetime, String pattern) {
		Date d = null;
		SimpleDateFormat sdf = null;

		try {
			sdf = new SimpleDateFormat(pattern);
			d = sdf.parse(datetime);
		} catch (ParseException pe) {

		}
		return d;
	}

	/**
	 * 纳秒时钟在当前时钟下的时间，即nanoTime时刻的系统时间，相同的nanoTime，在不同系统时间下是不一样的。
	 * 
	 * @param nanoTime
	 *            纳秒绝对时间
	 * @return
	 */
	public static Date getDateAtNano(long nanoTime) {
		long nanoNow = System.nanoTime();
		return new Date(System.currentTimeMillis()
				+ TimeUnit.NANOSECONDS.toMillis(nanoTime - nanoNow));
	}

	/**
	 * 返回从nanoTime开始到现在的时间间隔，unit表示返回时间的单位
	 * @param nanoTime
	 * @param unit
	 * @return
	 */
	public static long getDurationSinceNano(long nanoTime, TimeUnit unit) {
		return unit.convert(System.nanoTime() - nanoTime, TimeUnit.NANOSECONDS);
	}
}
