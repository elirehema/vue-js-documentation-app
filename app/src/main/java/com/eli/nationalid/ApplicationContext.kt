package com.eli.nationalid

import android.app.Application
import android.content.Context
import android.content.res.Configuration
import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication

import com.crashlytics.android.BuildConfig
import com.crashlytics.android.Crashlytics
import com.crashlytics.android.core.CrashlyticsCore
import com.eli.nationalid.SharedPrefer.SharedPreferencesManager
import com.eli.nationalid.util.Constants
import com.eli.nationalid.util.LocaleManager
import com.facebook.stetho.Stetho
import io.fabric.sdk.android.Fabric

class ApplicationContext : MultiDexApplication(){
    private var sharedPreferencesManager: SharedPreferencesManager? = null
    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(LocaleManager.setLocale(base))
        MultiDex.install(this)
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        LocaleManager.setLocale(this)
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        configureCrashReporting()
        Stetho.initializeWithDefaults(this);
        sharedPreferencesManager = SharedPreferencesManager(this)
    }

    override fun onTerminate() {
        super.onTerminate()
    }

    private fun configureCrashReporting() {
        val crashlyticsCore = CrashlyticsCore.Builder()
                .disabled(BuildConfig.DEBUG)
                .build()
        Fabric.with(this, Crashlytics.Builder().core(crashlyticsCore).build(), Crashlytics())
        Crashlytics.setUserIdentifier(Constants.CRUSH_ANALYTICS_USER_IDENTIER)
    }


    companion object {
        lateinit var instance: ApplicationContext
            private set
    }
}