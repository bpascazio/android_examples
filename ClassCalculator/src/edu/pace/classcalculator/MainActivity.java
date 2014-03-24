package edu.pace.classcalculator;

import java.util.Timer;
import java.util.TimerTask;

import edu.pace.classcalculator.test.AdditionOperatorTest;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	
		MyTimerTask myTask = new MyTimerTask();
		Timer myTimer = new Timer();
		myTimer.schedule(myTask, 3000, 1500);

	}
	class MyTimerTask extends TimerTask {
		public void run() {
			AdditionOperator ao = new AdditionOperator();
			SubtractionOperator so = new SubtractionOperator();
			PowerOperator po = new PowerOperator();
			DivisionOperator dop = new DivisionOperator();
			MultiplicationOperator mo = new MultiplicationOperator();
			ao.setA(10);
			ao.setB(15);
			ao.calculate();
			so.setA(ao.getResult());
			so.setB(3);
			so.calculate();
			po.setA(so.getResult());
			po.setB(3);
			po.calculate();
			Log.d("math", "po the answer is " + po.getResult());
			mo.setA(po.getResult());
			mo.setB(3);
			mo.calculate();
			Log.d("math", "mo the answer is " + mo.getResult());
			dop.setA(mo.getResult());
			dop.setB(2);
			dop.calculate();
			Log.d("math", "dop the answer is " + dop.getResult());
		}
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
