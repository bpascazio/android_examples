package com.smaatlearn.module4lab2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class M4L2ActivityLoginInfo extends Activity {

	Activity act;
	boolean existingUser;
	boolean loginsuccess;
	String username;
	String usertypeValue;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_m4_l2_logininfo);

		act = this;
		username = "";
		
		usertypeValue = getIntent().getExtras().getString("usertype");

		Button lsButton = (Button) findViewById(R.id.lsbutton);

		if (usertypeValue.equals("existing")) {
			existingUser = true;
			lsButton.setText("LOGIN");
			loginsuccess=false;
		} else {
			existingUser = false;
			lsButton.setText("SIGNUP");
			loginsuccess=true;
		}

		lsButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				
				EditText uname = (EditText) findViewById(R.id.username);
				username = uname.getText().toString();
				if (username.equals("smaat")) {
					loginsuccess=true;
				}
				
				finishWithResult();
			}
		});
	}
	
	private void finishWithResult()
	{
	    Bundle conData = new Bundle();
	    conData.putString("username", username);
	    conData.putString("usertype", usertypeValue);
	    conData.putString("loginsuccess", ""+loginsuccess);
	    Intent intent = new Intent();
	    intent.putExtras(conData);
	    setResult(RESULT_OK, intent);

	    finish();
	}

}
