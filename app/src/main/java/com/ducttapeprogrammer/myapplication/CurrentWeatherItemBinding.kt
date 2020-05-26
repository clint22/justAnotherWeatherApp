package com.ducttapeprogrammer.myapplication

import android.widget.TextView
import androidx.databinding.BindingAdapter


/**
 * Binding adapter is for the current weather item
 * */
@BindingAdapter("app:weatherItem")
fun setItem(view: TextView, item: String) {
    item.let {
        view.text = MyApplication.instance.getString(R.string.degree_celsius_description, item)
    }
}
