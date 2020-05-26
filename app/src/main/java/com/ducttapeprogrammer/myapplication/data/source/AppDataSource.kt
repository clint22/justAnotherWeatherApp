package com.ducttapeprogrammer.myapplication.data.source

interface AppDataSource {

    suspend fun getCurrentWeather(
        latitude: String,
        longitude: String,
        appId: String
    )
}