package com.ducttapeprogrammer.myapplication.data.model


import com.google.gson.annotations.SerializedName

/**
 * POJO for the next seven days Weather data
 * */
data class WeatherForNextSevenDays(
    @SerializedName("city")
    val city: City,
    @SerializedName("cnt")
    val cnt: Int,
    @SerializedName("cod")
    val cod: String,
    @SerializedName("list")
    val list: List<WeatherList>,
    @SerializedName("message")
    val message: Double
) {
    /**
     * Data class for getting City details
     * */
    data class City(
        @SerializedName("coord")
        val coord: Coord,
        @SerializedName("country")
        val country: String,
        @SerializedName("id")
        val id: Int,
        @SerializedName("name")
        val name: String,
        @SerializedName("population")
        val population: Int,
        @SerializedName("timezone")
        val timezone: Int
    ) {
        /**
         * Data class for getting coordinate details
         * */
        data class Coord(
            @SerializedName("lat")
            val lat: Double,
            @SerializedName("lon")
            val lon: Double
        )
    }

    /**
     * Data class that holds other weather information
     * */
    data class WeatherList(
        @SerializedName("clouds")
        val clouds: Int,
        @SerializedName("deg")
        val deg: Int,
        @SerializedName("dt")
        val dt: Int,
        @SerializedName("feels_like")
        val feelsLike: FeelsLike,
        @SerializedName("humidity")
        val humidity: Int,
        @SerializedName("pressure")
        val pressure: Int,
        @SerializedName("rain")
        val rain: Double,
        @SerializedName("speed")
        val speed: Double,
        @SerializedName("sunrise")
        val sunrise: Int,
        @SerializedName("sunset")
        val sunset: Int,
        @SerializedName("temp")
        val temp: Temp,
        @SerializedName("weather")
        val weather: List<WeatherMainList>
    ) {
        /**
         * Data class that get information about what's the weather feels like
         * */
        data class FeelsLike(
            @SerializedName("day")
            val day: Double,
            @SerializedName("eve")
            val eve: Double,
            @SerializedName("morn")
            val morn: Double,
            @SerializedName("night")
            val night: Double
        )

        /**
         * Data class that contains information about temperature changes
         * in the complete day
         * */
        data class Temp(
            @SerializedName("day")
            val day: Double,
            @SerializedName("eve")
            val eve: Double,
            @SerializedName("max")
            val max: Double,
            @SerializedName("min")
            val min: Double,
            @SerializedName("morn")
            val morn: Double,
            @SerializedName("night")
            val night: Double
        )

        /**
         * Data class for getting Weather meta-data
         **/
        data class WeatherMainList(
            @SerializedName("description")
            val description: String,
            @SerializedName("icon")
            val icon: String,
            @SerializedName("id")
            val id: Int,
            @SerializedName("main")
            val main: String
        )
    }
}