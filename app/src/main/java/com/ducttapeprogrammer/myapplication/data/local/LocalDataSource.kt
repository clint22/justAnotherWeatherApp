package com.ducttapeprogrammer.myapplication.data.local

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.ducttapeprogrammer.myapplication.Result
import com.ducttapeprogrammer.myapplication.data.source.LocalAppDataSource
import com.ducttapeprogrammer.myapplication.data.model.Places
import com.ducttapeprogrammer.myapplication.data.source.RemoteAppDataSource
import com.google.gson.Gson
import timber.log.Timber
import java.lang.Exception

/**
 * This class will write the logic of all the functions that is Local mentioned in the [RemoteAppDataSource]
 * */
class LocalDataSource(private var placesDao: PlacesAndWeatherDao) :
    LocalAppDataSource {

    override suspend fun insertPlace(
        place: Places
    ) {
        Timber.d("insertPlaceCalled")
        Timber.d("insertPlaceLocalDataSource %s", Gson().toJson(place))
        placesDao.insertPlace(place)
    }

    override fun observeAllPlaces(): LiveData<Result<List<Places>>> {
        Timber.d("observeAllPlacesCalled")
        return placesDao.observePlaces().map {
            Timber.d("observePlacesMapCalled")
            Result.Success(it)
        }
    }

    override fun getAllPlaces(): Result<List<Places>> {
        return try {
            Result.Success(placesDao.getAllPlaces())
        } catch (e: RuntimeException) {
            Result.Error(Unit)
        }
    }


}