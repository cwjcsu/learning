package com.cwjcsu.projecteuler.p101_150;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * TODO
 * 
 * @author atlas
 * 
 */
public class Problem102 {
	// (-340,495), B(-153,-910), C(835,-947)
	public static void main(String[] args) throws IOException {
		Point O = new Point(0, 0);

		FileInputStream is = new FileInputStream(new File("triangles.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		int count = 0;
		String line = null;
		while ((line = br.readLine()) != null) {
			String[] datas = line.split(",");
			int[] d = new int[6];
			for (int i = 0; i < 6; i++) {
				d[i] = Integer.parseInt(datas[i]);
			}
			int i = 0;
			if (true/**xxxx*/) {
				count++;
			}
		}
		System.out.println(count);
	}

	

	static class Point {
		int x;
		int y;

		public Point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}

	static class Triangle {
		Point x;
		Point y;
		Point z;

		double a, b, c;

		double area;

		public Triangle(Point x, Point y, Point z) {
			super();
			this.x = x;
			this.y = y;
			this.z = z;
		}

		private void caculate() {
			a = distance(x, y);
			b = distance(y, z);
			c = distance(z, x);
			double p = (a + b + c) / 2;
			area = Math.sqrt(p * (p - a) * (p - b) * (p - c));
		}
		
		public double getArea() {
			return area;
		}
	}

	public static double distance(Point a, Point b) {
		return Math.sqrt((a.x - b.x) ^ 2 + (a.y - b.y) ^ 2);
	}
}
