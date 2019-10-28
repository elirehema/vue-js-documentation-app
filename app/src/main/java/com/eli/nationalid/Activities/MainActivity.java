package com.eli.nationalid.Activities;

import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ImageDecoder;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.content.res.AppCompatResources;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.browser.customtabs.CustomTabsIntent.Builder;
import androidx.core.content.res.ResourcesCompat;

import com.eli.nationalid.R;
import com.eli.nationalid.util.BaseActivity;
import com.eli.nationalid.util.Constants;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends BaseActivity {
    private  Bitmap icon;
    private    ImageDecoder.Source source;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    protected void onStart() {
        super.onStart();
        /** Use a CustomTabsIntent.Builder to configure CustomTabsIntent.Once ready,
         * call CustomTabsIntent.Builder.build() to create a CustomTabsIntentand
         * launch the desired Url with CustomTabsIntent.launchUrl()
         **/
        openCustomTab();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    public void openCustomTab() {




        Builder builder = new CustomTabsIntent.Builder();
        builder.setToolbarColor(getResources().getColor(R.color.secondaryDarkColor));
        builder.setShowTitle(true);
        builder.addDefaultShareMenuItem();

        Intent actionIntent = new Intent(this.getApplicationContext(), ShareBroadcastReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, actionIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        icon = BitmapFactory.decodeResource(this.getResources(), android.R.drawable.ic_menu_share);

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

        PendingIntent pendingIntent1 = PendingIntent.getActivity(this,requestCode,intent, PendingIntent.FLAG_CANCEL_CURRENT);
        PendingIntent VUETIFY_INTENT = PendingIntent.getActivity(this,requestCode,vueTifyIntent,PendingIntent.FLAG_CANCEL_CURRENT);
        PendingIntent COMPOSITION_API_INTENT = PendingIntent.getActivity(this, requestCode,compositionIntent, PendingIntent.FLAG_CANCEL_CURRENT);

        builder.setActionButton(icon, "Share ", pendingIntent);
        builder.addMenuItem("NuxitJs", pendingIntent1);
        builder.addMenuItem("VuetifyJs",VUETIFY_INTENT);
        builder.addMenuItem("Composition API", COMPOSITION_API_INTENT);
        CustomTabsIntent customTabsIntent = builder.build();
        customTabsIntent.intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        customTabsIntent.intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        customTabsIntent.launchUrl(this, Uri.parse(Constants.VUEJS_URL));

    }


}
