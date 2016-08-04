package com.cwjcsu.projecteuler.p101_150;

/**
 * 
 * @author atlas
 * @date 2012-9-25
 */
public class Pb150 {
	public static void main(String[] args) {
		int N=6;
		long start = System.currentTimeMillis();
		long[][] memo = new long[N+2][N+2];
		long[] sum = new long[N+1];
		long ans = 273519;
		int a[][] = new int[N][N];
		int b[][] = new int[N][N];

		a[0][0]=15;
		
		a[1][0]=-14;
		a[1][1]=-7;
		
		a[2][0]=20;
		a[2][1]=-13;
		a[2][2]=-5;
		
		a[3][0]=-3;
		a[3][1]=8;
		a[3][2]=23;
		a[3][3]=-26;
		
		a[4][0]=1;
		a[4][1]=-4;
		a[4][2]=-5;
		a[4][3]=-18;
		a[4][4]=5;
		
		a[5][0]=-16;
		a[5][1]=31;
		a[5][2]=2;
		a[5][3]=9;
		a[5][4]=28;
		a[5][5]=3;
		long d=0;
		int I=0,J=0;
		for (int r = 1; r <= N; r++) {
			for (int c = 1; c <= r; c++) {
				sum[c] = sum[c - 1] + a[r-1][c-1];
			}
			for (int c = r; c > 0; c--) {
				for (int c2 = 0; c2 < c; c2++) {
					d = sum[c] - sum[c2];
					memo[c][c - c2] = d + memo[c - 1][c - c2 - 1];
					if (ans > memo[c][c - c2]){
						ans = memo[c][c - c2];
						I=c;
						J=c-c2;
						if(ans==-42){
							System.out.println(memo);
						}
					}
				}
			}
		}
		long used = System.currentTimeMillis() - start;
		System.out.println(ans + ",used " + used);
	}
	
	//271-276 ms
	public static void main2(String[] args) {
		long start = System.currentTimeMillis();
		long[][] memo = new long[1002][1002];
		long[] sum = new long[1001];
		long ans = 273519, s, t = 0, m20 = (1 << 20), m19 = (1 << 19), d;
		for (int r = 1; r <= 1000; r++) {
			for (int c = 1; c <= r; c++) {
				t = (615949 * t + 797807) % m20;
				sum[c] = sum[c - 1] + t - m19;
			}
			for (int c = r; c > 0; c--) {
				for (int c2 = 0; c2 < c; c2++) {
					d = sum[c] - sum[c2];
					memo[c][c - c2] = d + memo[c - 1][c - c2 - 1];
					if (ans > memo[c][c - c2])
						ans = memo[c][c - c2];
				}
			}
		}
		long used = System.currentTimeMillis() - start;
		System.out.println(ans + ",used " + used);
	}
	public static void main10(String[] args) {
		long s = System.currentTimeMillis();
		final int N = 6;
		//保存三角形
		int a[][] = new int[N][N];
		
		int b[][] = new int[N][N];

		a[0][0]=15;
		
		a[1][0]=-14;
		a[1][1]=-7;
		
		a[2][0]=20;
		a[2][1]=-13;
		a[2][2]=-5;
		
		a[3][0]=-3;
		a[3][1]=8;
		a[3][2]=23;
		a[3][3]=-26;
		
		a[4][0]=1;
		a[4][1]=-4;
		a[4][2]=-5;
		a[4][3]=-18;
		a[4][4]=5;
		
		a[5][0]=-16;
		a[5][1]=31;
		a[5][2]=2;
		a[5][3]=9;
		a[5][4]=28;
		a[5][5]=3;
		
		
		int min = Integer.MAX_VALUE;
		for (int d = 0; d <= N - 2; d++) {
			for (int i = 0; i < N - d; i++)
				b[N - 1 - d][i] = a[N - 1 - d][i];

			for (int i = 0; i <= N - 2 - d; i++)
				b[N - 2 - d][i] = a[N - 2 - d][i] + a[N - 1 - d][i]
						+ a[N - 1 - d][i + 1];

			for (int i = N - 3 - d; i >= 0; i--)
				for (int j = 0; j <= i; j++) {
					b[i][j] = a[i][j] + b[i + 1][j] + b[i + 1][j + 1]
							- b[i + 2][j + 1];
					if (min > b[i][j])
						min = b[i][j];
				}
		}
		
		long used = System.currentTimeMillis()-s;

		System.out.println(min+",used "+used);
	}
	/**
	 * 1，从最下面的一行开始，记录从a[i][j]位置为顶点，到最下面一行的三角形的和b[i][j]，选择最小的，
	 * 2，忽略最下面一行，从倒数第二行开始，重复1，记录，直到第2行。
	 * @param args
	 */
	//285-296 ms
	public static void main1(String[] args) {
		long s = System.currentTimeMillis();
		final int N = 1000;
		//保存三角形
		int a[][] = new int[N][N];
		
		int b[][] = new int[N][N];

		long t = 0;
		long mod = (long) Math.pow(2, 20);
		for (int i = 0; i < 1000; i++)
			for (int j = 0; j <= i; j++) {
				t = (615949 * t + 797807) % mod;
				a[i][j] = (int) (t - (mod / 2));
			}

		int min = Integer.MAX_VALUE;
		for (int d = 0; d <= N - 2; d++) {
			for (int i = 0; i < N - d; i++)
				b[N - 1 - d][i] = a[N - 1 - d][i];

			for (int i = 0; i <= N - 2 - d; i++)
				b[N - 2 - d][i] = a[N - 2 - d][i] + a[N - 1 - d][i]
						+ a[N - 1 - d][i + 1];

			for (int i = N - 3 - d; i >= 0; i--)
				for (int j = 0; j <= i; j++) {
					b[i][j] = a[i][j] + b[i + 1][j] + b[i + 1][j + 1]
							- b[i + 2][j + 1];
					if (min > b[i][j])
						min = b[i][j];
				}
		}
		
		long used = System.currentTimeMillis()-s;

		System.out.println(min+",used "+used);
	}
}
