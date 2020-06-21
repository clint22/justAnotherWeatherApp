package com.ducttapeprogrammer.myapplication.data.source

import androidx.lifecycle.LiveData
import com.ducttapeprogrammer.myapplication.Result
import com.ducttapeprogrammer.myapplication.data.model.CurrentWeatherRemote
import com.ducttapeprogrammer.myapplication.data.model.Places
import com.ducttapeprogrammer.myapplication.data.model.WeatherForNextSevenDaysRemote

/**
 * This interface will hold all the details about the operations in the application
 * */
interface AppDataSource {

    /**
     * This function will return the [CurrentWeatherRemote] that is wrapped in the [Result]
     * */
    suspend fun getCurrentWeather(
        latitude: String?,
        longitude: String?,
        appId: String
    ): Result<CurrentWeatherRemote>

    /**
     * This function will observe the [CurrentWeatherRemote] response
     * */
    fun observeCurrentWeather(): LiveData<CurrentWeatherRemote>

    /**
     * This function will return the [WeatherForNextSevenDaysRemote] that is wrapped in the [Result]
     * */
    suspend fun getWeatherDataForNextSevenDays(
        latitude: String?,
        longitude: String?,
        appId: String
    )

    /**
     * This function will observer the [WeatherForNextSevenDaysRemote] response
     * */
    fun observeWeatherDataForNextSevenDays(): LiveData<List<WeatherForNextSevenDaysRemote.WeatherList>>

    /**
     * This function will insert the place into the Room Database
     * */
    suspend fun insertPlace(place: Places)

    /**
     * This function will observe the [Places] List
     * */
    fun observeAllPlaces(): LiveData<List<Places>>

}