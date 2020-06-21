package com.ducttapeprogrammer.myapplication.location

import androidx.lifecycle.LiveData
import com.ducttapeprogrammer.myapplication.data.model.Places
import com.ducttapeprogrammer.myapplication.data.remote.RemoteDataSource

/**
 * This class will act as a link b/w [LocationViewModel] and the underlying dB
 * */
class LocationRepository {

    private val remoteDateSource = RemoteDataSource

    /**
     * This function will observe any changes in [Places] and updates the changes
     * */
    fun observeAllPlaces(): LiveData<List<Places>> = remoteDateSource.observeAllPlaces()

    /**
     * This function will insert a [Places] into the room database
     * */
    suspend fun insertPlace(place: Places) {

        remoteDateSource.insertPlace(place)
    }

}