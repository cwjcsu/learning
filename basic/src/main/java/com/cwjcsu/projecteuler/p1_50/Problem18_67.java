package com.cwjcsu.projecteuler.p1_50;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class Problem18_67 {
	public static void main(String[] args) {
		try {
			do67();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	static void doTest() {
		int[][] data = new int[][] { { 3 }, { 7, 4 }, { 2, 4, 6 },
				{ 8, 5, 9, 3 } };
		Node[][] nodes = buildNodes(data);
		Node last = calcPath(nodes);
		print(last);
	}

	static void do18() {
		Node[][] nodes = buildNodes(dd);
		Node last = calcPath(nodes);
		print(last);
	}

	static void do67() throws Exception {
		File f = new File("triangle.txt");
		InputStreamReader reader = new InputStreamReader(new FileInputStream(f));
		BufferedReader r = new BufferedReader(reader);
		Node[][] nodes = new Node[100][];
		String red = null;
		int i = 0;
		while ((red = r.readLine()) != null) {
			String[] data = red.split(" ");
			nodes[i] = new Node[i + 1];
			for (int j = 0; j < data.length; j++) {
				nodes[i][j] = new Node(Integer.valueOf(data[j]));
			}
			i++;
		}
		Node last = calcPath(nodes);
		print(last);
	}

	static int[][] dd = new int[][] { { 75 }, { 95, 64 }, { 17, 47, 82 },
			{ 18, 35, 87, 10 }, { 20, 04, 82, 47, 65 },
			{ 19, 01, 23, 75, 03, 34 }, { 88, 02, 77, 73, 07, 63, 67 },
			{ 99, 65, 04, 28, 06, 16, 70, 92 },
			{ 41, 41, 26, 56, 83, 40, 80, 70, 33 },
			{ 41, 48, 72, 33, 47, 32, 37, 16, 94, 29 },
			{ 53, 71, 44, 65, 25, 43, 91, 52, 97, 51, 14 },
			{ 70, 11, 33, 28, 77, 73, 17, 78, 39, 68, 17, 57 },
			{ 91, 71, 52, 38, 17, 14, 91, 43, 58, 50, 27, 29, 48 },
			{ 63, 66, 04, 68, 89, 53, 67, 30, 73, 16, 69, 87, 40, 31 },
			{ 04, 62, 98, 27, 23, 9, 70, 98, 73, 93, 38, 53, 60, 04, 23 } };

	static Node[][] buildNodes(int[][] data) {
		Node[][] nodes = new Node[data.length][];
		for (int i = 0; i < data.length; i++) {
			nodes[i] = new Node[data[i].length];
			for (int j = 0; j < data[i].length; j++) {
				nodes[i][j] = new Node(data[i][j]);
			}
		}
		return nodes;
	}

	static void print(Node last) {
		LinkedList<Node> nodes = new LinkedList<Node>();
		System.out.println("SUM:" + last.sum + "=>");
		while (last != null) {
			nodes.addFirst(last);
			last = last.prev;
		}

		for (Node n : nodes) {
			System.out.print(+n.value + " ");
		}
	}

	// make sure triangle
	static Node calcPath(Node[][] nodes) {
		Node last = null;
		Node head = nodes[0][0];
		head.prev = null;
		head.sum = head.value;
		if (nodes.length >= 2) {
			nodes[1][0].link(head);
			nodes[1][1].link(head);
		}
		for (int i = 2; i < nodes.length; i++) {
			int n = nodes[i].length;
			// System.out.println(n);
			if (n != (i + 1)) {

				throw new RuntimeException("Row " + (i + 1) + " got " + n
						+ " nodes");
			}
			nodes[i][0].link(nodes[i - 1][0]);
			nodes[i][n - 1].link(nodes[i - 1][i - 1]);
			for (int j = 1; j < n - 1; j++) {
				Node thiz = nodes[i][j];
				Node node1 = nodes[i - 1][j - 1];
				Node node2 = nodes[i - 1][j];
				if (node1.sum < node2.sum) {
					thiz.link(node2);
				} else {
					thiz.link(node1);
				}
			}
		}
		int i = nodes.length - 1;
		last = nodes[i][0];
		for (int j = 1; j < nodes[i].length; j++) {
			Node n = nodes[i][j];
			if (last.sum < n.sum) {
				last = n;
			}
			System.out.print(n.sum + ",");
		}
		System.out.println();
		return last;
	}
}

class Node {
	Node prev;
	int value;
	int sum;

	public Node(Integer value) {
		this.value = value;
	}

	public void link(Node prev) {
		this.prev = prev;
		this.sum = prev.sum + value;
	}

	@Override
	public String toString() {
		return String.valueOf(value);
	}
}
