package com.ducttapeprogrammer.myapplication.location

import android.app.Application
import androidx.lifecycle.LiveData
import com.ducttapeprogrammer.myapplication.Result
import com.ducttapeprogrammer.myapplication.data.local.AppDatabase
import com.ducttapeprogrammer.myapplication.data.local.LocalDataSource
import com.ducttapeprogrammer.myapplication.data.model.Places
import com.ducttapeprogrammer.myapplication.data.source.LocalAppDataSource

/**
 * This class will act as a link b/w [LocationViewModel] and the underlying dB
 * */
class DefaultLocationRepository(
    private val localDataSource: LocalAppDataSource
) : LocationRepository {

    /* private val localDataSource = LocalDataSource
     private val placesDao = AppDatabase.getDatabase(MyApplication.instance).placesDao()*/

    companion object {
        private var INSTANCE: DefaultLocationRepository? = null
        fun getLocationRepository(app: Application): DefaultLocationRepository {
            return INSTANCE ?: synchronized(this) {
                val database = AppDatabase.getDatabase(app)
                DefaultLocationRepository(LocalDataSource(database.placesDao())).also {
                    INSTANCE = it
                }
            }
        }
    }

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

        localDataSource.insertPlace(place)
    }

    override fun refreshPlaces() {
        getAllPlaces()
    }

}