package com.cwjcsu.projecteuler.p1_50;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class Problem42 {
	// 162
	public static void main(String[] args) throws Exception {
		do1();
	}

	static void do1() throws IOException {
		BufferedReader r = new BufferedReader(new FileReader("words.txt"));
		String s = r.readLine();
		String[] names = s.split(",");
		int c = 0;
		for (int i = 0; i < names.length; i++) {
			names[i] = names[i].substring(1, names[i].length() - 1);
			int sum = calcSum(names[i]);
			if (isTrangleNumber(sum)) {
				c++;
			}
		}
		System.out.println(c);
	}

	private static boolean isTrangleNumber(int sum) {
		int n = (int) Math.ceil(Math.sqrt(2L * sum)) - 1;
		return n * (n + 1) == 2 * sum;
	}

	static int calcSum(String d) {
		int s = 0;
		for (int i = 0; i < d.length(); i++) {
			s += d.charAt(i) - 'A' + 1;
		}
		return s;
	}
}
