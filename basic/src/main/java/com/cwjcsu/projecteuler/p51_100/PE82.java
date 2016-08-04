package com.cwjcsu.projecteuler.p51_100;

import java.io.File;
import java.io.IOException;

import com.cwjcsu.projecteuler.util.Util;

//see PE81
public class PE82 {
	static int[][] data;
	static int[][] f;
	static int N;

	// OK see 18, 67, 81 & 83.
	public static void main(String[] args) throws IOException {
		data = Util.transportMatrix(Util.readIntArray(new File("matrix82.txt"),
				","));
		N = data.length;

		f = new int[N][];
		for (int i = 0; i < N; i++) {
			f[i] = new int[N];
		}
		System.out.println(N);
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (i == 0) {
					f[i][j] = data[i][j];
				} else {
					int min = Integer.MAX_VALUE;
					for (int x = 0; x < N; x++) {
						int a = 0;
						if (x <= j) {
							a += Util.sum(data[i], x, j) + f[i - 1][x];
						} else {
							a += Util.sum(data[i], j, x) + f[i - 1][x];
						}
						if (min > a) {
							min = a;
						}
					}
					f[i][j] = min;
				}
			}
		}
		System.out.println(Util.min(f[N - 1]));
	}

	public void do2() throws IOException {
		data = Util.readIntArray(new File("matrix82_1.txt"), ",");
		N = data.length;
		f = new int[N][];
		for (int i = 0; i < N; i++) {
			f[i] = new int[N];
		}
		System.out.println(N);
		int y = N - 1;
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < N; i++) {
			if (f(i, y) < min) {
				min = f(i, y);
			}
		}
		System.out.println(min);
	}

	public static int f(int i, int j) {
		if (f[i][j] > 0) {
			return f[i][j];
		}
		if (j == 0) {
			f[i][j] = data[i][j];
			return f[i][j];
		}
		int a, b, c = 0;
		if (i == 0)
			a = Integer.MAX_VALUE;
		else
			a = f(i - 1, j);

		b = f(i, j - 1);
		if (i == N - 1) {
			c = Integer.MAX_VALUE;
		} else
			c = f(i + 1, j);
		int x, y;
		int min = a > b ? b : a;
		if (a > b) {
			x = i - 1;
			y = j;
		} else {
			x = i;
			y = j - 1;
		}
		if (min > c) {
			x = i + 1;
			y = j;
		}
		f[i][j] = data[i][j] + f(x, y);
		return f[i][j];
	}

	public static void do1() throws IOException {
		int[][] data = Util.readIntArray(new File("matrix82_1.txt"), ",");
		int N = data.length;
		int[][] f = new int[N][];
		for (int i = 0; i < N; i++) {
			f[i] = new int[N];
		}
		System.out.println(N);
		for (int j = 0; j < N; j++) {
			for (int i = 0; i < N; i++) {
				if (j == 0) {
					f[i][j] = data[i][j];
				} else {
					int x, y;
					if (i == 0) {
						if (f[i][j - 1] > f[i + 1][j]) {
							x = i + 1;
							y = j;
						} else {
							x = i;
							y = j - 1;
						}
					} else if (i == N - 1) {
						if (f[i][j - 1] > f[i - 1][j]) {
							x = i - 1;
							y = j;
						} else {
							x = i;
							y = j - 1;
						}
					} else {
						if (f[i - 1][j] > f[i][j - 1]) {
							x = i;
							y = j - 1;
						} else {
							x = i - 1;
							y = j;
						}
						if (f[x][y] > f[i + 1][j]) {
							x = i + 1;
							y = j;
						}
					}
					f[i][j] = f[x][y] + data[i][j];
				}
			}
		}
		int y = N - 1;
		int x = 0;
		int min = f[x][y];
		for (int i = 1; i < N; i++) {
			if (f[i][y] < min) {
				x = i;
				min = f[i][y];
			}
		}
		System.out.println(min);
	}
}
