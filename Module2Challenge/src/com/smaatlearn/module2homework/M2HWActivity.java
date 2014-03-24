package com.smaatlearn.module2homework;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class M2HWActivity extends Activity {

	static final String TAG = "M2HWActivity";
	int autocounter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_m2_hw);

		Button startButton = (Button) findViewById(R.id.startbutton);
		startButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				autocounter = 10;
				new Thread(new Runnable() {
					public void run() {
						while (autocounter > 0) {
							EditText textinfo = (EditText) findViewById(R.id.counterfield);

							UpdateText ut = new UpdateText(autocounter, textinfo);
							runOnUiThread(ut);
							Log.d(TAG, "autocounter is " + autocounter);		
							autocounter = autocounter - 1;
							try {
								Thread.sleep(250);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}
				}).start();
			} // onCLick
		}); // setOnClickListener
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.m2_hw, menu);
		return true;
	}

}
