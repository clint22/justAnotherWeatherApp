package com.ducttapeprogrammer.myapplication.location

import androidx.lifecycle.LiveData
import com.ducttapeprogrammer.myapplication.Result
import com.ducttapeprogrammer.myapplication.data.model.Places

/**
 * This interface will provide methods for DefaultLocationRepository and FakeLocationRepository
 * */
interface LocationRepository {
    /**
     * This function will observe any changes in [Places] and updates the changes
     * */
    fun observeAllPlaces(): LiveData<Result<List<Places>>>

    /**
     * This function will return all the places in the local dB.
     * */
    fun getAllPlaces(): Result<List<Places>>

    /**
     * This function will insert a [Places] into the room database
     * */
    suspend fun insertPlace(place: Places)

    /**
     * This function will refresh the current places and calls the getAllPlaces
     * */
    fun refreshPlaces()


}