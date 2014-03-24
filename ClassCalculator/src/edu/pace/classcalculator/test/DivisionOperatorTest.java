package edu.pace.classcalculator.test;

import android.util.Log;
import edu.pace.classcalculator.AdditionOperator;
import edu.pace.classcalculator.DivisionOperator;

public class DivisionOperatorTest {
	public void doIt() {
		DivisionOperator mo = new DivisionOperator();
		mo.setA(30);
		mo.setB(15);
		mo.calculate();
		Log.d("math", "the answer is "+mo.getResult());
	}

}
