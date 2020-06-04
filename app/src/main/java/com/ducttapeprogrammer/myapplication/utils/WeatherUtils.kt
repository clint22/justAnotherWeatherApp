package com.ducttapeprogrammer.myapplication.utils

import android.graphics.drawable.Drawable
import android.os.Build
import android.widget.ImageView
import androidx.annotation.RequiresApi
import com.ducttapeprogrammer.myapplication.*

/**
 * This function will return the weather type ID providing the weatherCondition ID
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

/**
 * This function will return the current weather condition icon providing the weatherID
 * for recyclerview item
 * */
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