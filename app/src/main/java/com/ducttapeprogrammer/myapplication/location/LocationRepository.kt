package com.ducttapeprogrammer.myapplication.location

import androidx.lifecycle.LiveData
import com.ducttapeprogrammer.myapplication.Result
import com.ducttapeprogrammer.myapplication.data.model.Places

interface LocationRepository {
    /**
     * This function will observe any changes in [Places] and updates the changes
     * */
    fun observeAllPlaces(): LiveData<Result<List<Places>>>

    fun getAllPlaces(): Result<List<Places>>

    /**
     * This function will insert a [Places] into the room database
     * */
    suspend fun insertPlace(place: Places)

    fun refreshPlaces()


}