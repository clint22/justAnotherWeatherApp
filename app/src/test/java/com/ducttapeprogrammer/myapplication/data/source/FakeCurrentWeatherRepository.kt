package com.ducttapeprogrammer.myapplication.data.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ducttapeprogrammer.myapplication.BuildConfig
import com.ducttapeprogrammer.myapplication.Result
import com.ducttapeprogrammer.myapplication.data.model.CurrentWeather
import com.ducttapeprogrammer.myapplication.data.model.WeatherForNextSevenDays
import com.ducttapeprogrammer.myapplication.forecast.CurrentWeatherRepository
import kotlinx.coroutines.runBlocking

class FakeCurrentWeatherRepository : CurrentWeatherRepository {

    var weatherForNextSevenDaysServiceData: LinkedHashMap<String, WeatherForNextSevenDays.WeatherList> =
        LinkedHashMap()
    private val observableWeatherForNextSevenDays =
        MutableLiveData<List<WeatherForNextSevenDays.WeatherList>>()


    override suspend fun getCurrentWeather(
        latitude: String?,
        longitude: String?,
        appId: String
    ): Result<CurrentWeather> {
        TODO("Not yet implemented")
    }

    override fun observeWeatherForNextSevenDays(): LiveData<List<WeatherForNextSevenDays.WeatherList>> {
        runBlocking {
            getWeatherDataForNextSevenDays(
                latitude = "12.2345",
                longitude = "23.1234",
                appId = BuildConfig.WEATHER_API_KEY
            )
        }
        return observableWeatherForNextSevenDays
    }

    override suspend fun getWeatherDataForNextSevenDays(
        latitude: String?,
        longitude: String?,
        appId: String
    ): Result<List<WeatherForNextSevenDays.WeatherList>> {
        return Result.Success(weatherForNextSevenDaysServiceData.values.toList())
    }

}