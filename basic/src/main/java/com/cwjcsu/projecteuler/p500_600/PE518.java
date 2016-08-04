/*$Id: $
 --------------------------------------
  Skybility
 ---------------------------------------
  Copyright By Skybility ,All right Reserved
 * author                     date    comment
 * chenweijun@skybility.com  2015年10月20日  Created
 */
package com.cwjcsu.projecteuler.p500_600;

import com.cwjcsu.projecteuler.util.IndexSorter2;
import com.cwjcsu.projecteuler.util.Util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * result:100315739184392
 * @author atlas
 *
 */
public class PE518 {

	public static void main(String[] args) {
		int N = (int) Math.pow(10, 8);
		// 10000000
		// int N = 100;
		int[] primes = Util.getPrimesBlow(N);// length:5761455
		// Map<Integer, Divisor> factorMap = new HashMap<Integer, Divisor>(
		// primes.length);
		Map<Long, Integer> squares = new HashMap<Long, Integer>();
		for (int i = 0; i < primes.length; i++) {
			squares.put(1L * (primes[i] + 1) * (primes[i] + 1), primes[i]);
		}

		/**
		 * c=complement[i]对应素数p=primes[i]，(p+1)*c构成比p大的最小平方数。
		 * 
		 */
		int[] complements = new int[primes.length];
		for (int i = 0; i < primes.length; i++) {
			int n = primes[i] + 1;
			int complement = 1;
			for (int j = 0; j < primes.length && n > 1; j++) {
				int prime = primes[j];
				int e = 0;
				while (n % prime == 0) {
					e++;
					n = n / prime;
				}
				if (e > 0) {
					if (e % 2 != 0) {
						complement *= prime;
					}
				}
			}
			complements[i] = complement;
			if (i % (primes.length / 100) == 0) {
				System.out.println(i + " Complements=> " + primes[i] + ":"
						+ complement);
				System.gc();
			}
		}
		// 将complements[i]的索引按照complement从小到大排序
		// complementIndexes[j]的值是complements的索引
		int[] complementIndexes = new int[complements.length];
		for (int i = 0; i < complementIndexes.length; i++) {
			complementIndexes[i] = i;
		}
		IndexSorter2.quicksort(complements, complementIndexes);
		System.out.println("Complements sorted");
		List<Integer> sameComplementPrimes = new ArrayList<Integer>();
		int currentComplement = 0;
		long sum = 0;
		for (int i = 0; i < complementIndexes.length; i++) {
			int newCurrentComplement = complements[complementIndexes[i]];
			if (newCurrentComplement > currentComplement) {
				if (currentComplement > 0) {
					sum += caculateCurrentComplements(sameComplementPrimes,
							squares);
				}
				sameComplementPrimes.clear();
				currentComplement = newCurrentComplement;
			}
			sameComplementPrimes.add(primes[complementIndexes[i]]);
		}
		System.out.println(sum);
	}

	private static long caculateCurrentComplements(List<Integer> complements,
			Map<Long, Integer> squares) {
		long sum = 0;
		for (int i = 0; i < complements.size(); i++) {
			int a = complements.get(i);
			for (int j = i + 1; j < complements.size(); j++) {
				int c = complements.get(j);
				long square = 1L * (a + 1) * (c + 1);
				Integer b = squares.get(square);
				if (b != null) {
					sum += a + b + c;
				}
			}
		}
		return sum;
	}

	public static boolean isGeometricSequence(long a, long b, int c) {
		return a * c == b * b;
	}

}
