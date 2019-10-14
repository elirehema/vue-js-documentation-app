package com.eli.nationalid;


import android.content.Context;

import androidx.multidex.MultiDex;
import androidx.multidex.MultiDexApplication;

import com.crashlytics.android.Crashlytics;
import com.crashlytics.android.core.CrashlyticsCore;
import com.eli.nationalid.SharedPrefer.SharedPreferencesManager;
import com.eli.nationalid.util.Constants;
import com.eli.nationalid.util.LocaleManager;
import io.fabric.sdk.android.Fabric;

public class App extends MultiDexApplication {
    private static final String TAG = "App";

    private SharedPreferencesManager sharedPreferencesManager;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(LocaleManager.setLocale(base));
        MultiDex.install(this);
    }
    @Override
    public void onConfigurationChanged(android.content.res.Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        LocaleManager.setLocale(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        configureCrashReporting();
        sharedPreferencesManager = new SharedPreferencesManager(this);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
    private void configureCrashReporting() {
        CrashlyticsCore crashlyticsCore = new CrashlyticsCore.Builder()
                .disabled(com.crashlytics.android.BuildConfig.DEBUG)
                .build();
        Fabric.with(this, new Crashlytics.Builder().core(crashlyticsCore).build(), new Crashlytics());
        Crashlytics.setUserIdentifier(Constants.CRUSH_ANALYTICS_USER_IDENTIER);
    }
}
