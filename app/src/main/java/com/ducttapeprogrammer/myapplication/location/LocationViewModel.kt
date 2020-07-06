package com.ducttapeprogrammer.myapplication.location

import androidx.lifecycle.*
import com.ducttapeprogrammer.myapplication.Result
import com.ducttapeprogrammer.myapplication.data.model.Places
import kotlinx.coroutines.launch

/**2
 * This class will act as a link b/w the [LocationFragment] and the [DefaultLocationRepository]
 * */
class LocationViewModel(private val locationRepository: LocationRepository) : ViewModel() {

    private val _locationClicked = MutableLiveData<Places>()
    val locationClicked: LiveData<Places> = _locationClicked

    private val _observeAllPlaces: LiveData<Result<List<Places>>> =
        locationRepository.observeAllPlaces()
    val observeAllPlaces: LiveData<Result<List<Places>>> = _observeAllPlaces

    private val _getAllPlaces: Result<List<Places>> =
        locationRepository.getAllPlaces()
    val getAllPlaces: Result<List<Places>> = _getAllPlaces

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


    @Suppress("UNCHECKED_CAST")
    class LocationViewModelFactory(
        private val locationRepository: LocationRepository
    ) : ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel> create(modelClass: Class<T>) =
            (LocationViewModel(locationRepository) as T)
    }

}