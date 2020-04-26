package com.eli.nationalid.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 * Copyright (c) $today.year.
 * This file was created by eli on 4/25/20
 * You are not allowed to copy it or use it in another project
 * Without permission from creator
 */
@Parcelize
data class DataModule(
        @SerializedName("id") var mId: Int?,
        @SerializedName("link")var mUrlink: String? = null,
        @SerializedName("title") var mTitle: String? = null,
        @SerializedName("subtitle")var mSubtitle: String? = null,
        @SerializedName("icon")var mIcon: String? = null

) : Parcelable