package com.ducttapeprogrammer.myapplication

import org.junit.Assert.assertEquals
import org.junit.Test


class UtilsTest {


    @Test
    fun getWeatherConditionType_inputThunderStorm_returnThunderStormID() {
        val input = 205
        val result = getWeatherCondition(input)
        assertEquals(result, THUNDER_STORM)
    }

    @Test
    fun getWeatherConditionType_inputDrizzle_returnDrizzleID() {
        val input = 305
        val result = getWeatherCondition(input)
        assertEquals(result, DRIZZLE)
    }

    @Test
    fun getWeatherConditionType_inputRain_returnRainID() {
        val input = 510
        val result = getWeatherCondition(input)
        assertEquals(result, RAIN)
    }

    @Test
    fun getWeatherConditionType_inputSnow_returnSnowID() {
        val input = 622
        val result = getWeatherCondition(input)
        assertEquals(result, SNOW)
    }

    @Test
    fun getWeatherConditionType_inputAtmosphere_returnAtmosphereID() {
        val input = 705
        val result = getWeatherCondition(input)
        assertEquals(result, ATMOSPHERE)
    }

    @Test
    fun getWeatherConditionType_inputClear_returnClearID() {
        val input = 800
        val result = getWeatherCondition(input)
        assertEquals(result, CLEAR)
    }

    @Test
    fun getWeatherConditionType_inputClouds_returnCloudsID() {
        val input = 802
        val result = getWeatherCondition(input)
        assertEquals(result, CLOUDS)
    }

    @Test
    fun getWeatherConditionType_inputInvalid_returnZero() {
        val input = 0
        val result = getWeatherCondition(input)
        assertEquals(result, 0)
    }

}