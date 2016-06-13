package com.cwjcsu.common;

import java.util.concurrent.TimeUnit;

/**
 * 
 * @author atlas
 * @date 2013-4-26
 */
public class Tick {
	private long start = System.nanoTime();

	/**
	 * elapsed time in ms since this Object is created
	 * 
	 * @return
	 */
	public long elapsedTime() {
		return TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - start);
	}

	public long elapsedTime(TimeUnit unit) {
		return unit.convert(System.nanoTime() - start, TimeUnit.NANOSECONDS);
	}

	public long elapsedSeconds() {
		return elapsedTime(TimeUnit.SECONDS);
	}
}
