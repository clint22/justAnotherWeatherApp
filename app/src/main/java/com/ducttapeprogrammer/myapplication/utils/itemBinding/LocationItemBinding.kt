package com.ducttapeprogrammer.myapplication.utils.itemBinding

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ducttapeprogrammer.myapplication.Result
import com.ducttapeprogrammer.myapplication.data.model.Places
import com.ducttapeprogrammer.myapplication.location.LocationAdapter

/**
 * [BindingAdapter]s for the [Places] list.
 */
@BindingAdapter("app:locationItems")
fun setItems(listView: RecyclerView, items: Result<List<Places>>?) {

    if (items is Result.Success) {
        (listView.adapter as LocationAdapter).submitList(items.data)
    }


    /*items?.let {
        (listView.adapter as LocationAdapter).submitList(items)
    }*/
}