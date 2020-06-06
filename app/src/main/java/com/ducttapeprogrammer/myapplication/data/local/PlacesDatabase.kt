package com.ducttapeprogrammer.myapplication.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ducttapeprogrammer.myapplication.data.model.Places

// Annotates class to be a Room Database with a table (entity) of the Places class
@Database(entities = [Places::class], version = 1, exportSchema = false)
public abstract class PlacesDatabase : RoomDatabase() {

    abstract fun placesDao(): PlacesDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: PlacesDatabase? = null

        fun getDatabase(context: Context): PlacesDatabase {

            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PlacesDatabase::class.java,
                    "places_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }

}