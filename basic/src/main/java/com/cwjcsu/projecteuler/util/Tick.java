package com.cwjcsu.projecteuler.util;

import java.util.concurrent.TimeUnit;

/**
 * 
 * @author atlas
 * @date 2013-4-26
 */
public class Tick {
	private long start = System.nanoTime();

	public long elapsedTime() {
		return TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - start);
	}

	public long elapsedTime(TimeUnit unit) {
		return TimeUnit.NANOSECONDS.convert(System.nanoTime() - start, unit);
	}
}
