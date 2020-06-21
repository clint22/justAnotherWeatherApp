package com.ducttapeprogrammer.myapplication.forecast

import androidx.lifecycle.LiveData
import com.ducttapeprogrammer.myapplication.Result
import com.ducttapeprogrammer.myapplication.data.model.CurrentWeatherRemote
import com.ducttapeprogrammer.myapplication.data.model.WeatherForNextSevenDaysRemote
import com.ducttapeprogrammer.myapplication.data.remote.RemoteDataSource

/**
 * This class will act as a link b/w [CurrentWeatherViewModel] and [RemoteDataSource]
 * */
class CurrentWeatherRepository {

    private val remoteDataSource = RemoteDataSource

    /**
     * This function will return the result of current weather from the [RemoteDataSource.getCurrentWeather]
     * */
    suspend fun getCurrentWeather(
        latitude: String?,
        longitude: String?,
        appId: String
    ): Result<CurrentWeatherRemote> {
        return remoteDataSource.getCurrentWeather(latitude, longitude, appId)

    }

    /**
     * This function will observe any changes in [WeatherForNextSevenDaysRemote.WeatherList] and updates the changes
     * */
    fun observeWeatherForNextSevenDays(): LiveData<List<WeatherForNextSevenDaysRemote.WeatherList>> =
        remoteDataSource.observeWeatherDataForNextSevenDays()

    /**
     * This function will help in getting the [WeatherForNextSevenDaysRemote]
     * */
    suspend fun getWeatherDataForNextSevenDays(
        latitude: String?,
        longitude: String?,
        appId: String
    ) {
        remoteDataSource.getWeatherDataForNextSevenDays(latitude, longitude, appId)
    }
}