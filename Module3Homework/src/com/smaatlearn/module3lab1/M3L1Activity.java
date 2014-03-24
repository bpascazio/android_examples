package com.smaatlearn.module3lab1;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class M3L1Activity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		setContentView(R.layout.activity_m3_l1);
		setContentView(R.layout.activity_m3_l1_second_layout);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.m3_l1, menu);
		return true;
	}

}
