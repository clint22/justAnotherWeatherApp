package com.ducttapeprogrammer.myapplication

import android.app.Application
import timber.log.Timber
import timber.log.Timber.DebugTree

/**
 * This class will extend the Application class and act as a Singleton
 * */
class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
        if (BuildConfig.DEBUG) {
            Timber.plant(DebugTree())
        }
    }

    companion object {
        lateinit var instance: MyApplication
            private set
    }
}