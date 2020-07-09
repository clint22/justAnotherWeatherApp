package com.ducttapeprogrammer.myapplication.location

import android.os.Build
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.ducttapeprogrammer.myapplication.Result
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
    private val places1 = com.ducttapeprogrammer.myapplication.data.model.Places(
        1,
        32.12,
        12.23,
        "Raichur",
        "Karnataka",
        "India"
    )
    private val places2 = com.ducttapeprogrammer.myapplication.data.model.Places(
        2,
        52.12,
        11.23,
        "Kochi",
        "Kerala",
        "India"
    )
    private val places3 = com.ducttapeprogrammer.myapplication.data.model.Places(
        3,
        62.12,
        56.23,
        "Kannur",
        "Kerala",
        "India"
    )
    private val localPlaces = listOf(places1, places2, places3).sortedBy { it.id }


    /**
     * set up the viewModel by passing the fakeLocationRepository
     * */
    @Before
    fun setupViewModel() {
        fakeLocationRepository = FakeLocationRepository()
        fakeLocationRepository.addPlaces(places1, places2, places3)
        locationViewModel = LocationViewModel(fakeLocationRepository)
    }


    /**
     * Function which tests observeAllPlaces is equal to fakeLocationRepository observeAllPlaces
     * */
    @Test
    fun observePlaces_requestAllPlacesFromLocationRepository() {
        val places = locationViewModel.observeAllPlaces
        assertThat(places.value, IsEqual(fakeLocationRepository.observeAllPlaces().value))
    }


    /**
     * Function which tests getAllPlaces is equal to localPlaces ( Static )
     * */
    @Test
    fun getAllPlaces_requestAllPlacesFromLocationRepository() {
        val places = locationViewModel.getAllPlaces as Result.Success
        assertThat(places.data, IsEqual(localPlaces))
    }

}