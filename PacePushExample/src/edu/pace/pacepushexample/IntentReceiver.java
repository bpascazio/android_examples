package edu.pace.pacepushexample;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import edu.pace.pacepushexample.MainActivity;
import com.urbanairship.UAirship;
import com.urbanairship.push.PushManager;

public class IntentReceiver extends BroadcastReceiver {

	private static final String logTag = "Pace";

    @Override
    public void onReceive(Context context, Intent intent) {
        //Log.i(logTag, "Received intent: " + intent.toString());
        String action = intent.getAction();

        if (action.equals(PushManager.ACTION_PUSH_RECEIVED)) {

            int id = intent.getIntExtra(PushManager.EXTRA_NOTIFICATION_ID, 0);

            Log.i(logTag, "Received push notification. Alert: "
                    + intent.getStringExtra(PushManager.EXTRA_ALERT)
                    + " [NotificationID="+id+"]");

            logPushExtras(intent);

        } else if (action.equals(PushManager.ACTION_NOTIFICATION_OPENED)) {

            //Log.i(logTag, "User clicked notification. Message: " + intent.getStringExtra(PushManager.EXTRA_ALERT));

            String color = logPushExtras(intent);

            Intent launch = new Intent(Intent.ACTION_MAIN);
            if (color.equals("blue")) {
            	launch.setClass(UAirship.shared().getApplicationContext(), MainActivity.class);
            } else {
            	launch.setClass(UAirship.shared().getApplicationContext(), MainActivityGreen.class);
            }
            launch.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            UAirship.shared().getApplicationContext().startActivity(launch);

        } else if (action.equals(PushManager.ACTION_REGISTRATION_FINISHED)) {
            //Log.i(logTag, "Registration complete. APID:" + intent.getStringExtra(PushManager.EXTRA_APID)
                    //+ ". Valid: " + intent.getBooleanExtra(PushManager.EXTRA_REGISTRATION_VALID, false));
        }
    }

    /**
     * Log the values sent in the payload's "extra" dictionary.
     *
     * @param intent A PushManager.ACTION_NOTIFICATION_OPENED or ACTION_PUSH_RECEIVED intent.
     */
    private String logPushExtras(Intent intent) {
        Set<String> keys = intent.getExtras().keySet();
        String color = "blue";
        for (String key : keys) {

            //ignore standard extra keys (GCM + UA)
            List<String> ignoredKeys = (List<String>)Arrays.asList(
                    "collapse_key",//GCM collapse key
                    "from",//GCM sender
                    PushManager.EXTRA_NOTIFICATION_ID,//int id of generated notification (ACTION_PUSH_RECEIVED only)
                    PushManager.EXTRA_PUSH_ID,//internal UA push id
                    PushManager.EXTRA_ALERT);//ignore alert
            if (ignoredKeys.contains(key)) {
                continue;
            }
            if (key.equals("thecolor"))color=intent.getStringExtra(key);
            Log.i(logTag, "Push Notification Extra: ["+key+" : " + intent.getStringExtra(key) + "]");
        }
        return color;
    }
	
}


