package com.ducttapeprogrammer.myapplication

import android.annotation.SuppressLint
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ducttapeprogrammer.myapplication.data.model.WeatherForNextSevenDays
import com.ducttapeprogrammer.myapplication.forecast.WeatherForNextSevenDaysAdapter
import timber.log.Timber


/**
 * [BindingAdapter]s for the [WeatherForNextSevenDays.WeatherList]s list.
 */
@ExperimentalStdlibApi
@BindingAdapter("app:items")
fun setItems(listView: RecyclerView, items: List<WeatherForNextSevenDays.WeatherList>?) {
    items?.let {
        (listView.adapter as WeatherForNextSevenDaysAdapter).submitList(items)
    }
}

/**
 * This function will help in binding the current temperature by taking the [TextView]
 * and Min and Max temperature
 * */
@SuppressLint("StringFormatMatches")
@BindingAdapter("app:currentTemperature")
fun setCurrentTemperature(textView: TextView, value: WeatherForNextSevenDays.WeatherList.Temp) {

    textView.text =
        MyApplication.instance.getString(
            R.string.degree_celsius_max_min_description,
            convertKelvinToDegreeCelsius(value.max).toString(),
            convertKelvinToDegreeCelsius(value.min).toString()
        )
}

/**
 * This function will help in binding the weather icon for the next 5 days
 * */
@BindingAdapter("app:weatherIcon")
fun setWeatherIcon(
    imageView: ImageView,
    items: List<WeatherForNextSevenDays.WeatherList.WeatherMainList>
) {
    items.forEach { weatherMain ->
        Timber.d(weatherMain.id.toString())
        imageView.setRecyclerWeatherConditionIcon(getWeatherCondition(weatherMain.id))
    }
}

/**
 * This function will help in binding the date for the next 5 days
 * */
@BindingAdapter("app:date")
fun setDate(
    textView: TextView,
    items: List<WeatherForNextSevenDays.WeatherList.WeatherMainList>
) {
    textView.text = getCurrentDate()


}
