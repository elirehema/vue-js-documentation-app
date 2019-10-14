package com.eli.nationalid.util;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;

import com.eli.nationalid.SharedPrefer.SharedPreferencesManager;

import java.util.Locale;

public class LocaleManager {
    /**
     * For english locale
     */
    public static final String LANGUAGE_KEY_ENGLISH = "en";
    /**
     * for hindi locale
     */
    public static final String LANGUAGE_KEY_SWAHILI = "sw";
    /***
     * // for spanish locale
     */

    public static final String LANGUAGE_KEY_SPANISH = "es";
    /**
     *  SharedPreferences Key
     */
    //private static final String LANGUAGE_KEY = "language_key";

    /**
     * set current pref locale
     * @param mContext
     * @return
     */
    public static Context setLocale(Context mContext) {
        return updateResources(mContext, getLanguagePref(mContext));
    }

    /**
     * Set new Locale with context
     * @param mContext
     * @param mLocaleKey
     * @return
     */
    public static Context setNewLocale(Context mContext, String mLocaleKey) {
        setLanguagePref(mContext, mLocaleKey);
        return updateResources(mContext, mLocaleKey);
    }

    /**
     * Get saved Locale from SharedPreferences
     * @param mContext current context
     * @return current locale key by default return english locale
     */
    public static String getLanguagePref(Context mContext) {
        SharedPreferencesManager mPreferences = new SharedPreferencesManager(mContext);
        return mPreferences.getString(SharedPreferencesManager.Key.LANGUAGE_KEY, LANGUAGE_KEY_ENGLISH);
    }

    /**
     *  set pref key
     * @param mContext
     * @param localeKey
     */
    private static void setLanguagePref(Context mContext, String localeKey) {
        SharedPreferencesManager mPreferences = new SharedPreferencesManager(mContext);
        mPreferences.put(SharedPreferencesManager.Key.LANGUAGE_KEY, localeKey);
    }

    /**
     * update resource
     * @param context
     * @param language
     * @return
     */
    private static Context updateResources(Context context, String language) {
        Locale locale = new Locale(language);
        Locale.setDefault(locale);
        Resources res = context.getResources();
        Configuration config = new Configuration(res.getConfiguration());
        if (Build.VERSION.SDK_INT >= 17) {
            config.setLocale(locale);
            context = context.createConfigurationContext(config);
        } else {
            config.locale = locale;
            res.updateConfiguration(config, res.getDisplayMetrics());
        }
        return context;
    }

    /**
     * get current locale
     * @param res
     * @return
     */
    public static Locale getLocale(Resources res) {
        Configuration config = res.getConfiguration();
        return Build.VERSION.SDK_INT >= 24 ? config.getLocales().get(0) : config.locale;
    }
}
