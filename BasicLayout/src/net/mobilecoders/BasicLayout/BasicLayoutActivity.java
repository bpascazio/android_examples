package net.mobilecoders.BasicLayout;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class BasicLayoutActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		final Button button1 = (Button) findViewById(R.id.button1);
		button1.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Context c = v.getContext();
				Intent i = new Intent(c, LinearLayoutActivity.class);
				c.startActivity(i);
			}
		});
		final Button button2 = (Button) findViewById(R.id.button2);
		button2.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Context c = v.getContext();
				Intent i = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse("geo:37.827500,-122.48"));
//				Intent i = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse("http://www.amazon.com"));
				c.startActivity(i);
			}
		});

    }
}