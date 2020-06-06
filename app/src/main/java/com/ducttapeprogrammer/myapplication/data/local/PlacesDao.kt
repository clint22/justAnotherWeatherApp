package com.ducttapeprogrammer.myapplication.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ducttapeprogrammer.myapplication.data.model.Places

@Dao
interface PlacesDao {

    @Query("SELECT * from places_table ORDER BY id ASC")
    fun getPlaces(): LiveData<List<Places>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPlace(places: Places)


}