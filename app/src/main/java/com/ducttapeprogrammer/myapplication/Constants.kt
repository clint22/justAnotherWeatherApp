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

//Base URL
const val BASE_URL = "https://api.openweathermap.org/data/2.5/"

//API Key
const val API_KEY = "4504261d389d54692524da1ad6dd932d"

//PARAM KEYS
const val KEY_LATITUDE = "lat"
const val KEY_LONGITUDE = "lon"
const val KEY_APP_ID = "appid"

//TEST DATA's
//16.198896 lat, 77.369793 long ( Raichur )
//9.992540 lat, 76.302948 long ( Kochi )
//-32.041388, 115.936144 long ( Australia )
//11.9871160, 75.7698640 ( Kannur )
const val TEST_LATITUDE = "9.989876"
const val TEST_LONGITUDE = "76.294732"

//TEMPERATURE CONSTANTS
const val KELVIN = 273.1

//Others
const val WEATHER_FOR_NEXT_SEVEN_DAYS_INITIAL_RANGE = 1