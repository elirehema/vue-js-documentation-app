package com.eli.nationalid.Activities;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.eli.nationalid.BuildConfig;
import com.eli.nationalid.R;

public class ShareBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String url = intent.getDataString();
        if (url == null) {
            return;
        }
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");

        String shareMessage= "\n Find all :-" +
                " \n- Vue documentations,\n" +
                "\n- NuxitJs Documentation,\n" +
                "\n- Vuetify Documentation,\n" +
                "\n- Composition API's Documentation,\n" +
                "\n- Bootstrap Vue Documentation,\n" +
                "\n- Vue Native Documentation,\n" +
                "\n- Vue-multiselect Documentation,\n In this app:- \n\n";
        shareMessage = shareMessage + "https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID +"\n\n" +
                "\n @vuejsdevelopers, @vuejs @vuetifyjs";
        shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
        Intent chooserIntent = Intent.createChooser(shareIntent, context.getString(R.string.app_name));
        chooserIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(chooserIntent);
    }
}