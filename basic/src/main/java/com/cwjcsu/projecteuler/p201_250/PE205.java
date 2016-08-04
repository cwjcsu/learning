package com.cwjcsu.projecteuler.p201_250;

/**
 * perter可以得分是9到36,colin是6到36， 用两个int[]记录他们每次得分的可能性，除以所有情况，就得到每个得分的概率了。然后相加
 * 
 * @author atlas
 * @date 2013-4-26
 */
public class PE205 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// perter 的得分所有情况
		int m1 = 4 * 4 * 4 * 4 * 4 * 4 * 4 * 4 * 4;
		// colin得分所有情况
		int m2 = 6 * 6 * 6 * 6 * 6 * 6;
		// r1[i]是perter得分i-1的所有可能组合数，r2[i]是colin得分i-1的所有可能组合
		int[] r1 = new int[36];
		int[] r2 = new int[36];
		// p1[i]是perter得分i-1的概率，p2[i]是colin得分i-1的概率
		double[] p1 = new double[36];
		double[] p2 = new double[36];
		for (int k1 = 1; k1 <= 4; k1++) {
			for (int k2 = 1; k2 <= 4; k2++) {
				for (int k3 = 1; k3 <= 4; k3++) {
					for (int k4 = 1; k4 <= 4; k4++) {
						for (int k5 = 1; k5 <= 4; k5++) {
							for (int k6 = 1; k6 <= 4; k6++) {
								for (int k7 = 1; k7 <= 4; k7++) {
									for (int k8 = 1; k8 <= 4; k8++) {
										for (int k9 = 1; k9 <= 4; k9++) {
											r1[k1 + k2 + k3 + k4 + k5 + k6 + k7
													+ k8 + k9 - 1] += 1;
										}
									}
								}
							}
						}
					}
				}
			}
		}
		for (int a = 1; a <= 6; a++) {
			for (int b = 1; b <= 6; b++) {
				for (int c = 1; c <= 6; c++) {
					for (int d = 1; d <= 6; d++) {
						for (int e = 1; e <= 6; e++) {
							for (int f = 1; f <= 6; f++) {
								r2[a + b + c + d + e + f - 1] += 1;
							}
						}
					}
				}
			}
		}
		for (int i = 0; i < 36; i++) {
			p1[i] = 1.0 * r1[i] / m1;
			p2[i] = 1.0 * r2[i] / m2;
		}
		double result = 0;
		for (int i = 8; i < 36; i++) {
			for (int j = 5; j < i; j++) {
				result += p1[i] * p2[j];
			}
		}
		System.out.println(result);
	}

	/**
	 * n个骰子，s个面(1->s)，投掷出c的概率
	 * 
	 * @param n
	 * @param s
	 * @param c
	 * @return
	 */
	public double p(int n, int s, int c) {
		if (c < n) {
			return 0.0;
		}
		if (c == n) {
			return 1.0 / n;
		}
		return 0.0;
	}

}
