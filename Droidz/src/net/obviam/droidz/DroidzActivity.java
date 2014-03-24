package net.obviam.droidz;


import com.google.ads.AdRequest;
import com.google.ads.AdSize;
import com.google.ads.AdView;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.view.View;
import android.widget.LinearLayout;

public class DroidzActivity extends Activity {
    /** Called when the activity is first created. */
	
	private static final String TAG = DroidzActivity.class.getSimpleName();
	
	private AdView adView;
	private MainGamePanel mgpView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // requesting to turn the title OFF
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // making it full screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        
        
        setContentView(R.layout.main);
		LinearLayout layout = (LinearLayout)findViewById(R.id.mainLayout);

		adView = new AdView(this, AdSize.BANNER, "a14f170abc972e5");  //bytefly
		layout.addView(adView);
		adView.loadAd(new AdRequest());      

		mgpView = new MainGamePanel(this);
		layout.addView(mgpView);
		
    }

	@Override
	protected void onDestroy() {
		Log.d(TAG, "Destroying...");
		super.onDestroy();
	}

	@Override
	protected void onStop() {
		Log.d(TAG, "Stopping...");
		super.onStop();
	}
    
    
}