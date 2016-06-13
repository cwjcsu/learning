package com.cwjcsu.common;

import java.util.Date;
import java.util.Map;
/**
 * 装饰一个Map，提供便利方法获取值，所有getXXX方法均返回对象，可能是null，所有require均会返回非null的值，如果值是null，会抛出异常。
 * @author atlas
 *
 */
public interface Dict {
	
	<T> T get(String key, T defaultValue);

	<T> T get(String key);

	String getAsString(String key);

	Integer getAsInteger(String key);

	Long getAsLong(String key);

	Double getAsDouble(String key);

	Boolean getAsBoolean(String key);

	String requireString(String key);

	<T> T require(String key);

	int requireInteger(String key);
	
	int requireInteger(String key, int defaultValue);

	long requireLong(String key);

	double requireDouble(String key);

	Boolean requireBoolean(String key);
	
	/**
	 * 直接获取一个date
	 * @param key
	 * @param pattern 参照java.text.SimpleDateFormat里的那个pattern
	 * @return
	 */
	Date getAsDate(String key, String pattern);
	
	Date requireDate(String key, String pattern);

	Map getMap();
}
