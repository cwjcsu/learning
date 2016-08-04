package com.cwjcsu.projecteuler.p51_100;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.cwjcsu.projecteuler.util.IntPair;
import com.cwjcsu.projecteuler.util.Tick;
import com.cwjcsu.projecteuler.util.Util;

/**
 * A*算法
 * @author atlas
 * @date 2013-10-21
 */
public class PE83 {

	static int[][] G;
	static int N;
	static Map<IntPair,Point> points ;
	static Point start;
	static Point end;
	static List<Point> openList;
	static Set<IntPair> closedList;
	 
	public static void main(String[] args) throws IOException {
		G = Util.readIntArray(new File("matrix83.txt"), ",");
		Tick t = new Tick();
		N = G.length;
		points = new HashMap<IntPair,Point>();
		for(int i=0;i<N;i++){
			for(int j=0;j<N;j++){
				IntPair index = new IntPair(i,j);
				Point p = new Point(index);
				points.put(index, p);
				p.H = (int)(Math.sqrt((N-i)*(N-i)+(N-j)*(N-j))*10);
			}
		}
		start = points.get(new IntPair(0,0));
		start.F = G[0][0];
		
		end = points.get(new IntPair(N-1,N-1));
		openList = new ArrayList<Point>();
		openList.add(start);
		closedList = new HashSet<IntPair>();
		caculate();
		System.out.println("Time used "+t.elapsedTime());
	}
	
	
	
	private static void caculate() {
		if(openList.isEmpty()){// open list is empty,no path found
			System.out.println("No Path found");
			return ;
		}
		//a) Look for the lowest F cost square on the open list. We refer to this as the current square.
		Point current =  openList.remove(0);
		if(current==end){
			System.out.println("Found best path:"+current.F);
			return;
		}
		//b) Switch it to the closed list.
		closedList.add(current.index);
		//c) For each of the 4 squares adjacent to this current square …
		Set<Point> arounds = getAroundPoint(current);
		for(Point p:arounds){
			//（1）If it is not walkable or if it is on the closed list, ignore it.
			if(closedList.contains(p.index)){
				continue;
			}
			//（2）If it isn't on the open list, add it to the open list. Make the current square the parent of this square. Record the F, G, and H costs of the square.
			if(!openList.contains(p)){
				openList.add(p);
				p.parent = current;
				p.F = current.F+getScore(p);
			}else{
				//If it is on the open list already, check to see if this path to that square is better, using G cost as the measure. A lower G cost means that this is a better path. If so, change the parent of the square to the current square, and recalculate the G and F scores of the square. If you are keeping your open list sorted by F score, you may need to resort the list to account for the change.
				int newValue = current.F+getScore(p);
				if(newValue<p.F){
					//a better path
					p.F = newValue;
					p.parent = current;
				}
			}
		}
		//sort to speed up
		Collections.sort(openList);
		caculate();
	}
	
	private static int getScore(Point point){
		int i=point.index.getV1();
		int j = point.index.getV2();
		return G[i][j];
	}
	
	static Set<Point> aroundTemp = new HashSet<Point>(4);
	
	private static Set<Point> getAroundPoint(Point point){
		int i=point.index.getV1();
		int j = point.index.getV2();
		aroundTemp.clear();
		if(i>0){
			aroundTemp.add(points.get(new IntPair(i-1, j)));
		}
		if(j>0){
			aroundTemp.add(points.get(new IntPair(i, j-1)));
		}
		
		if(i<N-1){
			aroundTemp.add(points.get(new IntPair(i+1, j)));
		}
		if(j<N-1){
			aroundTemp.add(points.get(new IntPair(i, j+1)));
		}
		return aroundTemp;
	}

	static class Point implements Comparable<Point>{
		//到当前节点最短路径的上一个节点
		Point parent;
		//当前节点的索引
		IntPair index;
		// F = G+H, where G is G[i][j]
		int F;
		//猜测的值
		int H;
		public Point(IntPair index) {
			super();
			this.index = index;
		}
		
		@Override
		public int compareTo(Point o) {
			return (F< o.F?-1:(F==o.F?0:1));
		}
		
		@Override
		public String toString() {
			return "("+index.getV1()+","+index.getV2()+"):"+F;
		}
	}
	
}
