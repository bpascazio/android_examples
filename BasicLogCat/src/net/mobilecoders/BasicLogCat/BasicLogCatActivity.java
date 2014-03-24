// Copyright 2010-2011 MobileCoders, Inc.

package net.mobilecoders.BasicLogCat;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class BasicLogCatActivity extends Activity {
    /* Called when the activity is first created. 
     * 
     * onCreate(Bundle) is where you initialize your activity. 
     * Most importantly, here you will usually call setContentView(int) 
     * with a layout resource defining your UI, and using findViewById(int) 
     * to retrieve the widgets in that UI that you need to interact 
     * with programmatically.
     **/
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Log.d("BasicLogCatActivity", "onCreate");
    }
    
    /*
     * Called after onCreate(Bundle) Ñ or after onRestart() when the activity 
     * had been stopped, but is now again being displayed to the user. 
     * It will be followed by onResume().
     */
    @Override
    public void onStart() {
        super.onStart();
        setContentView(R.layout.main);
        Log.d("BasicLogCatActivity", "onStart");
    }
    
    /*
     * Called when you are no longer visible to the user. You will next receive 
     * either onRestart(), onDestroy(), or nothing, depending on later user activity.
     * Note that this method may never be called, in low memory situations where 
     * the system does not have enough memory to keep your activity's process 
     * running after its onPause() method is called.
     */
    @Override
    public void onStop() {
        super.onStop();
        setContentView(R.layout.main);
        Log.d("BasicLogCatActivity", "onStop");
    }  
    
    /* Called when the activity is going in the background but not yet been killed. 
     * 
     * When activity B is launched in front of activity A, this callback will be 
     * invoked on A. B will not be created until A's onPause() returns, so be 
     * sure to not do anything lengthy here.  This callback is mostly used for 
     * saving any persistent state the activity is editing, to present a 
     * "edit in place" model to the user and making sure nothing is lost if there 
     * are not enough resources to start the new activity without first 
     * killing this one. This is also a good place to do things like stop 
     * animations and other things that consume a noticeable mount of CPU 
     * in order to make the switch to the next activity as fast as possible, 
     * or to close resources that are exclusive access such as the camera.
     */
    @Override
    public void onPause() {
        super.onPause();
        Log.d("BasicLogCatActivity", "onPause");
    }

    /*
     * Called after onRestoreInstanceState(Bundle), onRestart(), or onPause(), for 
     * your activity to start interacting with the user. This is a good place to 
     * begin animations, open exclusive-access devices (such as the camera), etc.
     * Keep in mind that onResume is not the best indicator that your activity 
     * is visible to the user; a system window such as the keyguard may be in 
     * front. Use onWindowFocusChanged(boolean) to know for certain that your 
     * activity is visible to the user (for example, to resume a game).
     */
    @Override
    public void onResume() {
        super.onResume();
        Log.d("BasicLogCatActivity", "onResume");
    }
    
    /*
     * Perform any final cleanup before an activity is destroyed. This can happen 
     * either because the activity is finishing (someone called finish() on it, 
     * or because the system is temporarily destroying this instance of the 
     * activity to save space. You can distinguish between these two 
     * scenarios with the isFinishing() method. 
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("BasicLogCatActivity", "onDestroy");
    }
}