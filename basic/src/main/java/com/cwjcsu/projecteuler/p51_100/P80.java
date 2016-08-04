package com.cwjcsu.projecteuler.p51_100;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class P80 {

	/**
	 * not ok
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		BigDecimal num = new BigDecimal("3");
		MathContext mc = new MathContext(2000, RoundingMode.HALF_DOWN);
		BigDecimal finalnum = new BigDecimal(Math.sqrt(num.doubleValue()), mc);
		System.out.println(finalnum);
		double d = 1.732050807568877193176604123436845839023590087890625732050807568877193176604123436845839023590087890625d;
		System.out
				.println(new BigDecimal(
						"1.73205080756887719317660412343684583902359008789062507568877193176604123436845839023590087890625"));
		for (int i = 1; i <= 100; i++) {
		}
	}

}
