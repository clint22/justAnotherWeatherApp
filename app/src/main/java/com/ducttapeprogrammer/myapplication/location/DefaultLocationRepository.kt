package com.ducttapeprogrammer.myapplication.location

import androidx.lifecycle.LiveData
import com.ducttapeprogrammer.myapplication.Result
import com.ducttapeprogrammer.myapplication.data.model.Places
import com.ducttapeprogrammer.myapplication.data.source.LocalAppDataSource
import timber.log.Timber

/**
 * This class will act as a link b/w [LocationViewModel] and the underlying dB
 * */
class DefaultLocationRepository(
    private val localDataSource: LocalAppDataSource
) : LocationRepository {
    /**
     * This function will observe any changes in [Places] and updates the changes
     * */
    override fun observeAllPlaces(): LiveData<Result<List<Places>>> =
        localDataSource.observeAllPlaces()

    override fun getAllPlaces(): Result<List<Places>> = localDataSource.getAllPlaces()

    /**
     * This function will insert a [Places] into the room database
     * */
    override suspend fun insertPlace(place: Places) {
        Timber.d("insertPlaceCalledDefaultLocationRepository")
        localDataSource.insertPlace(place)
    }

    override fun refreshPlaces() {
        getAllPlaces()
    }

}