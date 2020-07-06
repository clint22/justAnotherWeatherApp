package com.ducttapeprogrammer.myapplication.location

import android.os.Build
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.ducttapeprogrammer.myapplication.data.source.FakeLocationRepository
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.IsEqual
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

/**
 * A class which contains test cases of all the operations in
 * LocationViewModel
 * */
@RunWith(AndroidJUnit4::class)
@Config(sdk = [Build.VERSION_CODES.P])
class LocationViewModelTest {

    private lateinit var fakeLocationRepository: FakeLocationRepository
    private lateinit var locationViewModel: LocationViewModel


    @Before
    fun setupViewModel() {
        fakeLocationRepository = FakeLocationRepository()
        val places1 = com.ducttapeprogrammer.myapplication.data.model.Places(
            1,
            92.12,
            15.23,
            "Raichur",
            "Karnataka",
            "India"
        )
        val places2 = com.ducttapeprogrammer.myapplication.data.model.Places(
            2,
            12.12,
            35.23,
            "Kochi",
            "Kerala",
            "India"
        )
        val places3 = com.ducttapeprogrammer.myapplication.data.model.Places(
            3,
            12.12,
            16.23,
            "Kannur",
            "Kerala",
            "India"
        )
        fakeLocationRepository.addPlaces(places1, places2, places3)
        locationViewModel = LocationViewModel(fakeLocationRepository)
    }


    @Test
    fun getPlaces_requestAllPlacesFromLocationRepository() {

        val places = locationViewModel.observeAllPlaces
        assertThat(places.value, IsEqual(fakeLocationRepository.observeAllPlaces().value))
    }

    /**
     * Function which tests whether the user clicked
     * on the other locations or not
     * */
    /*@Test
    fun otherLocationClicked_setsOtherLocationAsCurrentlySelectedLocation() {

        locationViewModel.locationClicked
        val value = locationViewModel.locationClicked.getOrAwaitValue()
        assertThat(value, (not(nullValue())))

    }*/
}