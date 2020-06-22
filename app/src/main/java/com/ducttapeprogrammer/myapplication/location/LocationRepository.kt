package com.ducttapeprogrammer.myapplication.location

import androidx.lifecycle.LiveData
import com.ducttapeprogrammer.myapplication.Result
import com.ducttapeprogrammer.myapplication.data.model.Places
import com.ducttapeprogrammer.myapplication.data.source.FakeLocalDataSource

/**
 * This class will act as a link b/w [LocationViewModel] and the underlying dB
 * */
class LocationRepository constructor(
    private val localDataSource: FakeLocalDataSource
) {

    /* private val localDataSource = LocalDataSource
     private val placesDao = AppDatabase.getDatabase(MyApplication.instance).placesDao()*/


    /**
     * This function will observe any changes in [Places] and updates the changes
     * */
    fun observeAllPlaces(): LiveData<List<Places>> = localDataSource.observeAllPlaces()

    fun getAllPlaces(): Result<List<Places>> = localDataSource.getAllPlaces()

    /**
     * This function will insert a [Places] into the room database
     * */
    suspend fun insertPlace(place: Places) {

        localDataSource.insertPlace(place)
    }

}