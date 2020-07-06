package com.ducttapeprogrammer.myapplication.location

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.ducttapeprogrammer.myapplication.R
import com.ducttapeprogrammer.myapplication.ServiceLocator
import com.ducttapeprogrammer.myapplication.data.model.Places
import com.ducttapeprogrammer.myapplication.data.source.FakeAndroidLocationRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@MediumTest
@RunWith(AndroidJUnit4::class)
class LocationFragmentTest {

    private lateinit var locationRepository: LocationRepository

    @Before
    fun initRepository() {
        locationRepository = FakeAndroidLocationRepository()
        ServiceLocator.locationRepository = locationRepository
    }

    @After
    fun cleanUpDb() {
        ServiceLocator.resetLocationRepository()
    }

    @Test
    fun currentLocation_DisplayedInUI() = runBlockingTest {
        val places = Places(
            id = 1,
            latitude = 91.2,
            longitude = 93.2,
            region = "Raichur",
            state = "Karnataka",
            country = "India"
        )
        locationRepository.insertPlace(places)
        launchFragmentInContainer<LocationFragment>(null, R.style.AppTheme)
        Thread.sleep(5000)
    }

}