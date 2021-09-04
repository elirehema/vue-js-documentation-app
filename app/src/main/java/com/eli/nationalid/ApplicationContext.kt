package com.eli.nationalid

import android.content.Context
import android.content.res.Configuration
import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication
import com.eli.nationalid.SharedPrefer.SharedPreferencesManager
import com.eli.nationalid.util.LocaleManager
import com.facebook.stetho.Stetho

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
        Stetho.initializeWithDefaults(this);
        sharedPreferencesManager = SharedPreferencesManager(this)
    }

    override fun onTerminate() {
        super.onTerminate()
    }



    companion object {
        lateinit var instance: ApplicationContext
            private set
    }
}