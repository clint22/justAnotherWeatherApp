
package com.ducttapeprogrammer.myapplication.data.source

import androidx.lifecycle.LiveData
import com.ducttapeprogrammer.myapplication.Result
import com.ducttapeprogrammer.myapplication.data.model.CurrentWeather
import com.ducttapeprogrammer.myapplication.data.model.WeatherForNextSevenDays

/**
 * A Fake Remote data source which inherits and implements all the methods
 * in the RemoteAppDataSource
 * */
class FakeRemoteDataSource(private var currentWeather: CurrentWeather? = null) :
    RemoteAppDataSource {

    override suspend fun getCurrentWeather(
        latitude: String?,
        longitude: String?,
        appId: String
    ): Result<CurrentWeather> {
        return try {
            Result.Success(currentWeather)
        } catch (e: KotlinNullPointerException) {
            Result.Error(Unit)
        }
    }

    override fun observeCurrentWeather(): LiveData<CurrentWeather> {
        TODO("Implement observe current weather logic")
    }

    override suspend fun getWeatherDataForNextSevenDays(
        latitude: String?,
        longitude: String?,
        appId: String
    ): Result<List<WeatherForNextSevenDays.WeatherList>> {
        TODO("Implement get weather data for next 7 days logic")
    }


    override fun observeWeatherDataForNextSevenDays(): LiveData<Result<List<WeatherForNextSevenDays.WeatherList>>> {
        TODO("Implement observe weather data for next 7 days logic")
    }

}
