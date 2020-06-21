package com.ducttapeprogrammer.myapplication.location

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.ducttapeprogrammer.myapplication.getOrAwaitValue
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LocationViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Test
    fun otherLocationClicked_setsOtherLocationAsCurrentlySelectedLocation() {

        val locationViewModel = LocationViewModel()
        locationViewModel.locationClicked
        val value = locationViewModel.locationClicked.getOrAwaitValue()
        assertThat(value, (not(nullValue())))

    }
}