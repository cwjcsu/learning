package com.cwjcsu.projecteuler.dp;

/**
 * 
 * @author Sunny
 * 
 */
public class MaxCover {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	/**
	 * L=50 G[]={3,5,23,123,134,135,155}
	 * 
	 * @param G
	 */
	void maxCover(int[] data, int L) {
		int l = 0;
		int s = 0, e = 0;
		for (int i = 0; i < data.length; i++) {
			// s=G[i];
			if (data[i] - data[s] < L) {
				e = i;
			}
		}

	}

}
