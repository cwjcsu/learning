package com.cwjcsu.projecteuler.p51_100;
import com.cwjcsu.projecteuler.util.Util;

//121313
public class Problem51 {
	static int[] primes = Util.getPrimesBlow(30000000);

	public static int factory(int n) {
		int result = 1;

		for (int i = 1; i <= n; i++) {
			result *= i;
		}

		return result;
	}

	public static int[][] getCombinations(int n, int[] array) {
		int[][] result;

		if (n == 1) {
			result = new int[array.length][1];

			for (int i = 0; i < array.length; i++) {
				result[i][0] = array[i];
			}
		} else if (n == array.length) {
			result = new int[1][];

			result[0] = array.clone();
		} else {
			int numberOfCombinations = factory(array.length)
					/ (factory(n) * factory(array.length - n));
			result = new int[numberOfCombinations][n];
			int index = 0;

			for (int i = 0; i <= array.length - n; i++) {
				int[] a = new int[array.length - i - 1];

				for (int j = 0; j < a.length; j++) {
					a[j] = array[i + 1 + j];
				}

				int[][] left = getCombinations(n - 1, a);

				for (int j = 0; j < left.length; j++) {
					result[index][0] = array[i];

					for (int m = 0; m < left[j].length; m++) {
						result[index][m + 1] = left[j][m];
					}

					index++;
				}
			}
		}

		return result;
	}

	public static void main(String[] args) {
		int result = 0;
		int counter = 0;

		while (true) {
			if (isPrime(result)) {
				String number = String.valueOf(result);

				for (int numberOfSameDigiters = 2; numberOfSameDigiters <= number
						.length(); numberOfSameDigiters++) {
					for (int index = 0; index <= number.length()
							- numberOfSameDigiters; index++) {
						if (number.charAt(index) - '0' > 2) {
							continue;
						}

						int[] indexes;
						int c = 0;

						for (int i = index + 1; i < number.length(); i++) {
							if (number.charAt(i) == number.charAt(index)) {
								c++;
							}
						}

						indexes = new int[c];
						c = 0;

						for (int i = index + 1; i < number.length(); i++) {
							if (number.charAt(i) == number.charAt(index)) {
								indexes[c] = i;
								c++;
							}
						}

						int[][] p = getCombinations(numberOfSameDigiters - 1,
								indexes);

						for (int m = 0; m < p.length; m++) {
							int v = number.charAt(index) - '0' + 1;
							char[] s = new String(number).toCharArray();

							while (v <= 9) {
								char z = (char) (v + '0');
								s[0] = z;

								for (int b = 0; b < p[m].length; b++) {
									s[p[m][b]] = z;
								}

								int y = Integer.valueOf(new String(s));

								if (isPrime(y)) {
									counter++;
								}

								v++;
							}

							if (counter >= 7) {
								System.out.println(result);
								return;
							} else {
								counter = 0;
							}
						}

					}
				}
			}

			result++;
		}
	}

	public static boolean isPrime(int e) {
		double sqrtE = Math.sqrt(e);
		for (int i = 0; i < primes.length; i++) {
			int p = primes[i];
			if (p > sqrtE) {
				return true;
			}
			if (e % p == 0) {
				return false;
			}
		}
		System.err.println("Can't decide " + e);
		return false;
	}
}
