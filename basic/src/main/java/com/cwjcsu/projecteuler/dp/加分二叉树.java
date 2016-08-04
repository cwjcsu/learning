package com.cwjcsu.projecteuler.dp;

import java.util.Arrays;
import java.util.Random;

public class 加分二叉树 {
	public static void main(String[] args) {
		int n = 10;
		int[] d = new int[n];
		
		Random random = new Random(n);
		for (int i = 0; i < n; i++) {
			d[i] = random.nextInt();
		}
		print(d);
		System.out.println(maxScore(d));
	}

	// OK
	static void do1() {
		int[] d = { 5, 7, 1, 2, 10 };

		System.out.println(maxScore(d));
	}

	static Result maxScore1(int[] d) {
		int n = d.length;
		// m[i][j]表示从节点i->j形成的字数的最高加分k

		int[][] m = new int[n + 1][n + 1];
		for (int i = 1; i <= n; i++) {
			m[i][1] = d[i - 1];
			if (i < n) {
				m[i][2] = d[i - 1] + d[i];
			}
		}
		for (int i = 1; i <= n; i++) {
			for (int j = 3; j <= n - i + 1; j++) {
				int max = Integer.MIN_VALUE;
				for (int k = i + 1; k < j; k++) {
					int tmp = m[i][k - 1] * m[k + 1][j] + d[k - 1];
					if (tmp > max) {
						max = tmp;
					}
				}
				m[i][j] = max;
			}
		}
		Result r = new Result();
		r.score = m[1][n];
		return null;
	}

	static class Node {
		Node left;
		Node right;
		int value;
	}

	static Result maxScore(int[] d) {
		int n = d.length;
		int[][] m = new int[n + 1][n + 2];
		int[][] K = new int[n + 1][n + 2];
		Arrays.fill(m[0], 1);
		System.arraycopy(d, 0, m[1], 1, n);
		for (int j = 1; j <= n - 1; j++) {
			m[2][j] = d[j - 1] + d[j];
			K[2][j] = j;
			K[1][j] = j;
			m[j][n + 1] = 1;// 下面循环会取到这个数，它是1，表示空树
		}

		for (int i = 3; i <= n; i++) {//
			for (int j = 1; j <= n - (i - 1); j++) {// m[i,j],j可取到使i+j=n+1
				int max = Integer.MIN_VALUE;
				int J = 0;
				for (int k = j; k <= j + i - 1; k++) {
					int tmp = m[k - j][j] * m[i - (k - j) - 1][k + 1]
							+ d[k - 1];
					if (tmp > max) {
						max = tmp;
						J = k;
					}
				}
				K[i][j] = J;
				m[i][j] = max;
			}
		}
//		print(m);
		System.out.println("----------");
//		print(K);
		Result result = new Result();
		result.score = m[n][1];

		Node root = new Node();
		root.value = K[n][1];
		Calc(root, K);
		System.out.println("check score:" + checkScore(root, d));
		result.preorder = new int[d.length+1];
		preorder(root, result.preorder);

		return result;
	}

	static int Index = 0;

	static int[] preorder(Node node, int[] preorder) {
		if (node == null) {
			return preorder;
		}
		preorder[Index++] = node.value;
		preorder(node.left, preorder);
		preorder(node.right, preorder);
		return preorder;
	}

	static int checkScore(Node node, int[] d) {
		if (node == null)
			return 1;
		if (node.left == null && node.right == null) {
			return d[node.value - 1];
		}
		return d[node.value - 1] + checkScore(node.left, d)
				* checkScore(node.right, d);
	}

	static void CalcLeft(Node root, int[][] k, int s, int t) {
		if (s > t)
			return;
		Node left = new Node();
		if (s == t) {
			left.value = s;
			root.left = left;
			return;
		}
		left.value = k[t][1];
		root.left = left;
		CalcLeft(left, k, s, left.value - 1);
		CalcRight(left, k, left.value + 1, t);
	}

	static void CalcRight(Node root, int[][] k, int s, int t) {
		if (s > t)
			return;
		Node right = new Node();
		if (s == t) {
			right.value = s;
			root.right = right;
			return;
		}
		right.value = k[t - s][root.value + 1];
		root.right = right;
		CalcLeft(right, k, s, right.value - 1);
		CalcRight(right, k, right.value + 1, t);
	}

	static void Calc(Node root, int[][] k) {
		CalcLeft(root, k, 1, root.value - 1);
		CalcRight(root, k, root.value + 1, k[0].length - 2);
	}

	static void print(int[] m) {
		for (int i = 0; i < m.length; i++) {
			System.out.print(String.format("%2d ", m[i]));
		}
		System.out.println();
	}

	static void print(int[][] m) {
		for (int i = 0; i < m.length; i++) {
			System.out.print("(i,j)=" + "(" + i + ",j) ");
			for (int j = 0; j < m[i].length; j++) {
				System.out.print(String.format("%3d ", m[i][j]));
			}
			System.out.println();
		}
	}

	static class Result {
		int score;
		int[] preorder;

		public String toString() {
			String str = "Score:" + score + "\n";
			if (preorder != null) {
				for (int i = 0; i < preorder.length; i++) {
					str += preorder[i] + " ";
				}
			}
			return str;
		}
	}
}
