package edu.pace.app.mysocialnetwork;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class Login extends Activity {
	
	Activity act = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		act = this;

		Button loginButton = (Button) findViewById(R.id.login_button_login);
		loginButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				
				
				EditText uname = (EditText) findViewById(R.id.edittext_username);
				String username = uname.getText().toString();
				if (username.equals("bob")) {
					final Intent intent = new Intent();
					intent.setClass(act, Wall.class);
					startActivity(intent);
				}
				finish();		
			}
		});
		
	}
	
}
