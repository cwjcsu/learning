/*$Id: $
 --------------------------------------
  Skybility
 ---------------------------------------
  Copyright By Skybility ,All right Reserved
 * author                     date    comment
 * chenweijun@skybility.com  2015年9月22日  Created
 */
package com.cwjcsu.projecteuler.p151_200;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.cwjcsu.projecteuler.util.IntVector2D;
import com.cwjcsu.projecteuler.util.Util;

/**
 * best answer:Robert_Gerbicz on https://projecteuler.net/thread=184
 * @author atlas
 *
 */
public class PE184 {

	/**
	 * x^2 + y^2 < r ^2 (x,y) in Ir
	 * 
	 * @param r
	 * @return
	 */
	public static int countTriangleByR(int r) {
		IntVector2D origin = new IntVector2D(0, 0);
		List<IntVector2D> points = new ArrayList<IntVector2D>();
		int MAX = (int) (r / Math.sqrt(2));
		for (int x = -MAX; x <= MAX; x++) {
			for (int y = -MAX; y <= MAX; y++) {
				points.add(new IntVector2D(x, y));
			}
		}
		int sum = 0;
		for (int i = 0; i < points.size(); i++) {
			for (int j = i + 1; j < points.size(); j++) {
				for (int k = j + 1; k < points.size(); k++) {
					IntVector2D A = points.get(i);
					IntVector2D B = points.get(j);
					IntVector2D C = points.get(k);
					if(Util.isTriangle(A,B,C) && Util.triangleContainsPoint(A, B, C, origin)){
						sum ++;
					}
					
				}
			}
		}
		return sum;
	}
	
	public static void main1(String[] args) {
		//I2 = 8
		//I3 = 360
		//I5 = 10600 我的计算值是3536
		//I105=40730
		System.out.println(countTriangleByR(105));
	}
	public static void main(String[] args) {
		long start = System.nanoTime();

		int r = 105;
		int[][] arr = new int[r * r][2];
		int position = 0;
		long result = 0;

		for (int y = 0; y < r; y++) {
			for (int x = r - 1; x > 0; x--) {
				if (y * y + x * x < r * r) {
					arr[position][0] = y;
					arr[position][1] = x;
					position++;
				}
			}
		}

		int[][] array = new int[position][2];
		System.arraycopy(arr, 0, array, 0, position);

		for (int i = 0; i < array.length; i++) {
			for (int j = i + 1; j < array.length; j++) {
				if (array[i][0] * array[j][1] > array[j][0] * array[i][1]) {
					int temp[] = new int[2];
					temp = array[i];
					array[i] = array[j];
					array[j] = temp;
				}
			}
		}

		LinkedList<Integer> l = new LinkedList<Integer>();
		int temp = 1;

		for (int i = 0; i < position - 1; i++) {
			if (array[i][0] * array[i + 1][1] == array[i + 1][0] * array[i][1]) {
				temp++;
			} else {
				l.add(new Integer(temp));
				temp = 1;
			}
		}

		l.add(new Integer(1));
		int size = l.size();

		for (int i = 0; i < size; i++) {
			l.add(l.get(i));
		}

		size = l.size();
		double tem1 = 0;
		double tem2 = 0;

		for (int i = size - 1; i > 0; i--) {
			tem1 = tem1 + ((Integer) (l.get(i))).intValue();
			tem2 = tem2 + ((Integer) (l.get(i))).intValue()
					* ((Integer) (l.get(i))).intValue();
			result += ((Integer) (l.get(i - 1))).intValue()
					* (tem1 * tem1 - tem2) / 2;
		}

		result *= 2;

		long end = System.nanoTime();
		long runtime = end - start;
		System.out.println(result);
		System.out.println("Runtime: " + runtime / 1000000 + "ms (" + runtime
				+ "ns)");
	}
}
