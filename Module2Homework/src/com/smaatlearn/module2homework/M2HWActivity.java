package com.smaatlearn.module2homework;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class M2HWActivity extends Activity {

	static final String TAG = "M2HWActivity";
	int autocounter;

	Activity act = null;

	Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			// get the bundle and extract data by key
			Bundle b = msg.getData();
			String key = b.getString("My Key");
			TextView tv = (TextView) act.findViewById(R.id.textView1);
			tv.setText("done " + key);
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_m2_hw);
		act = this; // //////
		Button startButton = (Button) findViewById(R.id.startbutton);
		startButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				autocounter = 10;
				new Thread(new Runnable() {
					public void run() {
						while (autocounter > 0) {

							if (autocounter > 1) {
								act.runOnUiThread(new Runnable() {
									public void run() {
										TextView tv = (TextView) act
												.findViewById(R.id.textView1);
										tv.setText("" + autocounter);
									} // run
								});
							} else {
								Message msg = new Message();
								Bundle b = new Bundle();
								b.putString(
										"My Key",
										"My Value: "
												+ String.valueOf(autocounter));
								msg.setData(b);
								handler.sendMessage(msg);
							}

							// //////

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
