package edu.pace;

import android.app.Activity;
import android.os.Bundle;

public class AndroidNdkActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        PaceJni p = new PaceJni();
        p.initialize("test mobilecoders.net");
    }
}