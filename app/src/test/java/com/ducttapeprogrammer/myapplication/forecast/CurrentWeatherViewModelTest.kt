package com.ducttapeprogrammer.myapplication.forecast

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.ducttapeprogrammer.myapplication.data.source.FakeCurrentWeatherRepository
import com.ducttapeprogrammer.myapplication.getOrAwaitValue
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.not
import org.hamcrest.CoreMatchers.nullValue
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalStdlibApi
class CurrentWeatherViewModelTest {

    private lateinit var currentWeatherRepository: FakeCurrentWeatherRepository
    private lateinit var currentWeatherViewModel: CurrentWeatherViewModel

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setupViewModel() {
        currentWeatherRepository = FakeCurrentWeatherRepository()
        currentWeatherViewModel = CurrentWeatherViewModel(currentWeatherRepository)
    }

    @Test
    fun getWeatherDataForNextSevenDays_requestWeatherDataForNextSevenDays() = runBlocking {

        currentWeatherViewModel.getCurrentWeather(
            latitude = "12.345",
            longitude = "12.34",
            appId = "1234"
        )
        val value = currentWeatherViewModel.currentRegion.getOrAwaitValue()
        assertThat(value, (not(nullValue())))
    }


}