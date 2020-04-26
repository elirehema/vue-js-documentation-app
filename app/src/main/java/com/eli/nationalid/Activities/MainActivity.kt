package com.eli.nationalid.Activities

import android.app.AlertDialog
import android.app.PendingIntent
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.view.GravityCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.eli.nationalid.Api.ApiClient
import com.eli.nationalid.R
import com.eli.nationalid.adapters.RecyclerAdapter
import com.eli.nationalid.models.DataModule
import com.eli.nationalid.room.database.DatabaseClient
import com.eli.nationalid.room.entities.DataListEntity
import com.eli.nationalid.room.model.infoListViewModel
import com.eli.nationalid.ui.viewmodel.DataListViewModel
import com.eli.nationalid.util.*
import com.google.android.material.navigation.NavigationView
import com.student.Api.ApiInterface
import kotlinx.android.synthetic.main.main_activity.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : BaseActivity() {
    var model: DataListViewModel? = null
    var databaseModel: infoListViewModel? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        configureToolBar();
        configureNavigationDrawer();
        model = ViewModelProviders.of(this).get(DataListViewModel::class.java)
        databaseModel = ViewModelProviders.of(this).get(infoListViewModel::class.java)

    }


    private fun configureToolBar() {
        setSupportActionBar(toolbar)
        toolbar.setNavigationIcon(R.drawable.ic_menu_black_24dp)
        toolbar.inflateMenu(R.menu.navigation)
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }


    override fun onResume() {
        super.onResume()

        databaseModel?.itemInfoList?.observe(this, Observer<List<DataListEntity?>?> { t ->
            if(t.isNullOrEmpty()){
                callRemoteDataAndSaveToLocalDatabase()
            }
            createList(t as List<DataListEntity>)
        })

       // val data = model?.getDataList()

    }
    private fun configureNavigationDrawer() {
        navigation.setNavigationItemSelectedListener(object : NavigationView.OnNavigationItemSelectedListener {
            override fun onNavigationItemSelected(menuItem: MenuItem): Boolean {
                val itemId: Int = menuItem.getItemId()
                if (itemId == R.id.nav_about_us) {
                    startActivity(Intent(this@MainActivity, AboutUsActivity::class.java))
                } else if (itemId == R.id.nav_twitter) {
                   openTwitter();
                } else if (itemId == R.id.nav_facebook) {
                   openFacebook()
                } else if (itemId == R.id.nav_linkedin) {
                    openLinkedin()
                } else if (itemId == R.id.nav_youtube) {
                   openYoutubeChannel()
                } else if (itemId == R.id.nav_contact_us) {
                    //startActivity(Intent(this@MainActivity, ContactUsActivity::class.java))
                } else if (itemId == R.id.nav_general_info) {

                } else if (itemId == R.id.nav_home) {
                    toggle()

                } else if (itemId == R.id.nav_our_site) {

                }
                return false
            }
        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.getItemId() == android.R.id.home){ toggle() }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        return true
    }

    private fun toggle() {
        if (drawerLayout.isDrawerVisible(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else { drawerLayout.openDrawer(GravityCompat.START) }
    }

    private fun createList(apartmentData: List<DataListEntity>) {
        recyclerview.layoutManager = LinearLayoutManager(this)
        recyclerview.isNestedScrollingEnabled = false
        recyclerview.setHasFixedSize(true)
        recyclerview.setItemViewCacheSize(20)
        recyclerview!!.adapter = RecyclerAdapter(this, apartmentData)
        recyclerview!!.visibility = View.VISIBLE
    }

    fun openCustomTab(TAB_URL: String?) {
        val builder = CustomTabsIntent.Builder()
        //builder.setToolbarColor(resources.getColor(R.color.secondaryDarkColor, null))
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



    override fun onBackPressed() {
        AlertDialog.Builder(this)
                .setMessage("Are you sure you want to exit this app ?")
                .setCancelable(false)
                .setPositiveButton("YEs"){ _, _ -> super.onBackPressed() }
                .setNegativeButton("No", null)
                .show()

    }

    override fun onPause() {
        super.onPause()
        callRemoteDataAndSaveToLocalDatabase()

    }

    fun callRemoteDataAndSaveToLocalDatabase(){
        var apiInterface = ApiClient.getInstance().create(ApiInterface::class.java)
        val call = apiInterface.getDataList()
        call.enqueue(object : Callback<List<DataModule>?> {
            override fun onResponse(call: Call<List<DataModule>?>, response: Response<List<DataModule>?>) {
                //val statusCode = response.code()
                val user: List<DataModule>? = response.body()
                if (user != null) {
                    for (item in user){
                        var datalistItem = DataListEntity(item.mId!!,item.mTitle!!,item.mSubtitle!!,item.mIcon!!,item.mUrlink!!);
                        DatabaseClient.getmInstance(baseContext).getAppDatabase()
                                .infoModelDao()
                                .insertDataList(datalistItem)
                    }
                }


            }

            override fun onFailure(call: Call<List<DataModule>?>, t: Throwable) {
                // Log error here since request failed
            }
        })
    }
}


