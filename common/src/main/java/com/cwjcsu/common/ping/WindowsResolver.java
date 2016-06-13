package com.cwjcsu.common.ping;

import java.util.logging.Level;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * @author atlas
 * @date 2012-8-24
 */
class WindowsResolver implements PingResolver {

	private static Pattern pattern0 = Pattern.compile("Average(\\s+)=(\\s+)(\\d+)ms");
	private Pattern pattern1 = Pattern.compile("\\d+");

	public int getPingTime(String output) {
		Matcher matcher = pattern0.matcher(output);
		if (matcher.find()) {
			String m1 = matcher.group();
			matcher = pattern1.matcher(m1);
			if (matcher.find()) {
				try {
					return Integer.valueOf(matcher.group());
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
		return "cmd.exe /c chcp 437 & ping -n " + count + " " + host;
	}

	public String getPingCommand(int count, int w, String host) {
		String cmd = "cmd.exe /c chcp 437 & ping -n " + count + " -w " + w + " " + host;
		return cmd;
	}

	public static void main(String[] args) {
		// run this on windows
		System.out.println(Ping.ping("192.168.10.1", 2000));
	}
}
