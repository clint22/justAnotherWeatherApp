package com.ducttapeprogrammer.myapplication.data.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ducttapeprogrammer.myapplication.Result
import com.ducttapeprogrammer.myapplication.SHARED_PREF_WEATHER_CONDITION_KEY
import com.ducttapeprogrammer.myapplication.data.CurrentWeather
import com.ducttapeprogrammer.myapplication.data.source.AppDataSource
import com.ducttapeprogrammer.myapplication.getWeatherCondition
import com.ducttapeprogrammer.myapplication.setIntSharedPreference
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
    private var currentWeather: CurrentWeather? = null
    private var isExceptionOccurred: Boolean = false
    override suspend fun getCurrentWeather(
        latitude: String,
        longitude: String,
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
                isExceptionOccurred = true
                print(exception)
            }
        }
        if (isExceptionOccurred) {
            return Result.Error(Unit)
        } else {

            return Result.Success(currentWeather)
        }
    }

    override fun observeCurrentWeather(): LiveData<CurrentWeather> {
        return observeCurrentWeather
    }
}