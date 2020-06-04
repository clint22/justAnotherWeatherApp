package com.ducttapeprogrammer.myapplication.data.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ducttapeprogrammer.myapplication.*
import com.ducttapeprogrammer.myapplication.data.model.CurrentWeather
import com.ducttapeprogrammer.myapplication.data.model.WeatherForNextSevenDays
import com.ducttapeprogrammer.myapplication.data.source.AppDataSource
import com.ducttapeprogrammer.myapplication.utils.getWeatherCondition
import com.ducttapeprogrammer.myapplication.utils.setIntSharedPreference
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber
import java.io.IOException

/**
 * This class will write the logic of all the functions mentioned in the [AppDataSource]
 * */
object RemoteDataSource : AppDataSource {

    private val observeCurrentWeather = MutableLiveData<CurrentWeather>()
    private val observeWeatherForNextSevenDays =
        MutableLiveData<List<WeatherForNextSevenDays.WeatherList>>()
    private var currentWeather: CurrentWeather? = null
    private var weatherForNextSevenDays: WeatherForNextSevenDays? = null
    private var isCurrentWeatherExceptionOccurred: Boolean = false
    private var isWeatherForNextSevenDaysExceptionOccurred: Boolean = false
    override suspend fun getCurrentWeather(
        latitude: String?,
        longitude: String?,
        appId: String
    ): Result<CurrentWeather> {

        withContext(Dispatchers.IO) {

            try {
                val service = ApiFactory.retrofit().create(GetDataService::class.java)
                val call = service.getCurrentWeather(latitude, longitude, appId)
                if (call?.isSuccessful!!) {
                    call.body().let { currentWeatherData ->
                        getWeatherCondition(currentWeatherData?.weather?.get(0)?.id).setIntSharedPreference(
                            SHARED_PREF_WEATHER_CONDITION_KEY
                        )
                        currentWeather = currentWeatherData
                        observeCurrentWeather.postValue(currentWeatherData)
                        Timber.e(Gson().toJson(currentWeatherData))
                    }
                }
            } catch (exception: IOException) {
                isCurrentWeatherExceptionOccurred = true
                print(exception)
            }
        }
        return if (isCurrentWeatherExceptionOccurred) {
            Result.Error(Unit)
        } else {

            Result.Success(currentWeather)
        }
    }

    override fun observeCurrentWeather(): LiveData<CurrentWeather> {
        return observeCurrentWeather
    }

    override suspend fun getWeatherDataForNextSevenDays(
        latitude: String?,
        longitude: String?,
        appId: String
    ) {

        withContext(Dispatchers.IO) {

            try {
                val service = ApiFactory.retrofit().create(GetDataService::class.java)
                val call = service.getWeatherDataForNextSevenDays(
                    latitude,
                    longitude,
                    appId
                )
                if (call?.isSuccessful!!) {

                    call.body().let { weatherDataForNextSevenDaysData ->

                        weatherForNextSevenDays = weatherDataForNextSevenDaysData
                        observeWeatherForNextSevenDays.postValue(
                            weatherForNextSevenDays?.list?.subList(
                                WEATHER_FOR_NEXT_SEVEN_DAYS_INITIAL_RANGE,
                                (weatherForNextSevenDays?.list!!.size - 1)
                            )
                        )
                        Timber.e(Gson().toJson(weatherDataForNextSevenDaysData))
                    }
                }

            } catch (exception: IOException) {
                isWeatherForNextSevenDaysExceptionOccurred = true
                print(exception)
            }
        }

    }

    override fun observeWeatherDataForNextSevenDays(): LiveData<List<WeatherForNextSevenDays.WeatherList>> {
        return observeWeatherForNextSevenDays
    }
}