package com.ducttapeprogrammer.myapplication.utils

import android.content.Context
import com.ducttapeprogrammer.myapplication.MyApplication
import com.ducttapeprogrammer.myapplication.R

/**
 * Set a SharedPreference for a Integer value
 */
fun Int.setIntSharedPreference(sharedPrefKey: String) {

    val preference = MyApplication.instance.applicationContext.getSharedPreferences(
        MyApplication.instance.getString(R.string.app_name), Context.MODE_PRIVATE
    )
    val editor = preference.edit()
    editor.putInt(sharedPrefKey, this)
    editor.apply()

}

/**
 * Get a SharedPreference for a Integer value
 */
fun getIntSharedPreference(sharedPrefKey: String): Int {
    val preference = MyApplication.instance.applicationContext.getSharedPreferences(
        MyApplication.instance.getString(R.string.app_name), Context.MODE_PRIVATE
    )
    return preference.getInt(sharedPrefKey, 0)
}

/**
 * Set a SharedPreference for a String value
 */
fun String.setStringSharedPreference(sharedPrefKey: String) {

    val preference = MyApplication.instance.applicationContext.getSharedPreferences(
        MyApplication.instance.getString(R.string.app_name), Context.MODE_PRIVATE
    )
    val editor = preference.edit()
    editor.putString(sharedPrefKey, this)
    editor.apply()

}

/**
 * Get a SharedPreference for a Integer value
 */
fun getStringSharedPreference(sharedPrefKey: String): String? {
    val preference = MyApplication.instance.applicationContext.getSharedPreferences(
        MyApplication.instance.getString(R.string.app_name), Context.MODE_PRIVATE
    )
    return preference.getString(sharedPrefKey, "")
}

/**
 * Set a SharedPreference for a boolean value
 */
fun Boolean.setBooleanSharedPreference(sharedPrefKey: String) {

    val preference = MyApplication.instance.applicationContext.getSharedPreferences(
        MyApplication.instance.getString(R.string.app_name), Context.MODE_PRIVATE
    )
    val editor = preference.edit()
    editor.putBoolean(sharedPrefKey, this)
    editor.apply()

}

/**
 * Get a SharedPreference for a Boolean value
 */
fun getBooleanSharedPreference(sharedPrefKey: String): Boolean {
    val preference = MyApplication.instance.applicationContext.getSharedPreferences(
        MyApplication.instance.getString(R.string.app_name), Context.MODE_PRIVATE
    )
    return preference.getBoolean(sharedPrefKey, false)
}