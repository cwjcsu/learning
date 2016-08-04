package com.cwjcsu.projecteuler.p51_100;
 
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

// 取对数
public class P99 {
	public static void main(String[] args) throws Exception {
		do1();
	}

	static void do1() throws Exception {
		File f = new File("base_exp.txt");
		InputStream is = new FileInputStream(f);
		InputStreamReader reader = new InputStreamReader(is);
		BufferedReader r = new BufferedReader(reader);
		String red = null;
		int I = 0;
		int i = 0;
		double M = 0;
		while ((red = r.readLine()) != null) {
			String[] ds = red.split(",");
			int d1 = Integer.valueOf(ds[0]);
			int d2 = Integer.valueOf(ds[1]);
			double m1 = d2 * Math.log(d1);
			i++;
			if (m1 > M) {
				M = m1;
				I = i;
			}
		}
		System.out.println(I);
	}
}
