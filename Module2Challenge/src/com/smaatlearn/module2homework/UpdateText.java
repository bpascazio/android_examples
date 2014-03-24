package com.smaatlearn.module2homework;

import android.view.View;
import android.widget.EditText;

public class UpdateText implements Runnable {

	int counter = 0;
	View view = null;
	public UpdateText(int c, View v) {
		counter = c;
		view = v;
	}
	
	@Override
	public void run() {

		EditText textinfo = (EditText)view;
		textinfo.setText("" + counter);
	}
}
