package com.ducttapeprogrammer.myapplication.data.local

import androidx.lifecycle.LiveData
import com.ducttapeprogrammer.myapplication.Result
import com.ducttapeprogrammer.myapplication.data.model.CurrentWeatherRemote
import com.ducttapeprogrammer.myapplication.data.model.Places
import com.ducttapeprogrammer.myapplication.data.model.WeatherForNextSevenDaysRemote
import com.ducttapeprogrammer.myapplication.data.source.AppDataSource

object LocalDataSource : AppDataSource {

    override suspend fun getCurrentWeather(
        latitude: String?,
        longitude: String?,
        appId: String
    ): Result<CurrentWeatherRemote> {
        TODO("Not yet implemented")
    }

    override fun observeCurrentWeather(): LiveData<CurrentWeatherRemote> {
        TODO("Not yet implemented")
    }

    override suspend fun getWeatherDataForNextSevenDays(
        latitude: String?,
        longitude: String?,
        appId: String
    ) {
        TODO("Not yet implemented")
    }

    override fun observeWeatherDataForNextSevenDays(): LiveData<List<WeatherForNextSevenDaysRemote.WeatherList>> {
        TODO("Not yet implemented")
    }

    override suspend fun insertPlace(place: Places) {
        TODO("Not yet implemented")
    }

    override fun observeAllPlaces(): LiveData<List<Places>> {
        TODO("Not yet implemented")
    }

}