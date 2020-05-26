package com.ducttapeprogrammer.myapplication.data.source

import androidx.lifecycle.LiveData
import com.ducttapeprogrammer.myapplication.Result
import com.ducttapeprogrammer.myapplication.data.CurrentWeather

interface AppDataSource {

    suspend fun getCurrentWeather(
        latitude: String,
        longitude: String,
        appId: String
    ) : Result<CurrentWeather>

    fun observeCurrentWeather() : LiveData<CurrentWeather>


}