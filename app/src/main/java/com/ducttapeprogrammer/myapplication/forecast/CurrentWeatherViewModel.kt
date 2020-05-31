package com.ducttapeprogrammer.myapplication.forecast

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ducttapeprogrammer.myapplication.Event
import com.ducttapeprogrammer.myapplication.Result
import com.ducttapeprogrammer.myapplication.convertKelvinToDegreeCelsius
import com.ducttapeprogrammer.myapplication.data.model.CurrentWeather
import com.ducttapeprogrammer.myapplication.data.model.WeatherForNextSevenDays
import kotlinx.coroutines.launch
import java.util.*
import kotlin.math.roundToInt

/**
 * An annotation which makes sure 'capitalize(Locale.getDefault()' works perfectly with no issues
 * */
@ExperimentalStdlibApi
/**
 * This class will act as the link b/w UI and the repository
 * */
class CurrentWeatherViewModel : ViewModel() {

    private val currentWeatherRepository = CurrentWeatherRepository()

    private val _dataLoading = MutableLiveData<Boolean>(true)
    val dataLoading: LiveData<Boolean> = _dataLoading

    private val _lottieAnimation = MutableLiveData<Event<Boolean>>()
    val lottieAnimation: LiveData<Event<Boolean>> = _lottieAnimation

    /*private val _observeCurrentWeather: LiveData<CurrentWeather> =
        currentWeatherRepository.observeCurrentWeather()

    val observeCurrentWeather: LiveData<CurrentWeather> = _observeCurrentWeather*/

    private val _observeWeatherForNextSevenDays: LiveData<List<WeatherForNextSevenDays.WeatherList>> =
        currentWeatherRepository.observeWeatherForNextSevenDays()

    val observeWeatherForNextSevenDays: LiveData<List<WeatherForNextSevenDays.WeatherList>> =
        _observeWeatherForNextSevenDays

    // Two-way databinding, exposing MutableLiveData
    val currentTemperature = MutableLiveData<String>()
    val weatherCondition = MutableLiveData<String>()
    val windSpeed = MutableLiveData<String>()
    val currentRegion = MutableLiveData<String>()

    /**
     * This function will get the result from [CurrentWeatherRepository.getCurrentWeather] and updates the related liveData items
     * */
    fun getCurrentWeather(
        latitude: String?,
        longitude: String?,
        appId: String
    ) {
        _lottieAnimation.value = Event(false)
        _dataLoading.value = true

        viewModelScope.launch {
            currentWeatherRepository.getCurrentWeather(
                latitude, longitude, appId
            ).let {
                if (it is Result.Success) {
                    onWeatherDataLoaded(it.data)
                    _lottieAnimation.value = Event(true)
                    currentWeatherRepository.getWeatherDataForNextSevenDays(
                        latitude,
                        longitude,
                        appId
                    )
                    _dataLoading.value = false
                }

            }
        }


    }

    private fun onWeatherDataLoaded(data: CurrentWeather?) {
        currentTemperature.value = convertKelvinToDegreeCelsius(data?.main?.temp).toString()
        weatherCondition.value = data?.weather?.get(0)?.description?.capitalize(Locale.getDefault())
        windSpeed.value = (data?.wind?.speed)?.roundToInt().toString()
        currentRegion.value = data?.name + "," + data?.sys?.country
    }


}