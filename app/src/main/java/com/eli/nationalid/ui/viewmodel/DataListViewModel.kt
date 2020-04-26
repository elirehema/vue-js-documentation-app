package com.eli.nationalid.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.eli.nationalid.Api.ApiClient
import com.eli.nationalid.models.DataModule
import com.student.Api.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Copyright (c) $today.year.
 * This file was created by eli on 4/26/20
 * You are not allowed to copy it or use it in another project
 * Without permission from creator
 **/

class DataListViewModel : ViewModel() {

    private var dataList: MutableLiveData<List<DataModule?>>? = null
    var apiInterface = ApiClient.getInstance().create(ApiInterface::class.java)


    /**
     * we will call this method to get the data
     */
    fun getDataList(): LiveData<List<DataModule?>>? { //if the list is null
        if (dataList == null) {
            dataList = MutableLiveData<List<DataModule?>>()
            loadDatas()
        }
        return dataList
    }

    /**
     * This method is using Retrofit to get the JSON data from URL
     */
    private fun loadDatas() {
        val call = apiInterface.getDataList()
        call.enqueue(object : Callback<List<DataModule>?> {
            override fun onResponse(call: Call<List<DataModule>?>, response: Response<List<DataModule>?>) {
                val statusCode = response.code()
                val user: List<DataModule>? = response.body()

                dataList?.value = user;
            }

            override fun onFailure(call: Call<List<DataModule>?>, t: Throwable) {
                // Log error here since request failed
            }
        })

//        call.enqueue(object : Callback<List<DataModule>> {
//
//            override fun onResponse(call: Call<List<DataModule>>, response: Response<List<DataModule>>) {
//                print("DATAS"+response.body())
//                dataList?.value = response.body()
//            }
//
//            override fun onFailure(call: Call<List<DataModule>>, t: Throwable) {
//               print("ERROR: "+t.message +t.cause)
//            }
//        })
    }
}