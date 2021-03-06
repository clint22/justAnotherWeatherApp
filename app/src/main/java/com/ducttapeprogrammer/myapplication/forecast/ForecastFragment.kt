package com.ducttapeprogrammer.myapplication.forecast

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.ducttapeprogrammer.myapplication.*
import com.ducttapeprogrammer.myapplication.databinding.FragmentForecastBinding
import com.ducttapeprogrammer.myapplication.utils.getIntSharedPreference
import com.ducttapeprogrammer.myapplication.utils.getStringSharedPreference
import com.ducttapeprogrammer.myapplication.utils.setWeatherConditionIcon
import timber.log.Timber

/**
 * This fragment acts as the UI for showing weather related information
 * */
@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
@ExperimentalStdlibApi
class ForecastFragment : Fragment() {
    private val currentWeatherViewModel by viewModels<CurrentWeatherViewModel> {
        CurrentWeatherViewModel.CurrentWeatherViewModelFactory(
            (requireActivity().applicationContext as MyApplication).currentWeatherRepository
        )
    }

    //        private lateinit var currentWeatherViewModel: CurrentWeatherViewModel
    private lateinit var binding: FragmentForecastBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Timber.d("ForecastFragmentOnCreateViewCalled")
        // Inflate the layout for this fragment
        binding = FragmentForecastBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        Timber.d("ForecastFragmentOnActivityCreatedCalled")
        super.onActivityCreated(savedInstanceState)
        binding.lifecycleOwner = this.viewLifecycleOwner
        setupViewModel()
        setAdapter()
        observeViewModel()
        getCurrentWeatherData()

    }

    private fun setAdapter() {
        val weatherForNextSevenDaysAdapter = WeatherForNextSevenDaysAdapter(
            binding.viewModel
        )
        binding.recyclerViewWeatherForNextSevenDays.adapter = weatherForNextSevenDaysAdapter
    }


    private fun getCurrentWeatherData() {
        currentWeatherViewModel.getCurrentWeather(
            getStringSharedPreference(SHARED_PREF_CURRENT_LATITUDE),
            getStringSharedPreference(SHARED_PREF_CURRENT_LONGITUDE),
            BuildConfig.WEATHER_API_KEY
        )
    }

    private fun observeViewModel() {
        currentWeatherViewModel.lottieAnimation.observe(requireActivity(), EventObserver {
            if (it) {
                binding.lottieLoadingAnimation.stopLottieAnimationView()
                binding.imageViewWeatherCondition.setWeatherConditionIcon(
                    getIntSharedPreference(
                        SHARED_PREF_WEATHER_CONDITION_KEY
                    )
                )
            } else {
                binding.lottieLoadingAnimation.startLottieAnimationView()
            }
        })
    }

    private fun setupViewModel() {
        binding.viewModel = currentWeatherViewModel
    }

}
