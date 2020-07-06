package com.ducttapeprogrammer.myapplication.utils.itemBinding

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ducttapeprogrammer.myapplication.Result
import com.ducttapeprogrammer.myapplication.data.model.Places
import com.ducttapeprogrammer.myapplication.location.LocationAdapter
import timber.log.Timber

/**
 * [BindingAdapter]s for the [Places] list.
 */
@BindingAdapter("app:locationItems")
fun setItems(listView: RecyclerView, items: Result<List<Places>>?) {
    Timber.d("setItemsCalled")
    if (items is Result.Success) {
        Timber.d("setItemsSuccessCalled")
        (listView.adapter as LocationAdapter).submitList(items.data)
    } else {
        Timber.d("setItemsErrorCalled")
    }


    /*items?.let {
        (listView.adapter as LocationAdapter).submitList(items)
    }*/
}