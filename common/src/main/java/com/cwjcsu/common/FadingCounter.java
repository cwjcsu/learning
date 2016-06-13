package com.cwjcsu.common;

/**
 * 在一定时间内的计数，调用{@link #size()}时，在生命周期以前调用的{@link #increment()}不会统计
 * @author atlas
 * @date 2013-3-19
 */
public class FadingCounter extends FadingLinkedList<Void> {

	/**
	 * 
	 * @param livingTime ms
	 */
	public FadingCounter(int livingTime) {
		super(livingTime);
	}

	/**
	 * 增加1
	 */
	public void increment() {
		push(null);
	}

	/**
	 * Returns the count that not faded.
	 */
	public int get() {
		return super.size();
	}

	public void reset() {
		super.clear();
	}
}
