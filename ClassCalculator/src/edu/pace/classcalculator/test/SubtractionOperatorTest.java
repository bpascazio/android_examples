package edu.pace.classcalculator.test;

import android.util.Log;
import edu.pace.classcalculator.SubtractionOperator;

public class SubtractionOperatorTest {
	public void doIt() {
		SubtractionOperator mo = new SubtractionOperator();
		mo.setA(10);
		mo.setB(15);
		mo.calculate();
		Log.d("math", "subtract the answer is "+mo.getResult());
	}

}
