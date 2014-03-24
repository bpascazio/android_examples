package net.mobilecoders;

import net.mobilecoders.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.MotionEvent;
import android.view.View;

public class SpashActivity extends Activity {

	private final static int STOPSPLASH = 1;
	View splash;
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);
		Message msg = new Message();
		msg.what = STOPSPLASH;
		splashHandler.sendMessageDelayed(msg, 5000); // ms
	}

	@Override
	public void onDestroy() {
		splashHandler = null;
		System.gc();
		super.onDestroy();
	}
	
	private Handler splashHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
				case STOPSPLASH:
					checkLogin();					
					break;
			}
			super.handleMessage(msg);
		}
	};

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getAction()) {
			case MotionEvent.ACTION_UP:
				checkLogin();
		}
		return true;
	}

	private void checkLogin() {
		
		boolean auth = Preferences.authenticated(this);
		if (auth) {
		    final Intent intent = new Intent();
		    intent.setClass(this, MainActivity.class);
		    startActivity(intent);			
		} else {
		    final Intent intent = new Intent();
		    intent.setClass(this, LoginActivity.class);
		    startActivity(intent);			
		}
		finish();
    }
}