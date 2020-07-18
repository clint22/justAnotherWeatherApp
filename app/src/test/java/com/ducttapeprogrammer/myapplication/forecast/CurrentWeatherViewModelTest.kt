package com.ducttapeprogrammer.myapplication.forecast

import android.os.Build
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.ducttapeprogrammer.myapplication.MainCoroutineRule
import com.ducttapeprogrammer.myapplication.Result
import com.ducttapeprogrammer.myapplication.data.model.CurrentWeather
import com.ducttapeprogrammer.myapplication.data.source.FakeCurrentWeatherRepository
import com.google.gson.Gson
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.IsEqual
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import timber.log.Timber

/**
 * Test double for CurrentWeatherViewModel
 * */
@ExperimentalCoroutinesApi
@ExperimentalStdlibApi
@RunWith(AndroidJUnit4::class)
@Config(sdk = [Build.VERSION_CODES.P])
class CurrentWeatherViewModelTest {

    private lateinit var fakeCurrentWeatherRepository: FakeCurrentWeatherRepository
    private lateinit var currentWeatherViewModel: CurrentWeatherViewModel

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()


    private val clouds = CurrentWeather.Clouds(
        all = 0
    )
    private val coord = CurrentWeather.Coord(
        lat = 19.12333,
        lon = 23.12323
    )
    private val main = CurrentWeather.Main(
        feelsLike = 12.23,
        humidity = 10,
        pressure = 20,
        temp = 332.23,
        tempMax = 123.45,
        tempMin = 145.23
    )
    private val sys = CurrentWeather.Sys(
        country = "IN",
        id = 10,
        sunrise = 12,
        sunset = 10,
        type = 22
    )
    private val weather = CurrentWeather.Weather(
        description = "Chilly",
        icon = "sample",
        id = 10,
        main = "main"
    )
    private val wind = CurrentWeather.Wind(
        deg = 15,
        speed = 22.34
    )
    private val currentWeather = CurrentWeather(
        base = "",
        clouds = clouds,
        cod = 0,
        coord = coord,
        dt = 0,
        weatherId = 0,
        main = main,
        name = "Kochi",
        sys = sys,
        timezone = 0,
        visibility = 0,
        weather = listOf(weather),
        wind = wind
    )

    /**
     * setup the viewModel() by passing the FakeCurrentWeatherRepository
     * */
    @Before
    fun setupViewModel() {
        fakeCurrentWeatherRepository = FakeCurrentWeatherRepository()
        fakeCurrentWeatherRepository.addCurrentWeather(currentWeather)
        currentWeatherViewModel = CurrentWeatherViewModel(fakeCurrentWeatherRepository)


    }


    /**
     * Function which tests if the getCurrentWeather is 'Success' or 'not'
     * */
    @Test
    fun getCurrentWeather_requestFromCurrentWeatherRepository() = runBlockingTest {

        val weather = currentWeatherViewModel.getCurrentWeather(
            longitude = "96.12",
            latitude = "56.88",
            appId = "1234"
        ) as Result.Success
        assertThat(weather.data, IsEqual(currentWeather))

    }

}