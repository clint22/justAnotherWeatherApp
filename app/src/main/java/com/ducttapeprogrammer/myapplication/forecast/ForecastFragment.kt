package com.ducttapeprogrammer.myapplication.forecast

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.ducttapeprogrammer.myapplication.SNOW
import com.ducttapeprogrammer.myapplication.databinding.FragmentForecastBinding
import com.ducttapeprogrammer.myapplication.getWeatherConditionIcon

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
class ForecastFragment : Fragment() {
    private lateinit var binding: FragmentForecastBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentForecastBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setWeatherConditionIcon()

    }

    private fun setWeatherConditionIcon() {

        binding.imageViewWeatherCondition.getWeatherConditionIcon(SNOW)
    }
}
