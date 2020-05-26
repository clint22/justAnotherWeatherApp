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

//Base URL
const val BASE_URL = "api.openweathermap.org/data/2.5/"

//API Key
const val API_KEY = "4504261d389d54692524da1ad6dd932d"

//PARAM KEYS
const val KEY_LATITUDE = "lat"
const val KEY_LONGITUDE = "lon"
const val KEY_APP_ID = "appid"

//TEST DATA's
const val TEST_LATITUDE = "9.992540"
const val TEST_LONGITUDE = "76.302948"