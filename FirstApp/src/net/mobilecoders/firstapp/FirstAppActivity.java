// Copyright 2010-2011 MobileCoders, Inc.

package net.mobilecoders.firstapp;

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
