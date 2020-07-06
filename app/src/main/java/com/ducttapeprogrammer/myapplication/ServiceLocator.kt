package com.ducttapeprogrammer.myapplication

import android.content.Context
import androidx.annotation.VisibleForTesting
import com.ducttapeprogrammer.myapplication.data.local.AppDatabase
import com.ducttapeprogrammer.myapplication.data.local.LocalDataSource
import com.ducttapeprogrammer.myapplication.data.remote.RemoteDataSource
import com.ducttapeprogrammer.myapplication.data.source.LocalAppDataSource
import com.ducttapeprogrammer.myapplication.forecast.CurrentWeatherRepository
import com.ducttapeprogrammer.myapplication.forecast.DefaultCurrentWeatherRepository
import com.ducttapeprogrammer.myapplication.location.DefaultLocationRepository
import com.ducttapeprogrammer.myapplication.location.LocationRepository

object ServiceLocator {
    private var database: AppDatabase? = null

    @Volatile
    var locationRepository: LocationRepository? = null
        @VisibleForTesting set
    private var currentWeatherRepository: CurrentWeatherRepository? = null


    fun provideCurrentWeatherRepository(): CurrentWeatherRepository {
        synchronized(this) {
            return currentWeatherRepository ?: createCurrentWeatherRepository()
        }
    }

    private fun createCurrentWeatherRepository(): CurrentWeatherRepository {
        val newRepo = DefaultCurrentWeatherRepository(RemoteDataSource)
        currentWeatherRepository = newRepo
        return newRepo
    }

    fun provideLocationRepository(context: Context): LocationRepository {
        synchronized(this) {
            return locationRepository ?: createLocationRepository(context)
        }
    }

    private fun createLocationRepository(context: Context): LocationRepository {
        val newRepo = DefaultLocationRepository(createPlacesLocalDataSource(context))
        locationRepository = newRepo
        return newRepo
    }

    private fun createPlacesLocalDataSource(context: Context): LocalAppDataSource {
        val database = database ?: createDatabase(context)
        return LocalDataSource(database.placesDao())
    }

    private fun createDatabase(context: Context): AppDatabase {
        val result = AppDatabase.getDatabase(context)
        database = result
        return result
    }

    @VisibleForTesting
    fun resetLocationRepository() {
//        Clear all data to avoid test pollution
        database?.apply {
            clearAllTables()
            close()
        }
        database = null
        locationRepository = null
    }

}