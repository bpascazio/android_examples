package edu.pace.pacepushexample;

import android.app.Application;

import com.urbanairship.AirshipConfigOptions;
import com.urbanairship.UAirship;
import com.urbanairship.push.PushManager;
import edu.pace.pacepushexample.IntentReceiver;

public class MyApplication extends Application {

        @Override
        public void onCreate() {

                AirshipConfigOptions options = AirshipConfigOptions.loadDefaultOptions(this);
                options.developmentAppKey = "Gt7yuZTKRxyn3fHdtE98Dw";
                options.developmentAppSecret = "-3Z0sDOBSN6mod14KRPUuQ";
                options.productionAppKey = "Gt7yuZTKRxyn3fHdtE98Dw";
                options.productionAppSecret = "-3Z0sDOBSN6mod14KRPUuQ";
                options.gcmSender = "993061896878";
                options.inProduction = false;
                options.analyticsEnabled = false;

                UAirship.takeOff(this, options);
                PushManager.enablePush();
                PushManager.shared().setIntentReceiver(IntentReceiver.class);
        }
}



