package com.smaatlearn.example.firstapplication;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class FirstAppActivity extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		Log.d("MobileCodersTag", "onCreate()");
	}
}
