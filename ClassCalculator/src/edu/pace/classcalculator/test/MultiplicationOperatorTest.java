package edu.pace.classcalculator.test;

import android.util.Log;
import edu.pace.classcalculator.AdditionOperator;
import edu.pace.classcalculator.MultiplicationOperator;

public class MultiplicationOperatorTest {

	public void doIt() {
		MultiplicationOperator mo = new MultiplicationOperator();
		mo.setA(10);
		mo.setB(15);
		mo.calculate();
		Log.d("math", "the answer is "+ mo.getResult());
	}	
}
