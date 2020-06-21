package com.ducttapeprogrammer.myapplication.data.remote

import com.ducttapeprogrammer.myapplication.KEY_APP_ID
import com.ducttapeprogrammer.myapplication.KEY_LATITUDE
import com.ducttapeprogrammer.myapplication.KEY_LONGITUDE
import com.ducttapeprogrammer.myapplication.data.model.CurrentWeatherRemote
import com.ducttapeprogrammer.myapplication.data.model.WeatherForNextSevenDaysRemote
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * interface that holds of all the API details
 * */
interface GetDataService {

    /**
     * API for getting current weather details
     * */
    @GET("weather")
    suspend fun getCurrentWeather(
        @Query(KEY_LATITUDE) latitude: String?,
        @Query(KEY_LONGITUDE) longitude: String?,
        @Query(KEY_APP_ID) appId: String
    ): Response<CurrentWeatherRemote>?

    /**
     * API for getting weather data for the next 7 days
     * */
    @GET("forecast/daily")
    suspend fun getWeatherDataForNextSevenDays(
        @Query(KEY_LATITUDE) latitude: String?,
        @Query(KEY_LONGITUDE) longitude: String?,
        @Query(KEY_APP_ID) appId: String
    ): Response<WeatherForNextSevenDaysRemote>?

}