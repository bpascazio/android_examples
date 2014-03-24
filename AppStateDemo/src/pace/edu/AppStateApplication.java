package pace.edu;

import android.app.Application;
import android.util.Log;

public class AppStateApplication extends Application {

	private static final String TAG = "AppStateApplication";
	public int autocounter;
	@Override
	public void onCreate() {
		super.onCreate();
		Log.d(TAG, "in onCreate");
		autocounter = 0;
	}
}
