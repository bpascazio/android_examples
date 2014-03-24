package com.smaatlearn.module2lab1;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class M2L1Activity extends Activity {

	private static final String TAG = "M2L1Activity";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_m2_l1);
		final Button button = (Button) findViewById(R.id.button1);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
            	Context context = getApplicationContext();
            	CharSequence text = "Hello toast!";
            	int duration = Toast.LENGTH_SHORT;

            	Toast toast = Toast.makeText(context, text, duration);
            	toast.show();
            	
            	AdvancedCalc ac = new AdvancedCalc();
            	Log.d(TAG, "exp test ="+ac.exp(2,8));
            	Log.d(TAG, "power10 test ="+ac.power10(6));
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
