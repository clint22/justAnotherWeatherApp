package com.ducttapeprogrammer.myapplication.location

import com.ducttapeprogrammer.myapplication.MainCoroutineRule
import com.ducttapeprogrammer.myapplication.Result
import com.ducttapeprogrammer.myapplication.data.source.FakeLocalDataSource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.IsEqual
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * A test double for LocationRepository
 * */
@ExperimentalCoroutinesApi
class LocationRepositoryTest {

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private val places1 = com.ducttapeprogrammer.myapplication.data.model.Places(
        latitude = 19.234234,
        longitude = 12.2234,
        region = "Raichur",
        state = "Karnataka",
        country = "India"
    )

    private val localPlaces = listOf(places1).sortedBy { it.id }

    private lateinit var localDataSource: FakeLocalDataSource

    //    Class under test
    private lateinit var locationRepository: DefaultLocationRepository

    /**
     * Function which creates a new repository by passing the fakeLocalDataSource
     * */
    @Before
    fun createRepository() {

        localDataSource = FakeLocalDataSource(localPlaces.toMutableList())
        locationRepository = DefaultLocationRepository(localDataSource)

    }

    /**
     * Function which tests getAllPlaces ( Success ) is equal to localPlaces ( Static data )
     * */
    @Test
    fun getPlaces_requestAllPlacesFromLocalDataSource() = mainCoroutineRule.runBlockingTest {

        val places = locationRepository.getAllPlaces() as Result.Success
        assertThat(places.data, IsEqual(localPlaces))

    }

}