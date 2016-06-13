package com.cwjcsu.common;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * 
 * @author atlas
 * @date 2014-1-14
 */
public class TimeoutLineReader implements Callable<List<String>> {

	private final ScheduledExecutorService executor;
	private final long timeout;
	private InputStream inputSource;
	
	//flag for time out
	private volatile boolean timedout = false;
	
	public TimeoutLineReader(InputStream inputSource, long timeout) {
		this(inputSource, timeout, Executors
				.newSingleThreadScheduledExecutor(new NamedThreadFactory(
						"TimeoutReader", true)));
	}

	public TimeoutLineReader(InputStream inputSource, long timeout,
			ScheduledExecutorService executor) {
		this.inputSource = inputSource;
		this.timeout = timeout;
		this.executor = executor;
	}

	public List<String> readLines() {
		Future<List<String>> future = executor.submit(this);
		try {
			return future.get(timeout, TimeUnit.MILLISECONDS);
		} catch (ExecutionException e) {
		} catch (TimeoutException e) {
			timedout = true;
		} catch (InterruptedException e) {
		}
		return Collections.emptyList();
	}

	public List<String> call() throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(
				inputSource));
		String line = null;
		try {
			long dt = Math.max(50, Math.min(timeout / 10, 100));
			while (!timedout&&!in.ready()) {
				Thread.sleep(dt);
			}
			if(timedout){
				return Collections.emptyList();
			}
		} catch (InterruptedException e) {
			return Collections.emptyList();
		}
		List<String> lines = new ArrayList<String>();
		while ((line = in.readLine()) != null) {
			lines.add(line);
		}
		_.ADMIN.debug("{} liens read",lines.size());
		return lines;
	}
}
