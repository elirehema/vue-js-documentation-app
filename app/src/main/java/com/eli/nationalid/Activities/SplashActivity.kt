package com.eli.nationalid.Activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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


}