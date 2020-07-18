package com.ducttapeprogrammer.myapplication.data.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ducttapeprogrammer.myapplication.Result
import com.ducttapeprogrammer.myapplication.Result.Success
import com.ducttapeprogrammer.myapplication.data.model.CurrentWeather
import com.ducttapeprogrammer.myapplication.data.model.WeatherForNextSevenDays
import com.ducttapeprogrammer.myapplication.forecast.CurrentWeatherRepository
import kotlinx.coroutines.runBlocking

/**
 * This class will act as a double for CurrentWeatherRepository for unit testing.
 * */
class FakeCurrentWeatherRepository : CurrentWeatherRepository {

    private var currentWeatherServiceData: LinkedHashMap<Int, CurrentWeather> = LinkedHashMap()
    private var weatherForNextSevenDays: LinkedHashMap<Int, WeatherForNextSevenDays.WeatherList> =
        LinkedHashMap()
    private val observableWeatherForNextSevenDays =
        MutableLiveData<Result<List<WeatherForNextSevenDays.WeatherList>>>()
    private val observableCurrentWeather =
        MutableLiveData<Result<CurrentWeather>>()


    override suspend fun getCurrentWeather(
        latitude: String?,
        longitude: String?,
        appId: String
    ): Result<CurrentWeather> {
        currentWeatherServiceData[0].let {
            return Success(it)
        }
    }

    override fun observeWeatherForNextSevenDays(): LiveData<Result<List<WeatherForNextSevenDays.WeatherList>>> {
        return observableWeatherForNextSevenDays
    }

    override suspend fun getWeatherDataForNextSevenDays(
        latitude: String?,
        longitude: String?,
        appId: String
    ): Result<List<WeatherForNextSevenDays.WeatherList>> {
        return Success(weatherForNextSevenDays.values.toList())
    }

    override fun refreshCurrentWeather() {
        observableCurrentWeather.value = runBlocking {
            getCurrentWeather(
                latitude = "91.23",
                longitude = "12.34",
                appId = "1234"
            )
        }
    }


    /**
     * This function will help in adding new weather data easily
     * */
    fun addCurrentWeather(vararg currentWeathers: CurrentWeather) {
        for (currentWeather in currentWeathers) {
            currentWeatherServiceData[currentWeather.weatherId] = currentWeather
        }
        runBlocking {
            refreshCurrentWeather()
        }
    }
}