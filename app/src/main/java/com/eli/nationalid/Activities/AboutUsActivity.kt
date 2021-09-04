package com.eli.nationalid.Activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.eli.nationalid.R
import com.eli.nationalid.util.BaseActivity
import kotlinx.android.synthetic.main.activity_about_us.*
import com.google.android.gms.oss.licenses.OssLicensesMenuActivity;
import android.content.pm.PackageManager

import android.content.pm.PackageInfo




class AboutUsActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about_us)
        setupToolbar(resources.getString(R.string.about_this_app))
        displayApplicationVersion()
        licence_text.setOnClickListener {
            startLicenceIntent()
        }
    }


    private fun setupToolbar(str: String) {
        item_toolbar.title = str
        setSupportActionBar(item_toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }
    fun startLicenceIntent(){
        startActivity(Intent(this, OssLicensesMenuActivity::class.java))
        OssLicensesMenuActivity.setActivityTitle(getString(R.string.custom_license_title));

    }

    fun displayApplicationVersion(){
        val manager = this.packageManager
        val info = manager.getPackageInfo(this.packageName, PackageManager.GET_ACTIVITIES)

        about_version.text = "Version: "+info.versionName+" ["+info.versionCode.toString()+"]";

    }
}
