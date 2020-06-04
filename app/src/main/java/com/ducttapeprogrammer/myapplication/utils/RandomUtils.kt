package com.ducttapeprogrammer.myapplication.utils

import com.ducttapeprogrammer.myapplication.DATE_FORMAT_ONE
import com.ducttapeprogrammer.myapplication.KELVIN
import com.ducttapeprogrammer.myapplication.SHARED_PREF_DATE_COUNT
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.roundToInt


/**
 * This function will convert the kelvin to degree celsius given the input
 * */
fun convertKelvinToDegreeCelsius(kelvin: Double?): Int? {
    return (kelvin?.toInt()?.minus(KELVIN))?.roundToInt()
}


/**
 * This function will return the date as a String. It will increment date by 1 day
 * every time this function is called. It uses a [SHARED_PREF_DATE_COUNT] to verify the
 * day is only incremented till 4 days, to make sure, only the next 4 days date is shown.
 * */
fun getCurrentDate(): String {
    if (getIntSharedPreference(SHARED_PREF_DATE_COUNT) > 4) {
        val count = 0
        count.setIntSharedPreference(SHARED_PREF_DATE_COUNT)
    }
    var count = getIntSharedPreference(SHARED_PREF_DATE_COUNT)
    count += 1
    count.setIntSharedPreference(SHARED_PREF_DATE_COUNT)
    val oldDate: String? = SimpleDateFormat(DATE_FORMAT_ONE, Locale.getDefault()).format(Date())
    val simpleDateFormat = SimpleDateFormat(DATE_FORMAT_ONE, Locale.getDefault())
    val calendar = Calendar.getInstance()
    try {
        calendar.time = simpleDateFormat.parse(oldDate)
    } catch (e: ParseException) {
        e.printStackTrace()
    }
    calendar.add(Calendar.DAY_OF_MONTH, count)

    return simpleDateFormat.format(calendar.time)
}




