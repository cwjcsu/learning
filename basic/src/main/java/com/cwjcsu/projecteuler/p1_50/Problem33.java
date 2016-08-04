package com.cwjcsu.projecteuler.p1_50;
import java.util.HashMap;
/*
 * 
 *
 * 
16/64=1/4
49/98=4/8
26/65=2/5
19/95=1/5
 */

/**
 * 100
 */
public class Problem33 {
	public static void main(String[] args) {
		do2();
		for(String key:result.keySet()){
			System.out.println(key+"="+result.get(key));
		}
	}

	static void do2() {
		for (int x = 1; x <= 9; x++) {
			for (int y = 1; y <= 9; y++) {
				for (int z = 1; z <= 9; z++) {
					help(x, y, z);
					help(x, z, y);
					help(y, x, z);
					help(y, z, x);
					help(z, x, y);
					help(z, y, x);
				}
			}
		}
	}
	static HashMap<String,String> result = new HashMap<String,String>();
	static void help(int x, int y, int z) {
		int n = x * 10 + y;
		int d = x * 10 + z;

		if (n < d && n * z == d * y) {
			// System.out.println(n + "/" + d + "=" + y + "/" + z);
			result.put(n + "/" + d,  y + "/" + z);
		}

		d = x + z * 10;
		if (n < d && n * z == d * y) {
//			System.out.println(n + "/" + d + "=" + y + "/" + z);
			result.put(n + "/" + d,  y + "/" + z);
		}

		n = x + 10 * y;
		d = x * 10 + z;
		if (n < d && n * z == d * y) {
//			System.out.println(n + "/" + d + "=" + y + "/" + z);
			result.put(n + "/" + d,  y + "/" + z);
		}

		d = x + z * 10;
		if (n < d && n * z == d * y) {
//			System.out.println(n + "/" + d + "=" + y + "/" + z);
			result.put(n + "/" + d,  y + "/" + z);
		}

	}
}
