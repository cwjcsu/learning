package com.cwjcsu.learning.trial;
import java.lang.management.ManagementFactory;

/**
 * 
 * @author atlas
 * @date 2012-12-27
 */
public class SystemPropertiesPrinter {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		 System.out.println(ManagementFactory.getRuntimeMXBean().getName());
		 System.out.println(System.getProperty("sun.java.launcher.pid"));
		 
		 for(Object key:System.getProperties().keySet()){
			 System.out.println(key+"=>"+System.getProperty((String)key));
		 }
		 
	}
	 
}
