
package com.ducttapeprogrammer.myapplication.data.source

import androidx.lifecycle.LiveData
import com.ducttapeprogrammer.myapplication.Result
import com.ducttapeprogrammer.myapplication.data.model.Places

/**
 * A Fake Local Data Source class which is used to inherit and implement all the methods in
 * LocalAppDataSource
 * */
class FakeLocalDataSource(private var places: MutableList<Places>? = mutableListOf()) :
    LocalAppDataSource {

    override suspend fun insertPlace(
        place: Places
    ) {
        places?.add(place)
    }

    override fun getAllPlaces(): Result<List<Places>> {
        return try {
            Result.Success(places)
        } catch (e: KotlinNullPointerException) {
            Result.Error(Unit)
        }
    }

    override fun observeAllPlaces(): LiveData<Result<List<Places>>> {
        TODO("Something and all")
    }


}
