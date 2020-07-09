package com.ducttapeprogrammer.myapplication.forecast

import androidx.lifecycle.LiveData
import com.ducttapeprogrammer.myapplication.Result
import com.ducttapeprogrammer.myapplication.data.model.CurrentWeather
import com.ducttapeprogrammer.myapplication.data.model.WeatherForNextSevenDays

/**
 * This interface will provide methods for CurrentWeatherRepository and FakeCurrentWeatherRepository
 * */
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
    fun observeWeatherForNextSevenDays(): LiveData<Result<List<WeatherForNextSevenDays.WeatherList>>>

    /**
     * This function will help in getting the [WeatherForNextSevenDays]
     * */
    suspend fun getWeatherDataForNextSevenDays(
        latitude: String?,
        longitude: String?,
        appId: String
    ): Result<List<WeatherForNextSevenDays.WeatherList>>

    /**
     * This function will refresh the current weather and calls the getCurrentWeather() method
     * */
    fun refreshCurrentWeather()
}