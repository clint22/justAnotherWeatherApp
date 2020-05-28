package com.ducttapeprogrammer.myapplication.data.source

import androidx.lifecycle.LiveData
import com.ducttapeprogrammer.myapplication.Result
import com.ducttapeprogrammer.myapplication.data.CurrentWeather

/**
 * This interface will hold all the details about the operations in the application
 * */
interface AppDataSource {

    /**
     * This function will return the [CurrentWeather] that is wrapped in the [Result]
     * */
    suspend fun getCurrentWeather(
        latitude: String?,
        longitude: String?,
        appId: String
    ) : Result<CurrentWeather>

    /**
     * This function will observe the [CurrentWeather] response
     * */
    fun observeCurrentWeather() : LiveData<CurrentWeather>


}