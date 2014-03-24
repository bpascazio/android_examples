package com.smaatlearn.module2lab1;

import java.util.HashMap;
import java.util.Vector;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class M2L1Activity extends Activity {

	private static final String TAG = "M2L1Activity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_m2_l1);
		final Button buttonc = (Button) findViewById(R.id.calcbutton);
		buttonc.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				// Perform action on click
				Context context = getApplicationContext();
				CharSequence text = "Hello toast!";
				int duration = Toast.LENGTH_SHORT;

				Toast toast = Toast.makeText(context, text, duration);
				toast.show();

				AdvancedCalc ac = new AdvancedCalc();
				Log.d(TAG, "exp test =" + ac.exp(2, 8));
				Log.d(TAG, "power10 test =" + ac.power10(6));
			}
		});

		final Button buttonh = (Button) findViewById(R.id.hashmapbutton);
		buttonh.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {

				HashMap<String, String> hm = new HashMap<String, String>();

				hm.put("Apple", "Red");
				hm.put("Banana", "Yellow");
				hm.put("Mango", "Orange");

				String color = hm.get("Mango");

				// Perform action on click
				Context context = getApplicationContext();
				CharSequence text = "color is " + color;
				int duration = Toast.LENGTH_SHORT;

				Toast toast = Toast.makeText(context, text, duration);
				toast.show();
				
			}
		});

		final Button buttonv = (Button) findViewById(R.id.vectorbutton);
		buttonv.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {

				Vector<String> vct = new Vector<String>();

				vct.add("Rock");
				vct.add("Tree");
				vct.add("House");
				vct.add("Road");
				
				int i;
				for (i = 0; i < vct.size(); i++) {
					// Perform action on click
					Context context = getApplicationContext();
					CharSequence text = "physical object is "
							+ vct.elementAt(i);
					int duration = Toast.LENGTH_SHORT;

					Toast toast = Toast.makeText(context, text, duration);
					toast.show();
				}
				
				vct.removeElementAt(0);
				
				for (i = 0; i < vct.size(); i++) {
					// Perform action on click
					Context context = getApplicationContext();
					CharSequence text = "physical object is "
							+ vct.elementAt(i);
					int duration = Toast.LENGTH_SHORT;

					Toast toast = Toast.makeText(context, text, duration);
					toast.show();
				}
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.m2_l1, menu);
		return true;
	}

}
