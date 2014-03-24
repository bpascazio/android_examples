package edu.pace.pacedatabaseexample;

import edu.pace.pacedatabaseexample.model.Department;
import edu.pace.pacedatabaseexample.util.DatabaseHandler;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		DatabaseHandler db = new DatabaseHandler(this);
		
		Department d = new Department();
		d._dept_name = "IT";
		db.addDepartment(d);
		d._dept_name = "SALES";
		db.addDepartment(d);
		d._dept_name = "R&D";
		db.addDepartment(d);
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
