package com.cwjcsu.thirdparty;

/**
 * 
 * @author atlas
 * @date 2013-12-15
 */
public class TestFileName {

	static String[] keys={"sun.jnu.encoding","file.encoding"};
	public static void main(String[] args) {
		for(String key:keys){
			System.out.println(key+" : "+System.getProperty(key));
		}
	}
}
