package net.mobilecoders;

import net.mobilecoders.R;
import android.app.Activity;
//import android.content.Intent;
import android.os.Bundle;
//import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
//import android.widget.EditText;
import android.widget.TextView;
//import android.widget.Toast;

public class MainActivity extends Activity {

	MainActivity act;
	Button resetButton;
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		TextView textinfo = (TextView) findViewById(R.id.textView3);
		textinfo.setText("Welcome in " + Preferences.username(this));
		
		act = this;
		
		resetButton = (Button) findViewById(R.id.button1);
		resetButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Preferences.reset(act);
				finish();
			}
		});
	}
}
