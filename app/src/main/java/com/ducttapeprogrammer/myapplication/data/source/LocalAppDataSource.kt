package com.ducttapeprogrammer.myapplication.data.source

import androidx.lifecycle.LiveData
import com.ducttapeprogrammer.myapplication.Result
import com.ducttapeprogrammer.myapplication.data.local.PlacesAndWeatherDao
import com.ducttapeprogrammer.myapplication.data.model.Places

/**
 * This interface will provide methods for LocalDataSource and FakeLocalDataSource
 * */
interface LocalAppDataSource {

    /**
     * This function will insert the place into the Room Database
     * */
    suspend fun insertPlace(
        place: Places
    )

    /**
     * This function will observe the [Places] List
     * */
    fun observeAllPlaces(): LiveData<Result<List<Places>>>

    /**
     * This function will return all the places in the local dB
     * */
    fun getAllPlaces(): Result<List<Places>>
}