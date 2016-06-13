package com.cwjcsu.common;

import java.util.concurrent.ThreadFactory;

public class SingleThreadFactory implements ThreadFactory { 
 

	private final String name;

	private final boolean mDaemo;

	private final ThreadGroup mGroup;

	public SingleThreadFactory(String name) {
		this(name, false);
	}

	public SingleThreadFactory(String name, boolean daemo) {
		this.name = name;
		mDaemo = daemo;
		SecurityManager s = System.getSecurityManager();
		mGroup = (s == null) ? Thread.currentThread().getThreadGroup() : s
				.getThreadGroup();
	}

	public Thread newThread(Runnable runnable) {
		Thread ret = new Thread(mGroup, runnable, name, 0);
		ret.setDaemon(mDaemo);
		return ret;
	}

	public ThreadGroup getThreadGroup() {
		return mGroup;
	}
}