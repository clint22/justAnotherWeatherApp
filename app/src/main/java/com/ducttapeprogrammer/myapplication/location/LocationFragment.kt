package com.ducttapeprogrammer.myapplication.location

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ducttapeprogrammer.myapplication.databinding.FragmentLocationBinding


class LocationFragment : Fragment() {
    private lateinit var binding: FragmentLocationBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLocationBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        checkGpsEnabled()
        checkLocationPermission()
    }

    private fun checkGpsEnabled() {

        /*location nManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);*/
        val locationManager =
        enableGps()
    }

    private fun enableGps() {
        val builder = AlertDialog.Builder(requireActivity())
        builder.setMessage("Enable GPS").setCancelable(false).setPositiveButton(
            "Yes"
        ) { _, _ ->
            val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
            startActivity(intent)
        }.setNegativeButton(
            "No"
        ) { dialog, _ ->
            dialog.cancel()
        }
        val alertDialog = builder.create()
        alertDialog.show()
    }

    private fun checkLocationPermission() {

    }
}
