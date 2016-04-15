/*
 * 
 * The OperatorGenerator Class is for generating operator randomly.
 * 
 * */

package com.example.funwithmath;

public class OperatorGenerator {
	
	private int operatorGen;
	private int numOfOperator;
	
	
	public OperatorGenerator(int i) {
		// TODO Auto-generated constructor stub
		numOfOperator = i;
	}


	public int operatorGenerator() {
		if(numOfOperator==1){
			
			operatorGen = Math.abs((int) (Math.random() * 2));
			
		}else{
			
			operatorGen = Math.abs((int) (Math.random() * 4));
			
		}
		 
		 return operatorGen;
		
	}

}
