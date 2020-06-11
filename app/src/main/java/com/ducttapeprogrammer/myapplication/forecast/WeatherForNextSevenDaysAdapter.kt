package com.ducttapeprogrammer.myapplication.forecast

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ducttapeprogrammer.myapplication.data.model.WeatherForNextSevenDays
import com.ducttapeprogrammer.myapplication.databinding.WeeklyWeatherRecyclerItemBinding


/**
 * Adapter for the weather for next 7 days list. Has a reference to the [CurrentWeatherViewModel] to send actions back to it.
 */
@ExperimentalStdlibApi
class WeatherForNextSevenDaysAdapter(private val viewModel: CurrentWeatherViewModel?) :
    ListAdapter<WeatherForNextSevenDays.WeatherList, WeatherForNextSevenDaysAdapter.ViewHolder>(
        WeatherForNextSevenDaysDiffCallback()
    ) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val itemWeatherList = getItem(position)
        holder.bind(viewModel, itemWeatherList)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    /**
     * ViewHolder class which returns the bound view items
     * */
    class ViewHolder private constructor(private val binding: WeeklyWeatherRecyclerItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        /**
         * This function will binds
         * the [CurrentWeatherViewModel] and [WeatherForNextSevenDays.WeatherList]
         * */
        fun bind(
            viewModel: CurrentWeatherViewModel?,
            itemWeatherList: WeatherForNextSevenDays.WeatherList
        ) {

            binding.viewModel = viewModel
            binding.weatherForNextSevenDays = itemWeatherList
            binding.executePendingBindings()
        }

        companion object {
            /**
             * This function will inflate the layout and helps in view binding
             * */
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding =
                    WeeklyWeatherRecyclerItemBinding.inflate(layoutInflater, parent, false)

                return ViewHolder(binding)
            }
        }
    }
}

/**
 * Callback for calculating the diff between two non-null items in a list.
 *
 * Used by ListAdapter to calculate the minimum number of changes between and old list and a new
 * list that's been passed to `submitList`.
 */
class WeatherForNextSevenDaysDiffCallback :
    DiffUtil.ItemCallback<WeatherForNextSevenDays.WeatherList>() {
    override fun areItemsTheSame(
        oldItem: WeatherForNextSevenDays.WeatherList,
        newItem: WeatherForNextSevenDays.WeatherList
    ): Boolean {
        return oldItem.dt == newItem.dt
    }

    override fun areContentsTheSame(
        oldItem: WeatherForNextSevenDays.WeatherList,
        newItem: WeatherForNextSevenDays.WeatherList
    ): Boolean {
        return oldItem == newItem
    }
}