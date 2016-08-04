package com.cwjcsu.misc.puzzle;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/*$Id: $
 --------------------------------------
 Skybility
 ---------------------------------------
 Copyright By Skybility ,All right Reserved
 * author                     date    comment
 * chenweijun@skybility.com  2015年9月10日  Created
 */
/**
 * 结论：只要黑点的位置行列数之和为奇数，则无解，为偶数则有解
 * @author atlas
 *
 */
public class FiveXFive {

	public FiveXFive() {
	}

	/**
	 * 无解
	 * @param args
	 */
	public static void main0(String[] args) {
		caculate(5, 5, new Point(4, 3));
	}

	public static void main(String[] args) {
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				caculate(5, 5, new Point(i, j));
			}
		}
	}

	public static void caculate(int X, int Y, Point removedPoint) {
		for (int x = 0; x < X; x++) {
			for (int y = 0; y < Y; y++) {
				Point start = new Point(x, y);
				if (!start.equals(removedPoint)) {
					boolean ok = findPath(x, y, X, Y, removedPoint);
					if (ok) {
						System.out.println((removedPoint.x + removedPoint.y) + ":" + removedPoint);
					}
				}
			}
		}
	}

	private static boolean findPath(int i, int y, int X, int Y,
			Point removedPoint) {
		LinkedList<Point> path = new LinkedList<Point>();
		path.add(new Point(i, y));
		Set<Point> passPoints = new HashSet<Point>();
		passPoints.add(removedPoint);
		return findPath(path, X, Y, passPoints);
	}

	/**
	 * 
	 * @param path
	 * @param X
	 * @param Y
	 * @param removedPoint
	 * @param passPoints
	 * @return true 已经到终点了
	 */
	private static boolean findPath(LinkedList<Point> path, int X, int Y,
			Set<Point> passPoints) {
		Point c = path.getLast();
		List<Point> nextPoints = new ArrayList<Point>(4);
		if (c.x > 0) {
			Point p = new Point(c.x - 1, c.y);
			if (!passPoints.contains(p)) {
				nextPoints.add(p);
			}
		}
		if (c.x + 1 < X) {
			Point p = new Point(c.x + 1, c.y);
			if (!passPoints.contains(p)) {
				nextPoints.add(p);
			}
		}
		if (c.y > 0) {
			Point p = new Point(c.x, c.y - 1);
			if (!passPoints.contains(p)) {
				nextPoints.add(p);
			}
		}
		if (c.y + 1 < Y) {
			Point p = new Point(c.x, c.y + 1);
			if (!passPoints.contains(p)) {
				nextPoints.add(p);
			}
		}
		for (Point p : nextPoints) {
			if (!path.contains(p) && !passPoints.contains(p)) {
				path.addLast(p);
				findPath(path, X, Y, passPoints);
				if (path.size() == X * Y - 1) {
//					System.out.println(path);
					return true;
				}
				path.removeLast();
			}
		}
		return false;
	}
}
