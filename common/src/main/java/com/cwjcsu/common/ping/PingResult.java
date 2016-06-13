package com.cwjcsu.common.ping;

public class PingResult implements Comparable<PingResult> {
	private String host;
	private int time;

	public PingResult(String host, int time) {
		super();
		this.host = host;
		this.time = time;
	}

	public String getHost() {
		return host;
	}

	public int getTime() {
		return time;
	}

	public int compareTo(PingResult o) {
		return time - o.time;
	}

	@Override
	public String toString() {
		return host + "=" + time;
	}

}