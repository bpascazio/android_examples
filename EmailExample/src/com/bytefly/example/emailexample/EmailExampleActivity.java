package com.bytefly.example.emailexample;

import com.androidquery.AQuery;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class EmailExampleActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_email_example);

		AQuery aq = new AQuery(this);

		aq.id(R.id.btnSendEmail).clicked(this, "buttonClicked");

	}

	public void buttonClicked(View view) {

		Intent emailIntent = new Intent(Intent.ACTION_SEND);
		
		emailIntent.setData(Uri.parse("mailto:"));
		String[] to = {"bob@bytefly.com"};
		String[] cc = {"pace-winter-2013@bytefly.com"};
		emailIntent.putExtra(Intent.EXTRA_EMAIL, to);
		emailIntent.putExtra(Intent.EXTRA_CC, cc);
		emailIntent.putExtra(Intent.EXTRA_SUBJECT, "some subject");
		emailIntent.putExtra(Intent.EXTRA_TEXT, "here is a message");
		emailIntent.setType("message/rfc822");
		startActivity(Intent.createChooser(emailIntent, "Email"));
		

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.email_example, menu);
		return true;
	}

}
