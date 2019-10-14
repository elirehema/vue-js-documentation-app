package com.eli.nationalid.Activities;

import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;

import androidx.browser.customtabs.CustomTabsIntent;

import com.eli.nationalid.R;
import com.eli.nationalid.util.BaseActivity;
import com.eli.nationalid.util.Constants;

public class MainActivity extends BaseActivity {

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

    private void openCustomTab() {
        Bitmap icon = BitmapFactory.decodeResource(getResources(), android.R.drawable.ic_menu_share);

        Intent actionIntent = new Intent(this.getApplicationContext(), ShareBroadcastReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, actionIntent, PendingIntent.FLAG_UPDATE_CURRENT);


        CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
        builder.setToolbarColor(getResources().getColor(R.color.secondaryDarkColor));
        builder.setShowTitle(true);
        //builder.addDefaultShareMenuItem();
        builder.setActionButton(icon, "Share ", pendingIntent);
        CustomTabsIntent customTabsIntent = builder.build();
        customTabsIntent.launchUrl(this, Uri.parse(Constants.VUEJS_URL));

    }

}
