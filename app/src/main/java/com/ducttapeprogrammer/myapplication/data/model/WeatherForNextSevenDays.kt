package com.ducttapeprogrammer.myapplication.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "weather_weekly_table")
data class WeatherForNextSevenDays(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0,
    @ColumnInfo(name = "weatherId")
    var weatherId: Int,
    @ColumnInfo(name = "maxTemp")
    var maximumTemperature: Double,
    @ColumnInfo(name = "minTemp")
    var minimumTemperature: Double
)