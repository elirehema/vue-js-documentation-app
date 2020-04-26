package com.student.Api

import androidx.lifecycle.LiveData
import com.eli.nationalid.models.DataModule

import retrofit2.Call
import retrofit2.http.*

interface ApiInterface {

    @GET("datas.json")
    fun getDataList(): Call<List<DataModule>>


}
