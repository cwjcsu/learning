/*$Id: $
 --------------------------------------
  Skybility
 ---------------------------------------
  Copyright By Skybility ,All right Reserved
 * author                     date    comment
 * chenweijun@skybility.com  2015年10月10日  Created
*/ 
package com.cwjcsu.projecteuler.p300_400;
/**
 * http://blog.dreamshire.com/project-euler-317-solution/
 * @author atlas
 *
 */
public class PE317 {

	public static void main(String[] args) {//1856532.8455
		double v =	20;
		double h = 100;
		double g = 9.81;
		double V = Math.PI*Math.pow((2*g*v*h+v*v*v),2)/(4*g*g*g);
		System.out.println(V);
	}

}
