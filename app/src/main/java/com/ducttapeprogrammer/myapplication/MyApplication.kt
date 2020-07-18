package com.ducttapeprogrammer.myapplication

import android.app.Application
import android.os.Build
import com.ducttapeprogrammer.myapplication.forecast.CurrentWeatherRepository
import com.ducttapeprogrammer.myapplication.location.LocationRepository
import com.facebook.stetho.Stetho
import timber.log.Timber
import timber.log.Timber.DebugTree

/**
 * This class will extend the Application class and act as a Singleton
 * */
class MyApplication : Application() {

    val locationRepository: LocationRepository
        get() = ServiceLocator.provideLocationRepository(this)

    val currentWeatherRepository: CurrentWeatherRepository
        get() = ServiceLocator.provideCurrentWeatherRepository()

    override
    fun onCreate() {
        super.onCreate()
        instance = this
        if (!isRobolectricUnitTest()) {
            Stetho.initializeWithDefaults(instance)
        }
        if (BuildConfig.DEBUG) {
            Timber.plant(DebugTree())
        }
    }

    private fun isRobolectricUnitTest(): Boolean {
        return "robolectric" == Build.FINGERPRINT
    }

    companion object {
        lateinit var instance: MyApplication
            private set
    }
}