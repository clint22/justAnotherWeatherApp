package com.ducttapeprogrammer.myapplication.location

import android.Manifest
import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.ducttapeprogrammer.myapplication.*
import com.ducttapeprogrammer.myapplication.databinding.FragmentLocationBinding
import com.ducttapeprogrammer.myapplication.utils.setBooleanSharedPreference
import com.ducttapeprogrammer.myapplication.utils.setStringSharedPreference
import timber.log.Timber

/**
 * This fragment will helps the user to choose the current location and
 * also let the user to choose other location.
 * */
class LocationFragment : Fragment(), View.OnClickListener {
    private lateinit var binding: FragmentLocationBinding
    private val accessFineLocationAndCoarseLocationPermissionRequestCode = 10
    private var locationManager: LocationManager? = null
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
        setClickListeners()
    }

    private fun setClickListeners() {

        binding.textViewGivePermission.setOnClickListener(this)
    }

    private fun checkGpsEnabled() {

        locationManager =
            requireActivity().getSystemService(Context.LOCATION_SERVICE) as LocationManager?
        if (!locationManager?.isProviderEnabled(LocationManager.GPS_PROVIDER)!!) {
            enableGps()
        } else {
            checkLocationPermission()
        }

    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        Timber.d("onRequestPermissionsResult_called")
        if (requestCode == accessFineLocationAndCoarseLocationPermissionRequestCode) {

            var locationPermissionGranted = true
            for (grantResult in grantResults) {
                locationPermissionGranted = locationPermissionGranted and
                        (grantResult == PackageManager.PERMISSION_GRANTED)
            }
            Timber.d(locationPermissionGranted.toString())
            if (locationPermissionGranted) {
                Timber.d("locationPermissionGranted")
                true.setBooleanSharedPreference(SHARED_PREF_PERMISSIONS_GIVEN)
                getLatitudeAndLongitude()
            } else {
                binding.constraintLayoutPermissionDenied.visibility = View.VISIBLE
            }
        }

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

        Timber.d("checkLocationPermission_called")
        if (ActivityCompat.checkSelfPermission(
                requireActivity(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
            || ActivityCompat.checkSelfPermission(
                requireActivity(), Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    requireActivity(),
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) ||
                ActivityCompat.shouldShowRequestPermissionRationale(
                    requireActivity(),
                    Manifest.permission.ACCESS_COARSE_LOCATION
                )
            ) {
                binding.constraintLayoutPermissionDenied.visibility = View.VISIBLE

            } else {

                requestPermissions(
                    arrayOf(
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                    ), accessFineLocationAndCoarseLocationPermissionRequestCode
                )
            }
        } else {
            binding.constraintLayoutPermissionDenied.visibility = View.GONE
            getLatitudeAndLongitude()
        }

    }

    @SuppressLint("MissingPermission")
    private fun getLatitudeAndLongitude() {

        Timber.d("permission_success")
        val providers = locationManager?.getProviders(true)
        var bestLocation: Location? = null
        if (providers != null) {
            for (provider in providers) {
                val l: Location = locationManager?.getLastKnownLocation(provider) ?: continue
                if (bestLocation == null || l.accuracy < bestLocation.accuracy) { // Found best last known location: %s", l);
                    bestLocation = l
                    Timber.e(bestLocation.latitude.toString())
                    Timber.e(bestLocation.longitude.toString())
                    bestLocation.latitude.toString().setStringSharedPreference(
                        SHARED_PREF_CURRENT_LATITUDE
                    )
                    bestLocation.longitude.toString().setStringSharedPreference(
                        SHARED_PREF_CURRENT_LONGITUDE
                    )

                }
            }
        } else {
            requireActivity().getString(R.string.unable_to_find_location)
                .showToast(requireContext())
        }

    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.textViewGivePermission -> {

                val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                val uri: Uri = Uri.fromParts("package", requireActivity().packageName, null)
                intent.data = uri
                startActivity(intent)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        checkLocationPermission()
    }
}
