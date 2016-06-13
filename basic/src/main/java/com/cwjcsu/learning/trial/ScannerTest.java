package com.cwjcsu.learning.trial;
import java.util.Scanner;

/**
 * 
 * @author atlas
 * @date 2012-10-25
 */
public class ScannerTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		 Scanner s = new Scanner(System.in);
		 while(true){
			 int num =  s.nextInt();
			 String line = s.nextLine();
			 System.out.println(num+":"+line);
		 }
	}
	 
}
