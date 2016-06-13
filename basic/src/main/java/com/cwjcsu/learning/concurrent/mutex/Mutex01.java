package com.cwjcsu.learning.concurrent.mutex;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 一个互斥变量
 * 
 * @author atlas
 * 
 */
public class Mutex01 {
	private final ReentrantLock lock;
	private final Condition readyCond;

	public Mutex01(boolean fair) {
		lock = new ReentrantLock(fair);
		readyCond = lock.newCondition();
	}
	public Mutex01() {
		this(false);
	}

	private volatile boolean available = true;

	public void acquireInterruptibly() throws InterruptedException {
		lock.lock();
		try {
			while (!available) {
				readyCond.await();
			}
			available = false;
		} finally {
			lock.unlock();
		}
	}

	public void acquire() {
		lock.lock();
		try {
			while (!available) {
				try {
					readyCond.await();
				} catch (InterruptedException e) {
					// ignore
				}
			}
			available = false;
		} finally {
			lock.unlock();
		}
	}

	public boolean isAvailable() {
		lock.lock();
		try {
			boolean ret = available;
			return ret;
		} finally {
			lock.unlock();
		}
	}

	public void release() {
		lock.lock();
		try {
			this.available = true;
			this.readyCond.signalAll();
		} finally {
			lock.unlock();
		}
	}
}
