package com.cwjcsu.learning.trial;
import java.io.File;
import java.io.IOException;

/**
 * 
 * @author atlas
 * @date 2012-10-24
 */
public class FileName_charset {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		String source = "/home/atlas/326½_6";
	    String dest = "/home/atlas/testsss";
	    System.out.println(" - Moving " + source + " to " + dest);
	    File sourceFile = new File(source);
	    System.out.println(System.getProperty("file.encoding"));
	    System.out.println(System.getProperty("file.encoding"));
	    System.out.println(sourceFile.exists());
	    if(!sourceFile.exists()){
	    	System.out.println("created:"+sourceFile.createNewFile());
	    }
	    File destinationFile = new File(dest);
	    if (!sourceFile.renameTo(destinationFile)) {
	        System.out.println("--- could not perform rename -------");
	    }
	    System.out.println("Finished moving");
	}
	 
}
