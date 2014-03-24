package com.smaatlearn.module4lab2;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class M4L2Activity extends Activity {

	private static final String TAG = "M4L2Activity";
	private static final int RESULT_CODE = 1;
	Activity act;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_m4_l2);

		act = this;

		Button loginButton = (Button) findViewById(R.id.loginbutton);
		loginButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				final Intent intent = new Intent();
				Bundle mBundle = new Bundle();
				mBundle.putString("usertype", "existing");
				intent.putExtras(mBundle);
				intent.setClass(act, M4L2ActivityLoginInfo.class);
				startActivityForResult(intent, RESULT_CODE);
			}
		});
		Button signupButton = (Button) findViewById(R.id.signupbutton);
		signupButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				final Intent intent = new Intent();
				Bundle mBundle = new Bundle();
				mBundle.putString("usertype", "new");
				intent.putExtras(mBundle);
				intent.setClass(act, M4L2ActivityLoginInfo.class);
				startActivityForResult(intent, RESULT_CODE);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.m4_l2, menu);
		return true;
	}

	/* Called when the second activity's finished */
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		switch (requestCode) {
		case RESULT_CODE:
			if (resultCode == RESULT_OK) {
				Bundle res = data.getExtras();
				String uname = res.getString("username");
				String success = res.getString("loginsuccess");
				String usertype = res.getString("usertype");
				
				TextView welcome = (TextView)findViewById(R.id.welcome);
				
				if (usertype.equals("existing")) {
					if (success.equals("true")) {
						welcome.setText("Logged In " + uname);
					} else {
						welcome.setText("Logged Failed");
					}
				} else {
					welcome.setText("Signup completed");
					
					String url = "http://www.smaatlearn.com";
					Intent i = new Intent(Intent.ACTION_VIEW);
					i.setData(Uri.parse(url));
					startActivity(i);
				}
				
			}
			break;
		}
	}
}
