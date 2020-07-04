package com.ducttapeprogrammer.myapplication.data.source

import androidx.lifecycle.LiveData
import com.ducttapeprogrammer.myapplication.Result
import com.ducttapeprogrammer.myapplication.data.model.CurrentWeather
import com.ducttapeprogrammer.myapplication.data.model.WeatherForNextSevenDays

class FakeRemoteDataSource(private var currentWeather: CurrentWeather? = null) : RemoteAppDataSource {

    override suspend fun getCurrentWeather(
        latitude: String?,
        longitude: String?,
        appId: String
    ): Result<CurrentWeather> {
        return try {
            Result.Success(currentWeather)
        } catch (e: Exception) {
            Result.Error(Unit)
        }
    }

    override fun observeCurrentWeather(): LiveData<CurrentWeather> {
        TODO("Not yet implemented")
    }

    override suspend fun getWeatherDataForNextSevenDays(
        latitude: String?,
        longitude: String?,
        appId: String
    ): Result<List<WeatherForNextSevenDays.WeatherList>> {
        TODO("Not yet implemented")
    }


    override fun observeWeatherDataForNextSevenDays(): LiveData<List<WeatherForNextSevenDays.WeatherList>> {
        TODO("Not yet implemented")
    }

}