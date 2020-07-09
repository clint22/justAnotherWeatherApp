package com.ducttapeprogrammer.myapplication.data.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ducttapeprogrammer.myapplication.Result
import com.ducttapeprogrammer.myapplication.data.model.Places
import com.ducttapeprogrammer.myapplication.location.LocationRepository
import kotlinx.coroutines.runBlocking

/**
 * A double class for LocationRepository
 * */
class FakeLocationRepository : LocationRepository {

    private var locationServiceData: LinkedHashMap<Int, Places> = LinkedHashMap()
    private val observablePlaces = MutableLiveData<Result<List<Places>>>()


    override fun observeAllPlaces(): LiveData<Result<List<Places>>> {
        runBlocking { refreshPlaces() }
        return observablePlaces
    }

    override fun getAllPlaces(): Result<List<Places>> {
        return Result.Success(locationServiceData.values.toList())
    }

    /**
     * Function which helps to insert the new place
     * */
    override suspend fun insertPlace(place: Places) {
        locationServiceData[place.id] = place
    }

    override fun refreshPlaces() {
        observablePlaces.value = getAllPlaces()
    }

    /**
     * Function which helps to add new places easily
     * */
    fun addPlaces(vararg places: Places) {
        for (place in places) {
            locationServiceData[place.id] = place
        }
        runBlocking {
            refreshPlaces()
        }
    }
}