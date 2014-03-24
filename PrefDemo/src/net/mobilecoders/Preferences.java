package net.mobilecoders;

import android.app.Activity;
import android.content.SharedPreferences;

public class Preferences {
	
	public static final String PREFS_APP_STATE = "PrefDemoState";
	public static final String PREFS_NAME_AUTHENTICATED = "Authenticated";
	public static final String PREFS_NAME_USERNAME = "Username";
	public static final String PREFS_NAME_PASSWORD = "Password";

	static boolean authenticated(Activity a) {
		SharedPreferences settings = a.getSharedPreferences(PREFS_APP_STATE, 0);
		return settings.getBoolean(PREFS_NAME_AUTHENTICATED, false); //default set to false
	}

	static String username(Activity a) {
		SharedPreferences settings = a.getSharedPreferences(PREFS_APP_STATE, 0);
		return settings.getString(PREFS_NAME_USERNAME,"unknown");
	}
	
	static void setCredentials(Activity a, String username, String password) {
		SharedPreferences settings = a.getSharedPreferences(PREFS_APP_STATE, 0);
	    SharedPreferences.Editor editor = settings.edit();
	    editor.putBoolean(PREFS_NAME_AUTHENTICATED, true);
	    editor.putString(PREFS_NAME_USERNAME, username);
	    editor.putString(PREFS_NAME_PASSWORD, password);
	    editor.commit();
	}
	static void reset(Activity a) {
		SharedPreferences settings = a.getSharedPreferences(PREFS_APP_STATE, 0);
	    SharedPreferences.Editor editor = settings.edit();
	    editor.putBoolean(PREFS_NAME_AUTHENTICATED, false);
	    editor.commit();
	}
}
