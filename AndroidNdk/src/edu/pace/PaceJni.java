package edu.pace;

import android.util.Log;

public class PaceJni {

	public native void initializeJni(String server);

	public native int calculateTaxes(String state, int amount, long profit);

	public void initialize(String server) {
		Log.d("PaceJni","Normal Java Code");
		initializeJni(server);
	}
}
