package com.ducttapeprogrammer.myapplication.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


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