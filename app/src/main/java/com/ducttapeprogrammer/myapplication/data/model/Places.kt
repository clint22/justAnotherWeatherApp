package com.ducttapeprogrammer.myapplication.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * This is a POJO class for the Google places which also act's
 * as the table for the Room database. We are auto-generating the [id]
 * along with with other parameters
 * */
@Entity(tableName = "places_table")
data class Places(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0,
    @ColumnInfo(name = "latitude")
    var latitude: Double?,
    @ColumnInfo(name = "longitude")
    var longitude: Double?,
    @ColumnInfo(name = "region")
    var region: String?,
    @ColumnInfo(name = "state")
    var state: String?,
    @ColumnInfo(name = "country")
    var country: String?
)