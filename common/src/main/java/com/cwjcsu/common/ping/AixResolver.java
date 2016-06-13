package com.cwjcsu.common.ping;

import java.util.logging.Level;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * @author atlas
 * @date 2012-8-24
 */
class AixResolver implements PingResolver {
	private static Pattern pattern = Pattern.compile("(\\d+)/(\\d+)/(\\d+)");

	public int getPingTime(String output) {
		Matcher matcher = pattern.matcher(output);
		if (matcher.find()) {
			String m1 = matcher.group();
			String[] ss = m1.split("/");
			if (ss.length == 3) {
				try {
					return Integer.valueOf(ss[1]);
				} catch (NumberFormatException e) {
					LOG.log(Level.WARNING, "Can't resolving ping result " + output, e);
					return Integer.MAX_VALUE;
				}
			}
		}
		LOG.log(Level.WARNING, "Can't resolving ping result " + output);
		return Integer.MAX_VALUE;
	}

	public String getPingCommand(int count, String host) {
		return "ping -c " + count + " " + host;
	}

	public String getPingCommand(int count, int w, String host) {
		throw new UnsupportedOperationException("aix ping with timeout");
	}
}
