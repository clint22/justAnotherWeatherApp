package com.ducttapeprogrammer.myapplication.forecast

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.ducttapeprogrammer.myapplication.data.source.FakeCurrentWeatherRepository
import com.ducttapeprogrammer.myapplication.succeeded
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalStdlibApi
@RunWith(AndroidJUnit4::class)
class CurrentWeatherViewModelTest {

    private lateinit var fakeCurrentWeatherRepository: FakeCurrentWeatherRepository
    private lateinit var currentWeatherViewModel: CurrentWeatherViewModel

    @Before
    fun setupViewModel() {

        fakeCurrentWeatherRepository = FakeCurrentWeatherRepository()
        currentWeatherViewModel = CurrentWeatherViewModel(fakeCurrentWeatherRepository)
    }

    @Test
    fun getCurrentWeather_requestFromCurrentWeatherRepository() {

        val currentWeather = currentWeatherViewModel.getCurrentWeather(
            latitude = "91.2",
            longitude = "21.3",
            appId = "1234"
        )

        assertThat(currentWeather?.succeeded, `is`(true))

    }

}