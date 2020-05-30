package com.ducttapeprogrammer.myapplication

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ducttapeprogrammer.myapplication.data.model.WeatherForNextSevenDays
import com.ducttapeprogrammer.myapplication.forecast.WeatherForNextSevenDaysAdapter
import com.google.gson.Gson
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

@BindingAdapter("app:currentTemperature")
fun setCurrentTemperature(textView: TextView, value: Double) {

    textView.text =
        MyApplication.instance.getString(
            R.string.degree_celsius_description,
            convertKelvinToDegreeCelsius(value).toString()
        )
}


@BindingAdapter("app:weatherIcon")
fun setWeatherIcon(
    imageView: ImageView,
    items: List<WeatherForNextSevenDays.WeatherList.WeatherMainList>
) {
    Timber.d(Gson().toJson(items))
    items.forEach { weatherMain ->
        Timber.d(weatherMain.id.toString())
        imageView.setRecyclerWeatherConditionIcon(getWeatherCondition(weatherMain.id))
    }
}

@BindingAdapter("app:date")
fun setDate(
    textView: TextView,
    items: List<WeatherForNextSevenDays.WeatherList.WeatherMainList>
) {
    Timber.d(items.size.toString())
    textView.text = getCurrentDate(1)


}
