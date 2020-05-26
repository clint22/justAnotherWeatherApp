package com.ducttapeprogrammer.myapplication.forecast

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ducttapeprogrammer.myapplication.Event
import com.ducttapeprogrammer.myapplication.Result
import com.ducttapeprogrammer.myapplication.convertKelvinToDegreeCelsius
import com.ducttapeprogrammer.myapplication.data.CurrentWeather
import kotlinx.coroutines.launch
import java.util.*
import kotlin.math.roundToInt

@ExperimentalStdlibApi
class CurrentWeatherViewModel : ViewModel() {

    private val currentWeatherRepository = CurrentWeatherRepository()

    private val _dataLoading = MutableLiveData<Boolean>(true)
    val dataLoading: LiveData<Boolean> = _dataLoading

    private val _lottieAnimation = MutableLiveData<Event<Boolean>>()
    val lottieAnimation: LiveData<Event<Boolean>> = _lottieAnimation

    private val _observeCurrentWeather: LiveData<CurrentWeather> =
        currentWeatherRepository.observeCurrentWeather()

    val observeCurrentWeather: LiveData<CurrentWeather> = _observeCurrentWeather

    // Two-way databinding, exposing MutableLiveData
    val currentTemperature = MutableLiveData<String>()
    val weatherCondition = MutableLiveData<String>()
    val windSpeed = MutableLiveData<String>()


    fun getCurrentWeather(
        latitude: String,
        longitude: String,
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
                    _dataLoading.value = false
                    _lottieAnimation.value = Event(true)
                }

            }
        }


    }

    private fun onWeatherDataLoaded(data: CurrentWeather?) {
        currentTemperature.value = convertKelvinToDegreeCelsius(data?.main?.temp)
        weatherCondition.value = data?.weather?.get(0)?.description?.capitalize(Locale.getDefault())
        windSpeed.value = (data?.wind?.speed)?.roundToInt().toString()
    }


}