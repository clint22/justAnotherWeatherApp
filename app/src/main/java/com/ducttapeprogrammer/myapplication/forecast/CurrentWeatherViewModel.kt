package com.ducttapeprogrammer.myapplication.forecast

import androidx.lifecycle.*
import com.ducttapeprogrammer.myapplication.Event
import com.ducttapeprogrammer.myapplication.Result
import com.ducttapeprogrammer.myapplication.SHARED_PREF_PERMISSIONS_GIVEN
import com.ducttapeprogrammer.myapplication.data.model.CurrentWeather
import com.ducttapeprogrammer.myapplication.data.model.WeatherForNextSevenDays
import com.ducttapeprogrammer.myapplication.utils.convertKelvinToDegreeCelsius
import com.ducttapeprogrammer.myapplication.utils.getBooleanSharedPreference
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
class CurrentWeatherViewModel(
    private val currentWeatherRepository: CurrentWeatherRepository
) :
    ViewModel() {

    /*private val fakeRemoteDataSource = FakeRemoteDataSource()
    private val currentWeatherRepository = DefaultCurrentWeatherRepository(fakeRemoteDataSource)*/

    private val _dataLoading = MutableLiveData(true)
    val dataLoading: LiveData<Boolean> = _dataLoading

    private val _lottieAnimation = MutableLiveData<Event<Boolean>>()
    val lottieAnimation: LiveData<Event<Boolean>> = _lottieAnimation

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
     * This function will get the result from
     * [DefaultCurrentWeatherRepository.getCurrentWeather] and updates the related liveData items
     * */
    fun getCurrentWeather(
        latitude: String?,
        longitude: String?,
        appId: String
    ) {

        _lottieAnimation.value = Event(false)
        _dataLoading.value = true
        viewModelScope.launch {
//            Current Weather API will be called only if the permissions are given
            if (getBooleanSharedPreference(SHARED_PREF_PERMISSIONS_GIVEN)) {
                currentWeatherRepository.getCurrentWeather(
                    latitude,
                    longitude,
                    appId
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


    }

    private fun onWeatherDataLoaded(data: CurrentWeather?) {
        currentTemperature.value = convertKelvinToDegreeCelsius(
            data?.main?.temp
        ).toString()
        weatherCondition.value = data?.weather?.get(0)?.description?.capitalize(Locale.getDefault())
        windSpeed.value = (data?.wind?.speed)?.roundToInt().toString()
        currentRegion.value = data?.name + "," + data?.sys?.country
    }

    @Suppress("UNCHECKED_CAST")
    class CurrentWeatherViewModelFactory(
        private val currentWeatherRepository: CurrentWeatherRepository
    ) : ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel> create(modelClass: Class<T>) =
            (CurrentWeatherViewModel(currentWeatherRepository) as T)
    }
}