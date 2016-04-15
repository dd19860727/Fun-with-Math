/*
 * 
 * The GenerateNum Class is for generating factor numbers.
 * 
 * */
package com.example.funwithmath;

import android.R.array;

public class GenerateNum {
	
	private int numOfGen;
	private int factor1;
	private int factor2;
	private int factor3;
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

	public int[] generateNumMix(int f1, int f2, int f3, int oG) {
		// TODO Auto-generated method stub
		
		numOfArray=3;
		factor1 = f1;
		factor2 = f2;
		factor3 = f3;
		operatorGen =oG;
		returnArray = new int[numOfArray];
		
		factor1 = Math.abs((int) (Math.random() * 100));
		factor2 = Math.abs((int) (Math.random() * 100));
		factor3 = Math.abs((int) (Math.random() * 100));
		
		if (operatorGen == 1 || operatorGen == 3) {

			generateDivNum();

		}

		if (operatorGen == 2) {

			Boolean i = true;

			while (i == true) {

				factor2 = Math.abs((int) (Math.random() * 100));
				factor3 = Math.abs((int) (Math.random() * 100));

				int factorX = factor2 * factor3;

				if (factorX < 100) {

					int m = 100 - factorX;

					factor1 = factorX + Math.abs((int) (Math.random() * m));
					;

					i = false;

					break;
				}

			}

		}
		
		if (operatorGen == 3) {

			Boolean k = true;

			while (k == true) {

				factor1 = Math.abs((int) (Math.random() * 100));

				int factorX1 = factor2 / factor3;

				if (factor1 > factorX1) {

					k = false;

					break;
				}

			}

		}
		
		returnArray[0]=factor1;
		returnArray[1]=factor2;
		returnArray[2]=factor3;
	
	
	
	
	return returnArray;
	}
	
	private void generateDivNum() {
		// TODO Auto-generated method stub

		Boolean i = true;

		while (i == true) {

			int multiNum = Math.abs((int) (Math.random() * 100));

			factor2 = factor3 * multiNum;

			if (factor2 < 100 && factor2 != 0 && factor3 != 0) {

				i = false;

				break;
			}

		}

	}

}
