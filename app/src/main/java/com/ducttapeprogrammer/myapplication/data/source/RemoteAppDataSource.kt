package com.ducttapeprogrammer.myapplication.data.source

import androidx.lifecycle.LiveData
import com.ducttapeprogrammer.myapplication.Result
import com.ducttapeprogrammer.myapplication.data.model.CurrentWeather
import com.ducttapeprogrammer.myapplication.data.model.WeatherForNextSevenDays

/**
 * This interface will hold all the details about the operations in the application
 * */
interface RemoteAppDataSource {

    /**
     * This function will return the [CurrentWeather] that is wrapped in the [Result]
     * */
    suspend fun getCurrentWeather(
        latitude: String?,
        longitude: String?,
        appId: String
    ): Result<CurrentWeather>

    /**
     * This function will observe the [CurrentWeather] response
     * */
    fun observeCurrentWeather(): LiveData<CurrentWeather>

    /**
     * This function will return the [WeatherForNextSevenDays] that is wrapped in the [Result]
     * */
    suspend fun getWeatherDataForNextSevenDays(
        latitude: String?,
        longitude: String?,
        appId: String
    ) : Result<List<WeatherForNextSevenDays.WeatherList>>

    /**
     * This function will observer the [WeatherForNextSevenDays] response
     * */
    fun observeWeatherDataForNextSevenDays(): LiveData<List<WeatherForNextSevenDays.WeatherList>>


}