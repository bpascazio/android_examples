package edu.pace.classcalculator.test;

import android.util.Log;
import edu.pace.classcalculator.PowerOperator;

// TO-DO: Should make an OperatorTest superclass
public class PowerOperatorTest {
	public void doIt() {
		PowerOperator po = new PowerOperator();
		
		po.setA(2);
		po.setB(3);
		po.calculate();
		
		int result = po.getResult();
		
		if (result == 8) {
			Log.d("math", "Success: 2 ^ 3 is " + result);
		} else {
			Log.e("math", "Failure: 2 ^ 3 is " + result);
		}
	}
}
