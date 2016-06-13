package com.cwjcsu.common;

import java.util.NoSuchElementException;

/**
 * ported from java.util.LinkedList<E>,removed all unused method
 * 
 * @author atlas
 * @date 2013-3-19
 */
public class FadingLinkedList<E> {

	private transient Entry<E> header = new Entry<E>(null, null, null);

	/**
	 * ms
	 */
	private int livingTime;

	/**
	 * Constructs FadingLinkedList with elements of living time livingTime in
	 * milliseconds
	 */
	public FadingLinkedList(int livingTime) {
		header.next = header.previous = header;
		this.livingTime = livingTime;
	}

	/**
	 * remove all faded elements,
	 * 
	 * @return the count of not faded
	 */
	public synchronized int removeFaded() {
		long now = System.currentTimeMillis();
		int count = 0;
		for (Entry<E> e = header.next; e != header; e = e.next) {
			if ((int) (now - e.birthTime) >= livingTime) {
				// cut off this list here.
				cutOff(e);
				break;
			}
			count++;
		}
		return count;
	}

	private void cutOff(Entry<E> e) {
		if (e == header)
			throw new NoSuchElementException();
		e.previous.next = null;
		e.previous = null;
	}

	/**
	 * Returns the number of elements that not faded.
	 * 
	 */
	public int size() {
		return removeFaded();
	}

	public void push(E e) {
		addBefore(e, header.next);
	}

	private static class Entry<E> {
		E element;
		Entry<E> next;
		Entry<E> previous;

		long birthTime;

		Entry(E element, Entry<E> next, Entry<E> previous) {
			this.element = element;
			this.next = next;
			this.previous = previous;
			birthTime = System.currentTimeMillis();
		}
	}

	private synchronized Entry<E> addBefore(E e, Entry<E> entry) {
		Entry<E> newEntry = new Entry<E>(e, entry, entry.previous);
		newEntry.previous.next = newEntry;
		newEntry.next.previous = newEntry;
		return newEntry;
	}

	public synchronized void clear() {
		Entry<E> e = header.next;
		while (e != header) {
			Entry<E> next = e.next;
			e.next = e.previous = null;
			e.element = null;
			e = next;
		}
		header.next = header.previous = header;
	}

}
