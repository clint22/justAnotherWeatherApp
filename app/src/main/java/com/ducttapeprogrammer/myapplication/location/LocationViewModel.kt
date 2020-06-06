package com.ducttapeprogrammer.myapplication.location

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ducttapeprogrammer.myapplication.data.model.Places
import kotlinx.coroutines.launch

/**
 * This class will act as a link b/w the [LocationFragment] and the [LocationRepository]
 * */
class LocationViewModel : ViewModel() {

    private val locationRepository = LocationRepository()

    private val _getAllPlaces: LiveData<List<Places>> = locationRepository.allPlaces
    val getAllPlaces: LiveData<List<Places>> = _getAllPlaces

    fun insertPlace(place: Places) {

        viewModelScope.launch {

            locationRepository.insertPlace(place)
        }

    }


}