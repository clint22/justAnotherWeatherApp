package com.ducttapeprogrammer.myapplication.location

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ducttapeprogrammer.myapplication.data.model.Places
import kotlinx.coroutines.launch

/**
 * This class will act as a link b/w the [LocationFragment] and the [LocationRepository]
 * */
class LocationViewModel : ViewModel() {

    private val locationRepository = LocationRepository()

    private val _locationClicked = MutableLiveData<Places>()
    val locationClicked: LiveData<Places> = _locationClicked

    private val _observeAllPlaces: LiveData<List<Places>> =
        locationRepository.observeAllPlaces()
    val observeAllPlaces: LiveData<List<Places>> = _observeAllPlaces

    /**
     * This function will help in calling the insertPlace function inside
     * the repository
     * */
    fun insertPlace(place: Places) {

        viewModelScope.launch {

            locationRepository.insertPlace(place)
        }

    }

    /**
     * This function will check if the user has clicked any of the "Other Places" he has added.
     * */
    fun locationClicked(
        place: Places
    ) {
        _locationClicked.value = place
    }


}