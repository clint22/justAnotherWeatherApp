package com.ducttapeprogrammer.myapplication.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ducttapeprogrammer.myapplication.data.model.CurrentWeather
import com.ducttapeprogrammer.myapplication.data.model.Places
import com.ducttapeprogrammer.myapplication.data.model.WeatherForNextSevenDays


/** Annotates class to be a Room Database with a table (entity) of the Places class
 */
@Database(
    entities = [Places::class, CurrentWeather::class, WeatherForNextSevenDays::class],
    version = 3,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    /**
     * Links the [PlacesDao] in the [AppDatabase]
     * */
    abstract fun placesDao(): PlacesDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: AppDatabase? = null

        /**
         * This function will get the database ( by making sure only a single instance
         * of it's created )
         * */
        fun getDatabase(context: Context): AppDatabase {

            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "places_database"
                ).fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }

}