/*
 * 
 * The OperatorGenerator Class is for generating operator randomly.
 * 
 * */

package com.example.funwithmath;

public class OperatorGenerator {
	
	private int operatorGen;
	
	public int operatorGenerator() {
		
		 operatorGen = Math.abs((int) (Math.random() * 2));
		 
		 return operatorGen;
		
	}

}
