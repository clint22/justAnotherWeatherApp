package com.ducttapeprogrammer.myapplication.data.remote

import com.ducttapeprogrammer.myapplication.KEY_APP_ID
import com.ducttapeprogrammer.myapplication.KEY_LATITUDE
import com.ducttapeprogrammer.myapplication.KEY_LONGITUDE
import retrofit2.http.GET
import retrofit2.http.Query

interface GetDataService {

    @GET("weather")
    suspend fun getCurrentWeather(
        @Query(KEY_LATITUDE) latitude: String,
        @Query(KEY_LONGITUDE) longitude: String,
        @Query(KEY_APP_ID) appId: String
    )

}