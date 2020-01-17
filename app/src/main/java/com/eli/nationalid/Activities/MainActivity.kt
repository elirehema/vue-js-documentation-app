package com.eli.nationalid.Activities

import android.app.AlertDialog
import android.app.PendingIntent
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.browser.customtabs.CustomTabsIntent
import com.eli.nationalid.R
import com.eli.nationalid.util.BaseActivity
import com.eli.nationalid.util.Constants
import kotlinx.android.synthetic.main.main_activity.*

class MainActivity : BaseActivity(), View.OnClickListener {
    private var icon: Bitmap? = null
    private val source: ImageDecoder.Source? = null
    var button: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        button_vue_js.setOnClickListener(this)
        button_bootstrap_vue.setOnClickListener(this)
        button_vue_native.setOnClickListener(this)
        button_vuex_doc.setOnClickListener(this)
        button_composition_apis.setOnClickListener(this)
        button_vue_multiselect.setOnClickListener(this)
        button_nuxt_js.setOnClickListener(this)
        button_nuxt_pwa.setOnClickListener(this)
        button_vuetify_js.setOnClickListener(this)
        button_bootstrap_vue.setOnClickListener(this)
        button_this_app_repo.setOnClickListener(this)
        button_vue_repo.setOnClickListener(this)
    }

    override fun onStart() {
        super.onStart()
        /** Use a CustomTabsIntent.Builder to configure CustomTabsIntent.Once ready,
         * call CustomTabsIntent.Builder.build() to create a CustomTabsIntentand
         * launch the desired Url with CustomTabsIntent.launchUrl()
         */
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onRestart() {
        super.onRestart()
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    override fun onClick(v: View) {
        when (v.id) {
            R.id.button_vue_js -> {
                openCustomTab(Constants.VUEJS_URL)
            }
            R.id.button_bootstrap_vue ->{
                openCustomTab(Constants.BOOTSTRAP_VUE)
            }
            R.id.button_composition_apis ->{
                openCustomTab(Constants.COMPOSITION_API_URL)
            }
            R.id.button_nuxt_js->{
                openCustomTab(Constants.NUXT_URL)
            }
            R.id.button_nuxt_pwa ->{
                openCustomTab(Constants.NUXT_PWA_URL)
            }
            R.id.button_vue_multiselect ->{
                openCustomTab(Constants.VUE_MULT_SELECT_URL)
            }
            R.id.button_vuex_doc -> {
                openCustomTab(Constants.VUEX_URL)
            }
            R.id.button_vue_router ->{
                openCustomTab(Constants.VUE_ROUTER_URL)
            }
            R.id.button_vue_native ->{
                openCustomTab(Constants.VUENATIVE_URL)
            }
            R.id.button_vuetify_js ->{
                openCustomTab(Constants.VUETIFY_URL)
            }
            R.id.button_this_app_repo ->{
                openCustomTab(Constants.THIS_APP_REPO)
            }
            R.id.button_vue_repo ->{
                openCustomTab(Constants.VUEJS_REPO)
            }
        }
    }

    fun openCustomTab(TAB_URL: String?) {
        val builder = CustomTabsIntent.Builder()
        builder.setToolbarColor(resources.getColor(R.color.secondaryDarkColor))
        builder.setShowTitle(true)
        builder.addDefaultShareMenuItem()
        val actionIntent = Intent(this.applicationContext, ShareBroadcastReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(applicationContext, 0, actionIntent, PendingIntent.FLAG_UPDATE_CURRENT)

        builder.setActionButton(BitmapFactory.decodeResource(this.resources, android.R.drawable.ic_menu_share), "Share ", pendingIntent)
        val customTabsIntent = builder.build()
        customTabsIntent.intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        customTabsIntent.intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        customTabsIntent.launchUrl(this, Uri.parse(TAB_URL))
    }

    companion object {
        private const val TAG = "MainActivity"
    }

    override fun onBackPressed() {
        AlertDialog.Builder(this)
                .setMessage("Are you sure you want to exit this app ?")
                .setCancelable(false)
                .setPositiveButton("YEs"){dialog, which -> super.onBackPressed() }
                .setNegativeButton("No", null)
                .show()

    }
}