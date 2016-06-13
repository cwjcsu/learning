package com.cwjcsu.common.ping;

import java.util.logging.Level;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * @author atlas
 * @date 2012-8-24
 */
class LinuxResolver implements PingResolver {
	private static Pattern pattern = Pattern.compile("(\\d+).(\\d+)/(\\d+).(\\d+)/(\\d+).(\\d+)/(\\d+).(\\d+)");

	public int getPingTime(String output) {
		Matcher matcher = pattern.matcher(output);
		if (matcher.find()) {
			String m1 = matcher.group();
			String[] ss = m1.split("/");
			if (ss.length == 4) {
				try {
					return Double.valueOf(ss[1]).intValue();
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
		return "ping -c " + count + " -w " + w + " " + host;
	}

	public static void main(String[] args) {
		String out = "PING 192.168.122.1 (192.168.122.1) 56(84) bytes of data.\n"
				+ "64 bytes from 192.168.122.1: icmp_seq=1 ttl=64 time=0.262 ms\n"
				+ "--- 192.168.122.1 ping statistics ---"
				+ "1 packets transmitted, 1 received, 0% packet loss, time 0ms\n"
				+ "rtt min/avg/max/mdev = 0.262/0.262/0.262/0.000 ms";
		System.out.println(new LinuxResolver().getPingTime(out));
	}
}
