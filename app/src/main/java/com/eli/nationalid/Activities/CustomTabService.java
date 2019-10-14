package com.eli.nationalid.Activities;

import android.content.ComponentName;

import androidx.browser.customtabs.CustomTabsClient;
import androidx.browser.customtabs.CustomTabsServiceConnection;

public class CustomTabService extends CustomTabsServiceConnection {
    /**
     * Called when a connection to the {@link CustomTabsService} has been established.
     *
     * @param name   The concrete component name of the service that has been connected.
     * @param client {@link CustomTabsClient} that contains the {@link IBinder} with which the
     * connection have been established. All further communication should be initiated
     */

    /**
     * Package name for the Chrome channel the client wants to connect to. This
     * depends on the channel name.
     * Stable = com.android.chrome
     * Beta = com.chrome.beta
     * Dev = com.chrome.dev
     **/
    public static final String CUSTOM_TAB_PACKAGE_NAME = "com.android.chrome";  // Change when in stable
    private CustomTabsClient customTabsClient;


    @Override
    public void onCustomTabsServiceConnected(ComponentName name, CustomTabsClient client) {
        customTabsClient = client;

    }

    @Override
    public void onServiceDisconnected(ComponentName componentName) {

    }

    @Override
    public void onBindingDied(ComponentName name) {

    }

    @Override
    public void onNullBinding(ComponentName name) {

    }

}
