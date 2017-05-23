package hu.ait.ourhouseroommateapp;

import android.app.Application;
import android.content.Context;

/**
 * Created by nasheyarahman on 5/22/17.
 */

public class MainApplication extends Application {

    private static Context context;

    public void onCreate() {
        super.onCreate();
        MainApplication.context = getApplicationContext();
    }

    public static Context getAppContext() {
        return MainApplication.context;
    }
}
