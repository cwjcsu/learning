package com.cwjcsu.projecteuler.util;

public class QuickSort {
	private QuickSort() {
	}

	private static void swap(int a[], int i, int j) {
		int tmp = a[i];
		a[i] = a[j];
		a[j] = tmp;
	}

	private static void swap(long a[], int i, int j) {
		long tmp = a[i];
		a[i] = a[j];
		a[j] = tmp;
	}

	private static int partition(int a[], int p, int r) {
		int point = a[r];
		// ��С�ڵ���point��Ԫ���Ƶ��������
		// ������point��Ԫ���Ƶ��ұ�����
		int index = p;
		for (int i = index; i < r; ++i) {
			if (a[i] - point <= 0) {
				swap(a, index++, i);
			}
		}
		swap(a, index, r);
		return index;
	}

	private static int partition(long a[], int p, int r) {
		long point = a[r];
		// ��С�ڵ���point��Ԫ���Ƶ��������
		// ������point��Ԫ���Ƶ��ұ�����
		int index = p;
		for (int i = index; i < r; ++i) {
			if (a[i] - point <= 0) {
				swap(a, index++, i);
			}
		}
		swap(a, index, r);
		return index;
	}

	public static void quickSort(int a[], int fromIndex, int toIndex) {
		if (fromIndex < toIndex) {
			// ȷ����ֵ㣬��������Ԫ�ؽ����ƶ�
			// ���ǿ��������㷨�Ĺؼ�����
			int q = partition(a, fromIndex, toIndex);
			// ����������
			quickSort(a, fromIndex, q - 1);
			// ���Ұ������
			quickSort(a, q + 1, toIndex);
		}
	}

	public static void quickSort(int a[]) {
		quickSort(a, 0, a.length - 1);
	}

	public static void quickSort(long a[], int fromIndex, int toIndex) {
		if (fromIndex < toIndex) {
			// ȷ����ֵ㣬��������Ԫ�ؽ����ƶ�
			// ���ǿ��������㷨�Ĺؼ�����
			int q = partition(a, fromIndex, toIndex);
			// ����������
			quickSort(a, fromIndex, q - 1);
			// ���Ұ������
			quickSort(a, q + 1, toIndex);
		}
	}
}
