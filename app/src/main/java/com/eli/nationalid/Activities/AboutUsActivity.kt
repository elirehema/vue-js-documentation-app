package com.eli.nationalid.Activities

import android.os.Bundle
import com.eli.nationalid.R
import com.eli.nationalid.util.BaseActivity
import kotlinx.android.synthetic.main.activity_about_us.*

class AboutUsActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about_us)
        setupToolbar(resources.getString(R.string.about_this_app))
    }


    private fun setupToolbar(str: String) {
        item_toolbar.title = str
        setSupportActionBar(item_toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }
}
