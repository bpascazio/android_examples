package com.bytefly.example.browserexample;

import com.androidquery.AQuery;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.webkit.WebView;
import android.net.Uri;

public class BrowserExampleActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_browser_example);

		AQuery aq = new AQuery(this);

		aq.id(R.id.btnGo).clicked(this, "buttonGO");

		aq.id(R.id.editURL).text("http://www.apple.com");

	}

	public void buttonGO(View view) {

		AQuery aq = new AQuery(this);

		String url = (String) aq.id(R.id.editURL).getText().toString();

		boolean cbchecked = aq.id(R.id.checkUseWebView).isChecked();

		if (cbchecked) {

			boolean islocal = aq.id(R.id.checkUseLocal).isChecked();

			WebView wv = (WebView) findViewById(R.id.webView);

			if (islocal) {

				String mimeType = "text/html";
				String encoding = "UTF-8";
				String html = "<h1>this is a title</h1><body>pace android</body>";
				wv.loadDataWithBaseURL("", html, mimeType, encoding, "");
				
			} else {

				wv.loadUrl(url);

			}

		} else {
			Intent i = new Intent(android.content.Intent.ACTION_VIEW,
					Uri.parse(url));
			startActivity(i);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.browser_example, menu);
		return true;
	}

}
