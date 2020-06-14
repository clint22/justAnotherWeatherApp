package com.ducttapeprogrammer.myapplication

//Weather condition
const val THUNDER_STORM = 100
const val DRIZZLE = 101
const val RAIN = 103
const val SNOW = 104
const val ATMOSPHERE = 105
const val CLEAR = 106
const val CLOUDS = 107

//Weather condition range
val THUNDER_STORM_RANGE = 200..232
val DRIZZLE_RANGE = 300..321
val RAIN_RANGE = 500..531
val SNOW_RANGE = 600..622
val ATMOSPHERE_RANGE = 701..781
val CLEAR_RANGE = 800..800
val CLOUD_RANGE = 801..804

//SharedPref Keys
const val SHARED_PREF_WEATHER_CONDITION_KEY = "weather_condition"
const val SHARED_PREF_CURRENT_LATITUDE = "current_latitude"
const val SHARED_PREF_CURRENT_LONGITUDE = "current_longitude"
const val SHARED_PREF_DATE_COUNT = "date_count"
const val SHARED_PREF_PERMISSIONS_GIVEN = "permissions_given"

//Base URL
const val BASE_URL = "https://api.openweathermap.org/data/2.5/"

//PARAM KEYS
const val KEY_LATITUDE = "lat"
const val KEY_LONGITUDE = "lon"
const val KEY_APP_ID = "appid"

//TEMPERATURE CONSTANTS
const val KELVIN = 273.1

//Others
const val WEATHER_FOR_NEXT_SEVEN_DAYS_INITIAL_RANGE = 1
const val DATE_FORMAT_ONE = "E,MMMMd"
const val ACCESS_FINE_LOCATION_AND_COARSE_LOCATION_PERMISSION_REQUEST_CODE = 10
const val AUTO_COMPLETE_PLACES_REQUEST_CODE = 11

//Google Places
const val GOOGLE_PLACES_TYPE_LOCALITY = "locality"
const val GOOGLE_PLACES_TYPE_ADMINISTRATIVE_AREA_LEVEL_1 = "administrative_area_level_1"
const val GOOGLE_PLACES_TYPE_COUNTRY = "country"
const val GOOGLE_PLACES_NATURAL_FEATURE = "natural_feature"

