package com.ducttapeprogrammer.myapplication

import android.widget.TextView
import androidx.databinding.BindingAdapter


@BindingAdapter("app:weatherItem")
fun setItem(view: TextView, item: String) {
    item.let {
        view.text = MyApplication.instance.getString(R.string.degree_celsius_description, item)
    }
}

/*/**
 * [BindingAdapter]s for the [PatientDetails.Response.PatientDetail]s list.
 */
@BindingAdapter("app:items")
fun setItems(listView: RecyclerView, items: List<PatientDetails.Response.PatientDetail>?) {
    items?.let {
        (listView.adapter as PatientListAdapterTwo).submitList(items)
    }
}*/