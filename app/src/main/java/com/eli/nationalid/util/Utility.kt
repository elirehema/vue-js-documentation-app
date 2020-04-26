package com.eli.nationalid.util

import android.content.Context
import android.content.Intent
import android.net.Uri
import com.eli.nationalid.util.Constants.*


/**
 * Copyright (c) $today.year.
 * This file was created by eli on 4/25/20
 * You are not allowed to copy it or use it in another project
 * Without permission from creator
 **/

fun Context.loadJSONFromAssets(fileName: String): String {
    return applicationContext.assets.open(fileName).bufferedReader().use { reader ->
        reader.readText()
    }
}

fun Context.openFacebook() {
    try {
        this.getPackageManager().getPackageInfo("com.facebook.katana", 0)
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("fb://page/16434752359235394"))
        this.startActivity(intent)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    } catch (e: Exception) {
        this.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(FACEBOOK_URL)))
    }
}

fun Context.openYoutubeChannel() {
    val intent = Intent(Intent.ACTION_VIEW)
    intent.data = Uri.parse(YOUTUBE_CHANNEL)
    this.startActivity(intent)
}
fun Context.openLinkedin(){
    val intent = Intent(Intent.ACTION_VIEW)
    intent.data = Uri.parse(LINKEDIN_URL)
    this.startActivity(intent)
}
fun Context.openTwitter() {
    try {
        //get Twitter ApplicationContext
        this.getPackageManager().getPackageInfo("com.twitter.android", 0)
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("twitter://user?user_id=2292889800"))
        this.startActivity(intent)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    } catch (e: Exception) {
        this.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(TWITTER_URL)))
    }
}