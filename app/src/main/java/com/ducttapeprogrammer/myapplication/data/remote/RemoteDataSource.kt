package com.ducttapeprogrammer.myapplication.data.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ducttapeprogrammer.myapplication.MyApplication
import com.ducttapeprogrammer.myapplication.Result
import com.ducttapeprogrammer.myapplication.SHARED_PREF_WEATHER_CONDITION_KEY
import com.ducttapeprogrammer.myapplication.WEATHER_FOR_NEXT_SEVEN_DAYS_INITIAL_RANGE
import com.ducttapeprogrammer.myapplication.data.local.AppDatabase
import com.ducttapeprogrammer.myapplication.data.model.CurrentWeatherRemote
import com.ducttapeprogrammer.myapplication.data.model.Places
import com.ducttapeprogrammer.myapplication.data.model.WeatherForNextSevenDaysRemote
import com.ducttapeprogrammer.myapplication.data.source.AppDataSource
import com.ducttapeprogrammer.myapplication.utils.getWeatherCondition
import com.ducttapeprogrammer.myapplication.utils.setIntSharedPreference
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber
import java.io.IOException

/**
 * This class will write the logic of all the functions that is Remote mentioned in the [AppDataSource]
 * */
object RemoteDataSource : AppDataSource {

    private val observeCurrentWeather = MutableLiveData<CurrentWeatherRemote>()
    private val observeWeatherForNextSevenDays =
        MutableLiveData<List<WeatherForNextSevenDaysRemote.WeatherList>>()
    private var currentWeather: CurrentWeatherRemote? = null
    private var weatherForNextSevenDays: WeatherForNextSevenDaysRemote? = null
    private var isCurrentWeatherExceptionOccurred: Boolean = false
    private var isWeatherForNextSevenDaysExceptionOccurred: Boolean = false
    private val placesDao = AppDatabase.getDatabase(MyApplication.instance).placesDao()

    override suspend fun getCurrentWeather(
        latitude: String?,
        longitude: String?,
        appId: String
    ): Result<CurrentWeatherRemote> {

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

    override fun observeCurrentWeather(): LiveData<CurrentWeatherRemote> {
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

    override fun observeWeatherDataForNextSevenDays(): LiveData<List<WeatherForNextSevenDaysRemote.WeatherList>> {
        return observeWeatherForNextSevenDays
    }

    override suspend fun insertPlace(place: Places) {
        placesDao.insertPlace(place)
    }

    override fun observeAllPlaces(): LiveData<List<Places>> {
        return placesDao.getPlaces()
    }

}