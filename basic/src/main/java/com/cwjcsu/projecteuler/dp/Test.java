package com.cwjcsu.projecteuler.dp;

public class Test {
	public static void main(String[] args) {
		String str = "(A(B)(C(EF)G)H)";
		System.out.println(getNth(str, 2));
	}

	static String getNth(String str, int n) {
		int s1 = 0;
		int s2 = 0;
		int I = 0;
		int J = 0;
		for (int i = 0, j = str.length() - 1; i < str.length(); i++) {
			char ch = str.charAt(i);
			if (ch == '(') {
				s1++;
			}
			if (s1 == n) {
				I = i + 1;
				break;
			}
		}
		for (int j = str.length() - 1; j >= 0; j--) {
			char ch = str.charAt(j);
			if (ch == ')') {
				s2++;
			}
			if (s2 == n) {
				J = j;
				break;
			}
		}
		return str.substring(I, J);
	}
}
