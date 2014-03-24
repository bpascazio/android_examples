package com.smaatlearn.module1lab1;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;

public class M1L1Activity extends Activity {

	private static final String TAG = "M1L2Activity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_m1_l1);
		Log.d(TAG, "called onCreate()");
	}

	@Override
	protected void onStart() {
		super.onStart();
		Log.d(TAG, "called onStart()");
	}

	@Override
	protected void onPause() {
		super.onPause();
		Log.d(TAG, "called onPause()");
	}

	@Override
	protected void onStop() {
		super.onStop();
		Log.d(TAG, "called onStop()");
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		Log.d(TAG, "called onDestroy()");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.m1_l1, menu);
		return true;
	}

}
