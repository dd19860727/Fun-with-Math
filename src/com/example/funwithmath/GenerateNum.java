/*
 * 
 * The GenerateNum Class is for generating factor numbers.
 * 
 * */
package com.example.funwithmath;

import android.R.array;

public class GenerateNum {
	
	private int factor1;
	private int factor2;
	private int operatorGen;
	private int numOfArray;
	private int [] returnArray; 
	
	public int[] generateNum(int f1, int f2, int oG) {
		// TODO Auto-generated method stub
		
		numOfArray=2;
		factor1 = f1;
		factor2 = f2;
		operatorGen =oG;
		returnArray = new int[numOfArray];
		
		factor1 = Math.abs((int) (Math.random() * 100));
		factor2 = Math.abs((int) (Math.random() * 100));

		if(operatorGen==1){
			
			if (factor1 < factor2) {

				factor1 = factor2;
				factor2 = Math.abs((int) (Math.random() * factor1));

			}
			
		}
		
		returnArray[0]=factor1;
		returnArray[1]=factor2;
		
		return returnArray;

	}

}
