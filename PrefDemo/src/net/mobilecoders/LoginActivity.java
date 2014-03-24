package net.mobilecoders;

import net.mobilecoders.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity {

	Button loginButton;
	LoginActivity act;
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);  
		act = this;
		loginButton = (Button) findViewById(R.id.button1);
		loginButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				
				//make sure credentials are valid
				String username =  ((EditText) findViewById(R.id.editText1)).getText().toString();
				String password =  ((EditText) findViewById(R.id.editText2)).getText().toString();
				
				if (validate(username,password)) {
					Preferences.setCredentials(act, username, password);
					final Intent intent = new Intent();
					intent.setClass(act, MainActivity.class);
					startActivity(intent);
					finish();
				} else {
					CharSequence text = "Invalid username or password";
					Toast msg = Toast.makeText(act, text, Toast.LENGTH_LONG);
					msg.setGravity(Gravity.CENTER, msg.getXOffset() / 2,
					        msg.getYOffset() / 2);
					msg.show();
				}
				
			}
		});
	}
	
	public boolean validate(String username, String password) {
		if (password.equals("password")) return true;
		return false;
	}

}
