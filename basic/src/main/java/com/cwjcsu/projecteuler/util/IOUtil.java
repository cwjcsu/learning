package com.cwjcsu.projecteuler.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class IOUtil {
	public static int[][] readDataMatrix(String file) throws Exception {
		File f = new File(file);
		InputStreamReader reader = new InputStreamReader(new FileInputStream(f));
		BufferedReader r = new BufferedReader(reader);
		String red = null;
		LinkedList<String> reds = new LinkedList<String>();
		while ((red = r.readLine()) != null) {
			reds.addLast(red);
		}
		int[][] ret = new int[reds.size()][];
		for (int i = 0; i < reds.size(); i++) {
			String thiz = reds.get(i);
			String[] data = thiz.split(" ");
			ret[i] = new int[data.length];
			for (int j = 0; j < data.length; j++) {
				ret[i][j] = Integer.valueOf(data[j]);
			}
		}
		return ret;
	}
	public static void main(String[] args) throws Exception {
		int[][] red = readDataMatrix("G.txt");
		Util.print(red);
	}
}
