package com.ducttapeprogrammer.myapplication

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Build
import android.widget.ImageView
import androidx.annotation.RequiresApi
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.roundToInt

/**
 * This function will return weather type ID providing the weatherCondition ID
 * */
fun getWeatherCondition(weatherConditionId: Int?): Int {
    var weatherId = 0
    when (weatherConditionId) {
        in THUNDER_STORM_RANGE -> {
            weatherId = THUNDER_STORM
        }
        in DRIZZLE_RANGE -> {
            weatherId = DRIZZLE
        }
        in RAIN_RANGE -> {
            weatherId = RAIN
        }
        in SNOW_RANGE -> {
            weatherId = SNOW
        }
        in ATMOSPHERE_RANGE -> {
            weatherId = ATMOSPHERE
        }
        in CLEAR_RANGE -> {
            weatherId = CLEAR
        }
        in CLOUD_RANGE -> {
            weatherId = CLOUDS
        }
    }
    return weatherId
}

/**
 * This function will return the current weather condition icon providing the weatherID
 * */
@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
fun ImageView.setWeatherConditionIcon(weatherID: Int) {
    val drawable: Drawable?
    when (weatherID) {
        THUNDER_STORM -> {

            drawable = MyApplication.instance.resources.getDrawable(
                R.drawable.thunder_storm_svg,
                MyApplication.instance.theme
            )
            this.setImageDrawable(drawable)
        }
        DRIZZLE -> {
            drawable = MyApplication.instance.resources.getDrawable(
                R.drawable.shower_rains_svg,
                MyApplication.instance.theme
            )
            this.setImageDrawable(drawable)
        }
        RAIN -> {
            drawable = MyApplication.instance.resources.getDrawable(
                R.drawable.rain_svg,
                MyApplication.instance.theme
            )
            this.setImageDrawable(drawable)
        }

        SNOW -> {
            drawable = MyApplication.instance.resources.getDrawable(
                R.drawable.snow_svg,
                MyApplication.instance.theme
            )
            this.setImageDrawable(drawable)
        }
        ATMOSPHERE -> {
            drawable = MyApplication.instance.resources.getDrawable(
                R.drawable.mist_svg,
                MyApplication.instance.theme
            )
            this.setImageDrawable(drawable)
        }
        CLEAR -> {
            drawable = MyApplication.instance.resources.getDrawable(
                R.drawable.clear_sky_svg,
                MyApplication.instance.theme
            )
            this.setImageDrawable(drawable)
        }
        CLOUDS -> {
            drawable = MyApplication.instance.resources.getDrawable(
                R.drawable.cloudy_svg,
                MyApplication.instance.theme
            )
            this.setImageDrawable(drawable)
        }
    }


}

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
fun ImageView.setRecyclerWeatherConditionIcon(weatherID: Int) {
    val drawable: Drawable?
    when (weatherID) {
        THUNDER_STORM -> {

            drawable = MyApplication.instance.resources.getDrawable(
                R.drawable.thunder_storm_recycler_item_svg,
                MyApplication.instance.theme
            )
            this.setImageDrawable(drawable)
        }
        DRIZZLE -> {
            drawable = MyApplication.instance.resources.getDrawable(
                R.drawable.shower_rains_recyler_item_svg,
                MyApplication.instance.theme
            )
            this.setImageDrawable(drawable)
        }
        RAIN -> {
            drawable = MyApplication.instance.resources.getDrawable(
                R.drawable.rain_recycler_item_svg,
                MyApplication.instance.theme
            )
            this.setImageDrawable(drawable)
        }

        SNOW -> {
            drawable = MyApplication.instance.resources.getDrawable(
                R.drawable.snow_recycler_item_svg,
                MyApplication.instance.theme
            )
            this.setImageDrawable(drawable)
        }
        ATMOSPHERE -> {
            drawable = MyApplication.instance.resources.getDrawable(
                R.drawable.mist_recycler_item_svg,
                MyApplication.instance.theme
            )
            this.setImageDrawable(drawable)
        }
        CLEAR -> {
            drawable = MyApplication.instance.resources.getDrawable(
                R.drawable.clear_sky_recycler_item_svg,
                MyApplication.instance.theme
            )
            this.setImageDrawable(drawable)
        }
        CLOUDS -> {
            drawable = MyApplication.instance.resources.getDrawable(
                R.drawable.cloudy_recycler_item_svg,
                MyApplication.instance.theme
            )
            this.setImageDrawable(drawable)
        }
    }


}


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
 * This function will convert the kelvin to degree celsius given the input
 * */
fun convertKelvinToDegreeCelsius(kelvin: Double?): Int? {
    return (kelvin?.toInt()?.minus(KELVIN))?.roundToInt()
}

fun getCurrentDate(count: Int): String {
    val oldDate: String? = SimpleDateFormat("dd-MMMM-yyyy", Locale.getDefault()).format(Date())
    val simpleDateFormat = SimpleDateFormat("dd-MMMM-yyyy", Locale.getDefault())
    val calendar = Calendar.getInstance()
    try {
        calendar.time = simpleDateFormat.parse(oldDate)
    } catch (e: ParseException) {
        e.printStackTrace()
    }
    calendar.add(Calendar.DAY_OF_MONTH, count)
    return simpleDateFormat.format(calendar.time)
}




