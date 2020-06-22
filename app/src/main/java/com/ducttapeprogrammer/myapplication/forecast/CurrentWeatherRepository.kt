package com.ducttapeprogrammer.myapplication.forecast

import androidx.lifecycle.LiveData
import com.ducttapeprogrammer.myapplication.Result
import com.ducttapeprogrammer.myapplication.data.model.CurrentWeather
import com.ducttapeprogrammer.myapplication.data.model.WeatherForNextSevenDays

interface CurrentWeatherRepository {
    /**
     * This function will return the result of current weather from the [RemoteDataSource.getCurrentWeather]
     * */
    suspend fun getCurrentWeather(
        latitude: String?,
        longitude: String?,
        appId: String
    ): Result<CurrentWeather>

    /**
     * This function will observe any changes in [WeatherForNextSevenDays.WeatherList] and updates the changes
     * */
    fun observeWeatherForNextSevenDays(): LiveData<List<WeatherForNextSevenDays.WeatherList>>

    /**
     * This function will help in getting the [WeatherForNextSevenDays]
     * */
    suspend fun getWeatherDataForNextSevenDays(
        latitude: String?,
        longitude: String?,
        appId: String
    ) : Result<List<WeatherForNextSevenDays.WeatherList>>
}