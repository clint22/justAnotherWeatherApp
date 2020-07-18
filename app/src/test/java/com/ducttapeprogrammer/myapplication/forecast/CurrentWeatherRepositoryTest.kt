package com.ducttapeprogrammer.myapplication.forecast

import com.ducttapeprogrammer.myapplication.MainCoroutineRule
import com.ducttapeprogrammer.myapplication.Result
import com.ducttapeprogrammer.myapplication.data.model.CurrentWeather
import com.ducttapeprogrammer.myapplication.data.source.FakeRemoteDataSource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.IsEqual
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * A double class for WeatherRepository
 * */
@ExperimentalCoroutinesApi
class CurrentWeatherRepositoryTest {

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
    private lateinit var remoteDataSource: FakeRemoteDataSource

    //    Class under test
    private lateinit var currentWeatherRepository: DefaultCurrentWeatherRepository

    /**
     * Creating the current weather repository by passing the fake remote data source
     * */
    @Before
    fun createRepository() {

        remoteDataSource =
            FakeRemoteDataSource(
                currentWeather
            )
        currentWeatherRepository =
            DefaultCurrentWeatherRepository(
                remoteDataSource
            )
    }
    /**
     * Function which asserts if result from getCurrentWeather is equal to currentWeather( Static data )
     * */
    @ExperimentalCoroutinesApi
    @Test
    fun getCurrentWeather_requestCurrentWeatherFromRemoteDataSource() = mainCoroutineRule.runBlockingTest {

        val weather = currentWeatherRepository.getCurrentWeather(
            latitude = "15.234",
            longitude = "12.345",
            appId = "23"
        ) as Result.Success

        assertThat(weather.data, IsEqual(currentWeather))
    }

}