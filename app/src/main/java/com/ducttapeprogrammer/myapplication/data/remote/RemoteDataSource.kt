package com.ducttapeprogrammer.myapplication.data.remote

import com.ducttapeprogrammer.myapplication.data.source.AppDataSource

object RemoteDataSource : AppDataSource {

    override suspend fun getCurrentWeather(latitude: String, longitude: String, appId: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}