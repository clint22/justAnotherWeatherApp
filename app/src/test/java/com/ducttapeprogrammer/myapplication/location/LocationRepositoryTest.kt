package com.ducttapeprogrammer.myapplication.location

import com.ducttapeprogrammer.myapplication.Result
import com.ducttapeprogrammer.myapplication.data.source.FakeLocalDataSource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.IsEqual
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class LocationRepositoryTest {

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

    @Before
    fun createRepository() {

        localDataSource = FakeLocalDataSource(localPlaces.toMutableList())
        locationRepository = DefaultLocationRepository(localDataSource)

    }

    @Test
    fun getPlaces_requestAllPlacesFromLocalDataSource() = runBlockingTest {

        val places = locationRepository.getAllPlaces() as Result.Success
        assertThat(places.data, IsEqual(localPlaces))

    }

}