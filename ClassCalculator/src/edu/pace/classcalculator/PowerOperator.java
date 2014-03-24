package edu.pace.classcalculator;

public class PowerOperator extends MathOperator {
	public void calculate() {
		double resultDouble = java.lang.Math.pow(a, b);
		
		// TO-DO: Learn how to throw exceptions.
		if (resultDouble > 2147483647) {
			result = 0;
		} else {
			result = (int) resultDouble;
		}
	}
}
