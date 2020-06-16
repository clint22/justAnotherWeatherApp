package com.ducttapeprogrammer.myapplication

import com.ducttapeprogrammer.myapplication.utils.convertKelvinToDegreeCelsius
import com.ducttapeprogrammer.myapplication.utils.getWeatherCondition
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * A class which contains all the unit tests of Utils class
 * */
class UtilsTest {
    /**
     * Tests if a value within the range [THUNDER_STORM_RANGE] is provided will it returns the id [THUNDER_STORM]
     * */
    @Test
    fun getWeatherConditionType_inputThunderStorm_returnThunderStormID() {
        val input = 205
        val result = getWeatherCondition(input)
        assertEquals(result, THUNDER_STORM)
    }

    /**
     * Tests if a value within the range [DRIZZLE_RANGE] is provided will it returns the id [DRIZZLE]
     * */
    @Test
    fun getWeatherConditionType_inputDrizzle_returnDrizzleID() {
        val input = 305
        val result = getWeatherCondition(input)
        assertEquals(result, DRIZZLE)
    }

    /**
     * Tests if a value within the range [RAIN_RANGE] is provided will it returns the id [RAIN]
     * */
    @Test
    fun getWeatherConditionType_inputRain_returnRainID() {
        val input = 510
        val result = getWeatherCondition(input)
        assertEquals(result, RAIN)
    }

    /**
     * Tests if a value within the range [SNOW_RANGE] is provided will it returns the id [SNOW]
     * */
    @Test
    fun getWeatherConditionType_inputSnow_returnSnowID() {
        val input = 622
        val result = getWeatherCondition(input)
        assertEquals(result, SNOW)
    }

    /**
     * Tests if a value within the range [ATMOSPHERE_RANGE] is provided will it returns the id [ATMOSPHERE]
     * */
    @Test
    fun getWeatherConditionType_inputAtmosphere_returnAtmosphereID() {
        val input = 705
        val result = getWeatherCondition(input)
        assertEquals(result, ATMOSPHERE)
    }

    /**
     * Tests if a value within the range [CLEAR_RANGE] is provided will it returns the id [CLEAR]
     * */
    @Test
    fun getWeatherConditionType_inputClear_returnClearID() {
        val input = 800
        val result = getWeatherCondition(input)
        assertEquals(result, CLEAR)
    }

    /**
     * Tests if a value within the range [CLOUD_RANGE] is provided will it returns the id [CLOUDS]
     * */
    @Test
    fun getWeatherConditionType_inputClouds_returnCloudsID() {
        val input = 802
        val result = getWeatherCondition(input)
        assertEquals(result, CLOUDS)
    }

    /**
     * Tests if a value zero is provided will it returns the id as zero itself
     * */
    @Test
    fun getWeatherConditionType_inputInvalid_returnZero() {
        val input = 0
        val result = getWeatherCondition(input)
        assertEquals(result, 0)
    }

    /**
     * Tests if a temperature in Kelvin units is provided, will it return it in degree celsius
     * */
    @Test
    fun getKelvinToDegreeCelsiusValue_inputThreeHundredAndFifty_returnSeventySeven() {

        val input = 350.0
        val result =
            convertKelvinToDegreeCelsius(
                input
            )
        assertEquals(result, 77)
    }
}
