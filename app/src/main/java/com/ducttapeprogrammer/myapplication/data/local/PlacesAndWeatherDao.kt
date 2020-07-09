package com.ducttapeprogrammer.myapplication.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ducttapeprogrammer.myapplication.data.model.Places

/**
 * This class is responsible for instant SQL queries with less boilerplate code and manages
 * insertion and retrieval operations
 * */
@Dao
interface PlacesAndWeatherDao {

    /**
     * This Query will observe all the places in the table
     * */
    @Query("SELECT * from places_table")
    fun observePlaces(): LiveData<List<Places>>

    /**
     * This query will retrieve all the places in the table
     * */
    @Query("SELECT * from places_table")
    fun getAllPlaces(): List<Places>

    /**
     * This Query will insert a place in the places_table by REPLACING the place if it's already
     * there
     * */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPlace(places: Places)







}