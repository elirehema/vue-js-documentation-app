package com.eli.nationalid.Api;

import com.student.Api.ApiInterface;

/**
 * Copyright (c) $today.year.
 * This file was created by eli on 2/14/20
 * You are not allowed to copy it or use it in another project
 * Without permission from creator
 **/

public class NetworkModule {

    public ApiInterface getApiClient(){
        return ApiClient.getInstance().create(ApiInterface.class);
    }


}

