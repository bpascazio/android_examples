package com.smaatlearn.module4lab1;

import com.smaatlearn.module4lab1.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class M4L1Activity extends Activity {

	Button nextButton;
	M4L1Activity act;
	Thread thr;
	boolean threadrun;

	@Override
	public void onStop() {
		super.onStop();
		threadrun = false;
	}

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		act = this;

		nextButton = (Button) findViewById(R.id.button1);
		nextButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				final Intent intent = new Intent();
				intent.setClass(act, M4L1ActivityTwo.class);
				startActivity(intent);
			}
		});

		thr = null;
	}

	@Override
	public void onStart() {

		super.onStart();
		threadrun = true;
		thr = new Thread(new Runnable() {
			public void run() {
				while (threadrun) {

					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					runOnUiThread(new Runnable() {
						public void run() {

							M4L1Application AC = (M4L1Application) getApplicationContext();

							EditText textinfo = (EditText) findViewById(R.id.editText1);
							textinfo.setText("" + AC.autocounter);
							AC.autocounter = AC.autocounter + 1;
							Log.d("", "FIRST " + AC.autocounter);

						}
					});
				}
			}
		});

		thr.start();
	}
}