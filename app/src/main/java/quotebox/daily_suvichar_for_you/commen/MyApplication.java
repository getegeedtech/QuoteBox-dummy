package quotebox.daily_suvichar_for_you.commen;

import android.app.Application;

import com.google.android.gms.ads.MobileAds;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        MobileAds.initialize(this, initializationStatus -> {});
    }
}