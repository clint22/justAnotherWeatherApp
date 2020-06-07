package com.ducttapeprogrammer.myapplication.location

import androidx.lifecycle.LiveData
import com.ducttapeprogrammer.myapplication.MyApplication
import com.ducttapeprogrammer.myapplication.data.local.PlacesDatabase
import com.ducttapeprogrammer.myapplication.data.model.Places

/**
 * This class will act as a link b/w [LocationViewModel] and the underlying dB
 * */
class LocationRepository {

    private val placesDao = PlacesDatabase.getDatabase(MyApplication.instance).placesDao()

    val allPlaces: LiveData<List<Places>> = placesDao.getPlaces()

    /**
     * This function will insert a [Places] into the room database
     * */
    suspend fun insertPlace(place: Places) {

        placesDao.insertPlace(place)
    }

}