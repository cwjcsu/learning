package com.cwjcsu.projecteuler.p101_150;
 

import java.math.BigInteger;

import com.cwjcsu.projecteuler.util.Util;

public class PE112_113 {
	public static void main(String[] args) {

	}

	static void do113() {
		int B = 0;
		for (int i = 1;; i++) {
			if (isBouncy(i)) {
				B++;
			}
			 
		}
	}

	static void do112() {
		int B = 0;
		for (int i = 1;; i++) {
			if (isBouncy(i)) {
				B++;
			}
			if (B * 100 == 99 * i) {
				System.out.println("I:" + i);
				break;
			}
		}
	}

	static boolean isBouncy(long d) {
		int[] dd = Util.toDigit(d);
		int A = 0;
		int D = 0;
		for (int i = 0; i < dd.length - 1; i++) {
			if (dd[i] - dd[i + 1] > 0) {
				D++;
			} else {
				A++;
			}
			if (A != 0 && D != 0) {
				return true;
			}
		}
		return false;
	}

	static int[] getDigitArray(String ds) {
		int[] d = new int[ds.length()];
		for (int i = 0; i < ds.length(); i++) {
			d[i] = ds.charAt(i) - '0';
		}
		return d;
	}

	static boolean isBouncy(BigInteger d) {
		String ds = d.toString(10);
		int[] dd = getDigitArray(ds);
		int A = 0;
		int D = 0;
		for (int i = 0; i < dd.length - 1; i++) {
			if (dd[i] - dd[i + 1] > 0) {
				D++;
			} else {
				A++;
			}
			if (A != 0 && D != 0) {
				return true;
			}
		}
		return false;
	}
}
