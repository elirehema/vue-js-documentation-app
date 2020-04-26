package com.eli.nationalid.Activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.crashlytics.android.Crashlytics
import com.eli.nationalid.Api.ApiClient
import com.eli.nationalid.models.DataModule
import com.eli.nationalid.room.database.DatabaseClient
import com.eli.nationalid.room.entities.DataListEntity
import com.student.Api.ApiInterface
import io.fabric.sdk.android.Fabric
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (Fabric.isInitialized()) {
            logUser()
        }
        Handler().postDelayed({ // This method will be executed once the timer is over
            /*Intent i = new Intent(SplashActivity.this, MainActivity.ope);
                startActivity(i);*/
            finish()
            startActivity(Intent(this@SplashActivity, MainActivity::class.java))
        }, 2000)
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onRestart() {
        super.onRestart()
    }

    private fun logUser() {
        // TODO: Use the current user's information
        // You can call any combination of these three methods
        Crashlytics.setUserEmail("user@fabric.io")
        Crashlytics.setUserName("Test User")
    }

}