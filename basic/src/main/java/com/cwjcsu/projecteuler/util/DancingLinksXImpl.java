package com.cwjcsu.projecteuler.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

public class DancingLinksXImpl implements DancingLinksX {
	private static final Logger logger = Logger
			.getLogger(DancingLinksXImpl.class.getName());
	private Header root = null;
	private List solution = new ArrayList();
	private boolean shouldContinue = true;
	private int solutionCounter = 0;
	private int b; // number of blocks per row
	private int n; // number of columns/rows/blocks
	private int threeN;
	private int twoN;
	private int threeNsquare;
	private int fourNsquare;
	private int nSquare;
	private int numberOfNonZeros = 0;
	private int searchCount = 0;
	private int[][] puzzle;

	private void init() {
		n = b * b;
		nSquare = n * n;
		twoN = 2 * n;
		threeN = 3 * n;
		threeNsquare = 3 * nSquare;
		fourNsquare = 4 * nSquare;
	}

	private void search(int k) {
		if (searchCount % 1000000 == 0) {
			System.out.println("");
		}
		if (searchCount % 10000 == 0) {
			System.out.print(".");
		}
		searchCount++;
		// Abbruchbedingung
		if (root.right == root) {
			return;
		}
		Header c = chooseColumn();
		coverColumn(c);
		X r = c.down;
		while (r != c) {

			if (k < solution.size()) {
				solution.remove(k);
			}
			solution.add(k, r);

			X j = r.right;
			while (j != r) {
				coverColumn(j.header);
				j = j.right;
			}
			if (shouldContinue) {
				search(k + 1);
			}

			// are r and c realy overwritten here??
			X r2 = (X) solution.get(k);
			// c = r.header;
			X j2 = r2.left;
			while (j2 != r2) {
				uncoverColumn(j2.header);
				j2 = j2.left;
			}
			r = r.down;

			// here we can distinguis the different solutions
			if (k == nSquare - 1) {
				solutionCounter++;
				shouldContinue = handleSolution(createMatrixFromSolution(solution));
			}
		}
		uncoverColumn(c);
	}

	private int[][] createMatrixFromSolution(List solution) {
		int[][] result = new int[n][n];
		for (Iterator it = solution.iterator(); it.hasNext();) {
			int digit = -1;
			int cell = -1;
			X element = (X) it.next();
			X next = element;
			do {
				if (next.header.info.type == HeaderInfo.TYPE_ROW) {
					digit = next.header.info.digit;
				} else if (next.header.info.type == HeaderInfo.TYPE_CELL) {
					cell = next.header.info.position;
				}
				next = next.right;
			} while (element != next);
			int row = cell / n;
			int col = cell % n;
			result[row][col] = digit;
		}
		return result;
	}

	private String getMatrixAsString(int[][] matrix) {
		StringBuffer sb = new StringBuffer("\n");
		for (int row = 0; row < matrix.length; row++) {
			for (int col = 0; col < matrix[row].length; col++) {
				if (col > 0) {
					sb.append(",");
				}
				sb.append(matrix[row][col]);
			}
			sb.append("\n");
		}
		return sb.toString();
	}

	/**
	 * the default solution handler
	 */
	public boolean handleSolution(int[][] solution) {
		logger.info("Solution: " + solutionCounter);
		logger.info(getMatrixAsString(solution));
		sum+=solution[0][0];
		sum+=solution[0][1];
		sum+=solution[0][2];
		return true;
	}

	static int sum = 0;

	private Header chooseColumn() {
		// its mostly efficient to always choose the column with the smales size
		Header h = (Header) root.right;
		Header smalest = h;
		while (h.right != root) {
			h = (Header) h.right;
			if (h.size == 1) {
				smalest = h;
				break;
			} else if (h.size < smalest.size) {
				smalest = h;
			}
		}
		return smalest;
	}

	private void coverColumn(X column) {
		column.right.left = column.left;
		column.left.right = column.right;
		X i = column.down;
		while (i != column) {
			X j = i.right;
			while (j != i) {
				j.down.up = j.up;
				j.up.down = j.down;
				j.header.size--;
				j = j.right;
			}
			i = i.down;
		}
	}

	private void uncoverColumn(X column) {
		X i = column.up;
		while (i != column) {
			X j = i.left;
			while (j != i) {
				j.header.size++;
				j.down.up = j;
				j.up.down = j;
				j = j.left;
			}
			i = i.up;
		}
		column.right.left = column;
		column.left.right = column;
	}

	class X {
		X left;
		X right;
		X up;
		X down;
		Header header;
	}

	class Header extends X {
		int size = 0;
		HeaderInfo info;
	}

	class HeaderInfo {
		static final int TYPE_ROW = 0;
		static final int TYPE_COL = 1;
		static final int TYPE_BLOCK = 2;
		static final int TYPE_CELL = 3;

		int type = -1;
		int digit = -1;
		int position = -1;

		public String toString() {
			// create the header name
			StringBuffer name = new StringBuffer();
			if (type == TYPE_ROW) {
				name.append("Digit ");
				name.append(digit);
				name.append(" in row ");
			} else if (type == TYPE_COL) {
				name.append("Digit ");
				name.append(digit);
				name.append(" in column ");
			} else if (type == TYPE_BLOCK) {
				name.append("Digit ");
				name.append(digit);
				name.append(" in block ");
			} else if (type == TYPE_CELL) {
				name.append("Digit in cell ");
			}
			name.append(position + 1);
			return name.toString();
		}
	}

	class CoverCheck {
		int[] rows = new int[n];
		int[] columns = new int[n];
		int[] blocks = new int[n];
	}
}
