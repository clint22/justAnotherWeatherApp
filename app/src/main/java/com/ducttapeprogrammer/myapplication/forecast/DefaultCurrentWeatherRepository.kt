package com.ducttapeprogrammer.myapplication.forecast

import androidx.lifecycle.LiveData
import com.ducttapeprogrammer.myapplication.Result
import com.ducttapeprogrammer.myapplication.data.model.CurrentWeather
import com.ducttapeprogrammer.myapplication.data.model.WeatherForNextSevenDays
import com.ducttapeprogrammer.myapplication.data.remote.RemoteDataSource
import com.ducttapeprogrammer.myapplication.data.source.RemoteAppDataSource

/**
 * This class will act as a link b/w [CurrentWeatherViewModel] and [RemoteDataSource]
 * */
class DefaultCurrentWeatherRepository (
    private val remoteDataSource: RemoteAppDataSource
) : CurrentWeatherRepository {

    companion object {
        @Volatile
        private var INSTANCE: DefaultCurrentWeatherRepository? = null
        fun getCurrentWeatherRepository(): DefaultCurrentWeatherRepository {
            return INSTANCE ?: synchronized(this) {
                DefaultCurrentWeatherRepository(RemoteDataSource).also {
                    INSTANCE = it
                }
            }
        }
    }

    /**
     * This function will return the result of current weather from the [RemoteDataSource.getCurrentWeather]
     * */
    override suspend fun getCurrentWeather(
        latitude: String?,
        longitude: String?,
        appId: String
    ): Result<CurrentWeather> {
        return remoteDataSource.getCurrentWeather(latitude, longitude, appId)
    }

    /**
     * This function will observe any changes in [WeatherForNextSevenDays.WeatherList] and updates the changes
     * */
    override fun observeWeatherForNextSevenDays(): LiveData<Result<List<WeatherForNextSevenDays.WeatherList>>> =
        remoteDataSource.observeWeatherDataForNextSevenDays()

    /**
     * This function will help in getting the [WeatherForNextSevenDays]
     * */
    override suspend fun getWeatherDataForNextSevenDays(
        latitude: String?,
        longitude: String?,
        appId: String
    ): Result<List<WeatherForNextSevenDays.WeatherList>> {
        return remoteDataSource.getWeatherDataForNextSevenDays(latitude, longitude, appId)
    }

    override fun refreshCurrentWeather() {
        TODO("Not yet implemented")
    }
}