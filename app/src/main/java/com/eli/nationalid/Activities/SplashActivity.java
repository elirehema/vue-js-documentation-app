package com.eli.nationalid.Activities;

import android.app.PendingIntent;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import androidx.browser.customtabs.CustomTabsIntent;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;

import com.crashlytics.android.Crashlytics;
import com.eli.nationalid.R;
import com.eli.nationalid.util.Constants;

import io.fabric.sdk.android.Fabric;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (Fabric.isInitialized()) {
            logUser();
        }
        new Handler().postDelayed(new Runnable() {


            @Override
            public void run() {
                // This method will be executed once the timer is over
                /*Intent i = new Intent(SplashActivity.this, MainActivity.ope);
                startActivity(i);*/

                finish();
                openCustomTab();
            }
        }, 1500);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }


    private void logUser() {
        // TODO: Use the current user's information
        // You can call any combination of these three methods
        Crashlytics.setUserEmail("user@fabric.io");
        Crashlytics.setUserName("Test User");
    }


    private void openCustomTab() {


        CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
        builder.setToolbarColor(getResources().getColor(R.color.secondaryDarkColor));
        builder.setShowTitle(true);
        builder.addDefaultShareMenuItem();

        Intent actionIntent = new Intent(this.getApplicationContext(), ShareBroadcastReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, actionIntent, PendingIntent.FLAG_UPDATE_CURRENT);

       Bitmap icon = BitmapFactory.decodeResource(this.getResources(), android.R.drawable.ic_menu_share);

        /**
         * Create {@link PendingIntent} and another {@link CustomTabsIntent}
         * To open nuxtjs website
         * **/
        CustomTabsIntent anotherCustomTab = new CustomTabsIntent.Builder()
                .setToolbarColor(getResources().getColor(R.color.secondaryDarkColor))
                .addDefaultShareMenuItem()
                .setShowTitle(true).build();
        int requestCode = 100;
        Intent intent = anotherCustomTab.intent;
        intent.setData(Uri.parse(Constants.NUXT_URL));

        /**
         * Vuetify Intents
         * **/
        CustomTabsIntent vueTifyCustomTab = new CustomTabsIntent.Builder()
                .setToolbarColor(getResources().getColor(R.color.secondaryDarkColor))
                .addDefaultShareMenuItem()
                .setShowTitle(true).build();
        Intent vueTifyIntent = vueTifyCustomTab.intent;
        vueTifyIntent.setData(Uri.parse(Constants.VUETIFY_URL));

        /**
         * Composition Api Intent
         * **/
        CustomTabsIntent compositionCustomTab = new CustomTabsIntent.Builder()
                .setToolbarColor(getResources().getColor(R.color.secondaryDarkColor))
                .addDefaultShareMenuItem()
                .setShowTitle(true).build();
        Intent compositionIntent = compositionCustomTab.intent;
        compositionIntent.setData(Uri.parse(Constants.COMPOSITION_API_URL));

        /**
         * VueNative
         * **/
        CustomTabsIntent vueNativeCustomTab = new CustomTabsIntent.Builder()
                .setToolbarColor(getResources().getColor(R.color.secondaryDarkColor))
                .addDefaultShareMenuItem()
                .setShowTitle(true).build();
        Intent vueNativeIntent = vueNativeCustomTab.intent;
        vueNativeIntent.setData(Uri.parse(Constants.VUENATIVE_URL));

        /**
         * BootStrapVue
         * **/
        CustomTabsIntent bootStrapVueCustomTab = new CustomTabsIntent.Builder()
                .setToolbarColor(getResources().getColor(R.color.secondaryDarkColor))
                .addDefaultShareMenuItem()
                .setShowTitle(true).build();
        Intent bootStrapVueIntent = bootStrapVueCustomTab.intent;
        bootStrapVueIntent.setData(Uri.parse(Constants.BOOTSTRAP_VUE));

        /**
         * Vue-multiselect Intent
         * **/
        CustomTabsIntent multiselectCustomTab = new CustomTabsIntent.Builder()
                .setToolbarColor(getResources().getColor(R.color.secondaryDarkColor))
                .addDefaultShareMenuItem()
                .setShowTitle(true).build();
        Intent multiselectIntent = multiselectCustomTab.intent;
        multiselectIntent.setData(Uri.parse(Constants.VUE_MULT_SELECT_URL));


        PendingIntent pendingIntent1 = PendingIntent.getActivity(this, requestCode, intent, PendingIntent.FLAG_CANCEL_CURRENT);
        PendingIntent VUETIFY_INTENT = PendingIntent.getActivity(this, requestCode, vueTifyIntent, PendingIntent.FLAG_CANCEL_CURRENT);
        PendingIntent COMPOSITION_API_INTENT = PendingIntent.getActivity(this, requestCode, compositionIntent, PendingIntent.FLAG_CANCEL_CURRENT);
        PendingIntent VUENATIVE_INTENT = PendingIntent.getActivity(this, requestCode, vueNativeIntent, PendingIntent.FLAG_CANCEL_CURRENT);
        PendingIntent BOOTSTRAP_VUE_INTENT = PendingIntent.getActivity(this, requestCode, bootStrapVueIntent, PendingIntent.FLAG_CANCEL_CURRENT);
        PendingIntent MULTI_SELECTION_API_INTENT = PendingIntent.getActivity(this, requestCode,multiselectIntent, PendingIntent.FLAG_CANCEL_CURRENT);

        builder.setActionButton(icon, "Share ", pendingIntent);
        builder.addMenuItem("NuxitJs", pendingIntent1);
        builder.addMenuItem("Vue-Native", VUENATIVE_INTENT);
        builder.addMenuItem("VuetifyJs", VUETIFY_INTENT);
        builder.addMenuItem("Composition API", COMPOSITION_API_INTENT);
        builder.addMenuItem("Vue-multiselect",MULTI_SELECTION_API_INTENT);
        builder.addMenuItem("Bootstrap Vue", BOOTSTRAP_VUE_INTENT);
        CustomTabsIntent customTabsIntent = builder.build();
        customTabsIntent.intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        customTabsIntent.intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        customTabsIntent.launchUrl(this, Uri.parse(Constants.VUEJS_URL));

    }
}