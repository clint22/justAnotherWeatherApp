package com.ducttapeprogrammer.myapplication.utils.itemBinding

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ducttapeprogrammer.myapplication.data.model.Places
import com.ducttapeprogrammer.myapplication.location.LocationAdapter

@BindingAdapter("app:locationItems")
fun setItems(listView: RecyclerView, items: List<Places>?) {
    items?.let {
        (listView.adapter as LocationAdapter).submitList(items)
    }
}