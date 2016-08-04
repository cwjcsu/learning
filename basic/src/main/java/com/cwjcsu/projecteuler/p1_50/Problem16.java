package com.cwjcsu.projecteuler.p1_50;
import java.math.BigInteger;

public class Problem16 {
	public static void main(String[] args) {
		byte[] data = new byte[126];
		data[0] = 1;
		for (int i = 1; i < data.length; i++) {
			data[i] = 0;
		}
		String str = "1";
		for (int i = 0; i < 1000; i++) {
			str += "0";
		}
		BigInteger i = new BigInteger(str, 2);
		String r = i.toString(10);
		int s = 0;
		for (int j = 0; j < r.length(); j++) {
			char c = r.charAt(j);
			s += Integer.parseInt(String.valueOf(c));
		}
		System.out.println(s);
	}

}
