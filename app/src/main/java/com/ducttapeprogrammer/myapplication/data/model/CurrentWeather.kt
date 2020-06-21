package com.ducttapeprogrammer.myapplication.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * This is a POJO class for the Current Weather which also act's
 * as the table for the Room database. We are auto-generating the [id]
 * along with with other parameters
 * */
@Entity(tableName = "current_weather_table")
data class CurrentWeather(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0,
    @ColumnInfo(name = "temperature")
    var temperature: Double,
    @ColumnInfo(name = "description")
    var description: String,
    @ColumnInfo(name = "windSpeed")
    var windSpeed: Double,
    @ColumnInfo(name = "currentRegion")
    var currentRegion: String

)