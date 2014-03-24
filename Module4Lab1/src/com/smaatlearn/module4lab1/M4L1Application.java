package com.smaatlearn.module4lab1;

import android.app.Application;
import android.util.Log;

public class M4L1Application extends Application {

	private static final String TAG = "AppStateApplication";
	public int autocounter;
	@Override
	public void onCreate() {
		super.onCreate();
		Log.d(TAG, "in onCreate");
		autocounter = 0;
	}
}
