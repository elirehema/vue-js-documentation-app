package com.eli.nationalid.room.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "data_list")
data class DataListEntity(
        @field:PrimaryKey val uid: Int?=null,
        var dtitle: String,
        var dsubtitle: String,
        var dIcon: String,
        var dLink: String
)