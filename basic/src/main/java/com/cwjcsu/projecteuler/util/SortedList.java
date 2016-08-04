package com.cwjcsu.projecteuler.util;

public class SortedList<C extends Comparable<C>> {
	private E head;
	private E tail;

	public void insert(C c) {
//		if (head == null) {
//			head = tail = new E<C>(c);
//		} else {
//			E h = head;
//			while (h != null) {
//				if (c.compareTo(h.value) >= 0) {
//
//				}
//			}
//		}
	}

	private class E<C extends Comparable<C>> {
		public E(C c) {
			this.value = c;
		}

		E next;
		C value;
	}
}
