package edu.pace.classcalculator.test;

import android.util.Log;
import edu.pace.classcalculator.AdditionOperator;

public class AdditionOperatorTest {

	public void doIt() {
		AdditionOperator mo = new AdditionOperator();
		mo.setA(10);
		mo.setB(15);
		mo.calculate();
		Log.d("math", "the answer is "+mo.getResult());
	}
}
