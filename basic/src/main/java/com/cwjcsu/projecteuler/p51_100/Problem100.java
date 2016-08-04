package com.cwjcsu.projecteuler.p51_100;
import java.math.BigInteger;

public class Problem100 {
	/**
	 * 2n(n-1)=(n+m)(n+m-1) n+m>=10^12 min n+m
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(check(85, 35));
		do1();
	}

	static void do1() {
		long e = 1000000000000L;
		long n = (long) (e / Math.sqrt(2)) - 1;
		System.out.println("N start:" + n);
		for (;; n++) {
			double tm = getM(n);
			long m = (long) tm;
			if (check(n, m) == 0) {
				System.out.println("blue:" + n + ",red:" + m);
				break;
			}
			if(check(n,m+1)==0){
				System.out.println("blue:" + n + ",red:" + (m+1));
				break;				
			}
		}
		// for (long d = 0;; d++) {
		// long m = e + d - n;
		// if (2 * m * n + n + m * m - m - n * n == 0) {
		//
		// }
		// }
	}

	static int check2(int n, int m) {
		return m * m - m + 2 * n * m + n - n * n;
	}

	// check OK
	static long check(long n, long m) {
		BigInteger N = BigInteger.valueOf(n);
		// BigInteger M = BigInteger.valueOf(m);
		// BigInteger N_1 = BigInteger.valueOf(n - 1);
		BigInteger M$N = BigInteger.valueOf(m + n);
		BigInteger M$N_1 = BigInteger.valueOf(m + n - 1);
		return N.multiply(N.subtract(BigInteger.ONE))
				.multiply(BigInteger.valueOf(2)).subtract(M$N.multiply(M$N_1))
				.longValue();
		// return (n * (n - 1) << 1) - (n + m) * (n + m - 1);
	}

	static double getM(long n) {
		BigInteger i = BigInteger.valueOf(n);
		i = i.multiply(i);
		return (Math.sqrt(i.doubleValue() - (n << 3) + 1) - 2 * n + 1) / 2;
	}

	// static long getM(int n) {
	// return (long)(Math.sqrt(8 * n * n - 8 * n + 1) - 2 * n + 1) / 2;
	// }

	static boolean check3(int n, int m) {
		return Math.sqrt(8 * n * n - 8 * n + 1) - 2 * n + 1 == 2 * m;
	}

}
