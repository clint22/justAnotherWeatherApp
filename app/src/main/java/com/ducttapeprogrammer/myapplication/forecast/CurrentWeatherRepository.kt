package com.ducttapeprogrammer.myapplication.forecast

import androidx.lifecycle.LiveData
import com.ducttapeprogrammer.myapplication.Result
import com.ducttapeprogrammer.myapplication.data.CurrentWeather
import com.ducttapeprogrammer.myapplication.data.remote.RemoteDataSource

class CurrentWeatherRepository {

    private val appDataSource = RemoteDataSource

    suspend fun getCurrentWeather(
        latitude: String,
        longitude: String,
        appId: String
    ): Result<CurrentWeather> {
        return appDataSource.getCurrentWeather(latitude, longitude, appId)

    }

    fun observeCurrentWeather(): LiveData<CurrentWeather> = appDataSource.observeCurrentWeather()
}