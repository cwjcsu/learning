package com.cwjcsu.common;

import org.slf4j.helpers.MessageFormatter;

/**
 * 
 * @author atlas
 * @date 2012-12-22
 */
public class MF {
	public static String $(String messagePattern, Object arg) {
		return MessageFormatter.format(messagePattern, arg).getMessage();
	}

	public static String $(final String messagePattern, Object arg1, Object arg2) {
		return MessageFormatter.format(messagePattern, arg1, arg2).getMessage();
	}

	public static String $(final String messagePattern, Object arg1,
			Object arg2, Object arg3) {
		return MessageFormatter.arrayFormat(messagePattern,
				new Object[] { arg1, arg2, arg3 }).getMessage();
	}

	public static String $(final String messagePattern, Object arg1,
			Object arg2, Object arg3, Object arg4) {
		return MessageFormatter.arrayFormat(messagePattern,
				new Object[] { arg1, arg2, arg3, arg4 }).getMessage();
	}

	public static String $(final String messagePattern, final Object[] argArray) {
		return MessageFormatter.arrayFormat(messagePattern, argArray)
				.getMessage();
	}
}
