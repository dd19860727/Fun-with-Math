/*
 * 
 * The FactToString Class is for converting Integer fact number to String type.
 * 
 * */

package com.example.funwithmath.util;

public class FactToString {

	private int factor1;
	private int factor2;
	private int factor3;

	private String sfactor1;
	private String sfactor2;
	private String sfactor3;

	private int numOfArray;
	private String[] returnArray;

	public String[] factNumToStr(int f1, int f2) {
		// TODO Auto-generated method stub

		factor1 = f1;
		factor2 = f2;

		numOfArray = 2;
		returnArray = new String[numOfArray];

		if (factor1 < 10 && factor2 >= 10) {
			sfactor1 = "0" + new Integer(factor1).toString();
			sfactor2 = new Integer(factor2).toString();
		} else if (factor1 >= 10 && factor2 < 10) {
			sfactor1 = new Integer(factor1).toString();
			sfactor2 = "0" + new Integer(factor2).toString();

		} else if (factor1 < 10 && factor2 < 10) {
			sfactor1 = "0" + new Integer(factor1).toString();
			sfactor2 = "0" + new Integer(factor2).toString();
		} else {
			sfactor1 = new Integer(factor1).toString();
			sfactor2 = new Integer(factor2).toString();
		}

		returnArray[0] = sfactor1;
		returnArray[1] = sfactor2;

		return returnArray;

	}

	public String[] factNumToStrMix(int f1, int f2, int f3) {
		// TODO Auto-generated method stub
		
		factor1 = f1;
		factor2 = f2;
		factor3 = f3;

		numOfArray = 3;
		returnArray = new String[numOfArray];
		
		if (factor1 < 10 && factor2 < 10 && factor3 < 10) {
			sfactor1 = "0" + new Integer(factor1).toString();
			sfactor2 = "0" + new Integer(factor2).toString();
			sfactor3 = "0" + new Integer(factor3).toString();
		} else if (factor1 < 10 && factor2 < 10 && factor3 >= 10) {
			sfactor1 = "0" + new Integer(factor1).toString();
			sfactor2 = "0" + new Integer(factor2).toString();
			sfactor3 = new Integer(factor3).toString();

		} else if (factor1 < 10 && factor2 >= 10 && factor3 >= 10) {
			sfactor1 = "0" + new Integer(factor1).toString();
			sfactor2 = new Integer(factor2).toString();
			sfactor3 = new Integer(factor3).toString();
		} else if (factor1 < 10 && factor2 >= 10 && factor3 < 10) {
			sfactor1 = "0" + new Integer(factor1).toString();
			sfactor2 = new Integer(factor2).toString();
			sfactor3 = "0" + new Integer(factor3).toString();
		} else if (factor1 < 10 && factor2 >= 10 && factor3 >= 10) {
			sfactor1 = "0" + new Integer(factor1).toString();
			sfactor2 = new Integer(factor2).toString();
			sfactor3 = new Integer(factor3).toString();
		} else if (factor1 >= 10 && factor2 < 10 && factor3 < 10) {
			sfactor1 = new Integer(factor1).toString();
			sfactor2 = "0" + new Integer(factor2).toString();
			sfactor3 = "0" + new Integer(factor3).toString();
		} else if (factor1 >= 10 && factor2 >= 10 && factor3 < 10) {
			sfactor1 = new Integer(factor1).toString();
			sfactor2 = new Integer(factor2).toString();
			sfactor3 = "0" + new Integer(factor3).toString();
		} else if (factor1 >= 10 && factor2 < 10 && factor3 >= 10) {
			sfactor1 = new Integer(factor1).toString();
			sfactor2 = "0" + new Integer(factor2).toString();
			sfactor3 = new Integer(factor3).toString();
		} else {
			sfactor1 = new Integer(factor1).toString();
			sfactor2 = new Integer(factor2).toString();
			sfactor3 = new Integer(factor3).toString();
		}
		
		returnArray[0] = sfactor1;
		returnArray[1] = sfactor2;
		returnArray[2] = sfactor3;

		return returnArray;
	}

}
