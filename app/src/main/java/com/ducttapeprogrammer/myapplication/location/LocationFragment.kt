package com.ducttapeprogrammer.myapplication.location

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity.RESULT_CANCELED
import android.app.Activity.RESULT_OK
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
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.ducttapeprogrammer.myapplication.*
import com.ducttapeprogrammer.myapplication.databinding.FragmentLocationBinding
import com.ducttapeprogrammer.myapplication.utils.setBooleanSharedPreference
import com.ducttapeprogrammer.myapplication.utils.setStringSharedPreference
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.api.net.PlacesClient
import com.google.android.libraries.places.widget.Autocomplete
import com.google.android.libraries.places.widget.AutocompleteActivity
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode
import com.google.gson.Gson
import timber.log.Timber

/**
 * This fragment will helps the user to choose the current location and
 * also let the user to choose other location.
 * */
class LocationFragment : Fragment(), View.OnClickListener {
    private lateinit var binding: FragmentLocationBinding
    private val locationViewModel by viewModels<LocationViewModel> {
        LocationViewModel.LocationViewModelFactory(
            (requireActivity().applicationContext as MyApplication).locationRepository
        )
    }
    private val accessFineLocationAndCoarseLocationPermissionRequestCode =
        ACCESS_FINE_LOCATION_AND_COARSE_LOCATION_PERMISSION_REQUEST_CODE
    private val autocompletePlacesRequestCode = AUTO_COMPLETE_PLACES_REQUEST_CODE
    private var locationManager: LocationManager? = null
    private var placesClient: PlacesClient? = null
    private var currentPlaceClicked: Boolean = false

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
        setupViewModel()
        setAdapter()
        observeViewModel()
    }

    override fun onPause() {
        super.onPause()
        currentPlaceClicked = false
    }

    private fun observeViewModel() {
        locationViewModel.locationClicked.observe(requireActivity(), Observer {
            Timber.d(Gson().toJson(it))
            navigateToForecastFragment(
                otherPlaceClicked = true,
                latitude = it.latitude.toString(),
                longitude = it.longitude.toString()
            )
        })
    }

    private fun navigateToForecastFragment(
        otherPlaceClicked: Boolean,
        latitude: String,
        longitude: String
    ) {

        if (otherPlaceClicked) {
            latitude.setStringSharedPreference(SHARED_PREF_CURRENT_LATITUDE)
            longitude.setStringSharedPreference(SHARED_PREF_CURRENT_LONGITUDE)
        } else {
            latitude.setStringSharedPreference(SHARED_PREF_CURRENT_LATITUDE)
            longitude.setStringSharedPreference(SHARED_PREF_CURRENT_LONGITUDE)
        }
        findNavController().navigate(R.id.action_locationFragment_to_forecastFragment, null)

    }

    private fun setAdapter() {

        val locationAdapter = LocationAdapter(
            binding.viewModel
        )
        binding.recyclerViewLocations.adapter = locationAdapter

    }

    private fun setClickListeners() {

        binding.textViewGivePermission.setOnClickListener(this)
        binding.imageViewSearchLocation.setOnClickListener(this)
        binding.linearLayoutCurrentPlace.setOnClickListener(this)
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
                true.setBooleanSharedPreference(
                    SHARED_PREF_PERMISSIONS_GIVEN
                )
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
            setupGooglePlaces()
            getLatitudeAndLongitude()
        }

    }

    private fun setupGooglePlaces() {

        if (!Places.isInitialized()) {
            Places.initialize(requireActivity(), BuildConfig.GOOGLE_PLACES_API_KEY)
        }
        placesClient = Places.createClient(requireActivity())
    }

    private fun getLatitudeAndLongitude() {

        val providers = locationManager?.getProviders(true)
        val bestLocation: Location? = null
        if (providers != null) {
            getCurrentLocationLatitudeAndLongitude(providers, bestLocation)
        } else {
            requireActivity().getString(R.string.unable_to_find_location)
                .showToast(requireContext())
        }

    }

    @SuppressLint("MissingPermission")
    private fun getCurrentLocationLatitudeAndLongitude(
        providers: MutableList<String>,
        bestLocation: Location?
    ) {
        var location = bestLocation
        for (provider in providers) {
            val l: Location = locationManager?.getLastKnownLocation(provider) ?: continue
            if (location == null || l.accuracy < location.accuracy) {
                location = l
                if (currentPlaceClicked) {

                    navigateToForecastFragment(
                        otherPlaceClicked = false,
                        latitude = location.latitude.toString(),
                        longitude = location.longitude.toString()
                    )
                }
            }
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

            R.id.imageViewSearchLocation -> {
                onSearchCalled()
            }

            R.id.linearLayoutCurrentPlace -> {
                currentPlaceClicked = true
                getLatitudeAndLongitude()

            }
        }
    }

    private fun onSearchCalled() {

        val fields =
            listOf(
                Place.Field.ID,
                Place.Field.NAME,
                Place.Field.LAT_LNG,
                Place.Field.ADDRESS_COMPONENTS
            )
        val intent = Autocomplete.IntentBuilder(
            AutocompleteActivityMode.FULLSCREEN, fields
        ).build(requireActivity())
        startActivityForResult(intent, autocompletePlacesRequestCode)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == autocompletePlacesRequestCode) {
            when (resultCode) {
                RESULT_OK -> {
                    createPlace(data?.let { Autocomplete.getPlaceFromIntent(it) })
                }
                AutocompleteActivity.RESULT_ERROR -> {
                    val status = data?.let { Autocomplete.getStatusFromIntent(it) }
                    Timber.i("auto-complete_places_error %s", status?.statusMessage)
                }
                RESULT_CANCELED -> {
                    Timber.i("User canceled the operation")
                }
            }
        }

    }

    private fun createPlace(place: Place?) {

        Timber.d("places %s", Gson().toJson(place))
        val latitude = place?.latLng?.latitude
        val longitude = place?.latLng?.longitude
        var regionName = ""
        var stateName = ""
        var countryName = ""

        /*Loops through the addressComponents to filter the types which are locality, administrative_area_level_1 and
        * country so that we can easily show the selected place in the format REGION, DISTRICT, COUNTRY wise*/

        place?.addressComponents?.asList()?.forEach { places ->
            Timber.i("Places types %s", places.types)
            when {
                places.types.contains(GOOGLE_PLACES_NATURAL_FEATURE) -> {
                    regionName = places.name
                }
                places.types.contains(GOOGLE_PLACES_TYPE_LOCALITY) -> {
                    regionName = places.name
                }
                places.types.contains(GOOGLE_PLACES_TYPE_ADMINISTRATIVE_AREA_LEVEL_1) -> {
                    stateName = places.name

                }
                places.types.contains(GOOGLE_PLACES_TYPE_COUNTRY) -> {
                    countryName = places.name

                }
            }
        }
        Timber.i("latitude %s", latitude)
        Timber.i("Longitude %s", longitude)
        Timber.i("Region Name %s", regionName)
        Timber.i("State Name %s", stateName)
        Timber.i("Country Name %s", countryName)

        val places = com.ducttapeprogrammer.myapplication.data.model.Places(
            latitude = latitude,
            longitude = longitude,
            region = regionName,
            state = stateName,
            country = countryName
        )
        Timber.i("places is %s", places)
        addPlaceToLocal(places)
    }

    private fun addPlaceToLocal(places: com.ducttapeprogrammer.myapplication.data.model.Places) {
        locationViewModel.insertPlace(places)
    }

    override fun onResume() {
        super.onResume()
        checkLocationPermission()
    }

    private fun setupViewModel() {
        binding.viewModel = locationViewModel
    }
}
