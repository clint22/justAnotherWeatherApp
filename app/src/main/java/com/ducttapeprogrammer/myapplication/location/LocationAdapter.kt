package com.ducttapeprogrammer.myapplication.location

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ducttapeprogrammer.myapplication.data.model.Places
import com.ducttapeprogrammer.myapplication.databinding.LocationRecyclerItemBinding
import timber.log.Timber


class LocationAdapter(private val viewModel: LocationViewModel?) :
    androidx.recyclerview.widget.ListAdapter<Places, LocationAdapter.ViewHolder>(
        LocationDiffCallback()
    ) {
    class ViewHolder private constructor(private val binding: LocationRecyclerItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(
            viewModel: LocationViewModel?,
            itemLocationList: Places
        ) {
            Timber.d("locationAdapterCalled")
            binding.viewModel = viewModel
            binding.places = itemLocationList
            binding.executePendingBindings()
        }

        companion object {
            /**
             * This function will inflate the layout and helps in view binding
             * */
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding =
                    LocationRecyclerItemBinding.inflate(layoutInflater, parent, false)

                return ViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val itemWeatherList = getItem(position)
        holder.bind(viewModel, itemWeatherList)
    }


}

class LocationDiffCallback :
    DiffUtil.ItemCallback<Places>() {

    override fun areItemsTheSame(
        oldItem: Places,
        newItem: Places
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: Places,
        newItem: Places
    ): Boolean {

        return oldItem == newItem
    }

}

