package com.ducttapeprogrammer.myapplication.data.local

import androidx.lifecycle.LiveData
import com.ducttapeprogrammer.myapplication.Result
import com.ducttapeprogrammer.myapplication.data.model.CurrentWeatherRemote
import com.ducttapeprogrammer.myapplication.data.model.Places
import com.ducttapeprogrammer.myapplication.data.model.WeatherForNextSevenDaysRemote
import com.ducttapeprogrammer.myapplication.data.source.AppDataSource

/**
 * This class will write the logic of all the functions that is Local mentioned in the [AppDataSource]
 * */
object LocalDataSource : AppDataSource {

    override suspend fun getCurrentWeather(
        latitude: String?,
        longitude: String?,
        appId: String
    ): Result<CurrentWeatherRemote> {
        TODO("Implement CurrentWeather logic here")
    }

    override fun observeCurrentWeather(): LiveData<CurrentWeatherRemote> {
        TODO("Implement Observe CurrentWeather logic here")
    }

    override suspend fun getWeatherDataForNextSevenDays(
        latitude: String?,
        longitude: String?,
        appId: String
    ) {
        TODO("Implement WeatherForNextSeven Days logic here")
    }

    override fun observeWeatherDataForNextSevenDays(): LiveData<List<WeatherForNextSevenDaysRemote.WeatherList>> {
        TODO("Implement Observe WeatherForNextSeven Days logic here")
    }

    override suspend fun insertPlace(place: Places) {
        TODO("Implement Insert place logic here")
    }

    override fun observeAllPlaces(): LiveData<List<Places>> {
        TODO("Implement Observe Places logic here")
    }

}