package com.ducttapeprogrammer.myapplication.data.source

import androidx.lifecycle.LiveData
import com.ducttapeprogrammer.myapplication.Result
import com.ducttapeprogrammer.myapplication.data.model.Places

class FakeLocalAppDataSource(private var places: MutableList<Places>? = mutableListOf()) :
    LocalAppDataSource {

    override suspend fun insertPlace(place: Places) {
        places?.add(place)
    }

    override fun observeAllPlaces(): LiveData<Result<List<Places>>> {
        TODO("Not yet implemented")
    }

    override fun getAllPlaces(): Result<List<Places>> {
        return try {
            Result.Success(places)
        } catch (e: Exception) {
            Result.Error(Unit)
        }
    }
}