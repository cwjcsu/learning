package com.cwjcsu.projecteuler.p51_100;

import java.io.File;
import java.io.IOException;

import com.cwjcsu.projecteuler.util.Util;

public class PE81 {
	// OK,427337
	public static void main(String[] args) throws IOException {
		int[][] data = Util.readIntArray(new File("matrix.txt"), ",");
		int N = data.length;
		int[][] f = new int[N][];
		for (int i = 0; i < N; i++) {
			f[i] = new int[N];
		}
		f[0][0] = data[0][0];
		System.out.println(N);
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (i - 1 < 0 && j - 1 < 0) {
					f[i][j] = data[i][j];
				} else if (i - 1 < 0) {
					f[i][j] = f[i][j - 1] + data[i][j];
				} else if (j - 1 < 0) {
					f[i][j] = f[i - 1][j] + data[i][j];
				} else if (f[i - 1][j] > f[i][j - 1]) {
					f[i][j] = f[i][j - 1] + data[i][j];
				} else {
					f[i][j] = f[i - 1][j] + data[i][j];
				}
			}
		}
		System.out.println(f[N - 1][N - 1]);
	}

	// wrong
	public static void do1() throws IOException {
		int[][] data = Util.readIntArray(new File("matrix1.txt"), ",");
		int s = 0, x = 0, y = 0;
		s += data[x][y];
		int N = data.length;
		System.out.println(N);
		while (true) {
			if (x == N - 1 && y == N - 1) {
				s += data[x][y];
				break;
			} else if (y == N - 1) {
				x++;
			} else if (x == N - 1) {
				y++;
			} else if (data[x + 1][y] > data[x][y + 1]) {
				y++;
			} else {
				x++;
			}
			int got = data[x][y];
			s += got;
		}
		System.out.println(s);
	}
}
