package com.sample.openweathermap.domain.model.forecast


import com.google.gson.annotations.SerializedName

data class ForecastResponse(
    @SerializedName("city")
    val city: City,
    @SerializedName("cnt")
    val cnt: Int,
    @SerializedName("cod")
    val cod: String,
    @SerializedName("list")
    val list: List<Forecast>,
    @SerializedName("message")
    val message: Double
) {
    data class City(
        @SerializedName("coord")
        val coord: Coord,
        @SerializedName("country")
        val country: String,
        @SerializedName("id")
        val id: Int,
        @SerializedName("name")
        val name: String
    ) {
        data class Coord(
            @SerializedName("lat")
            val lat: Double,
            @SerializedName("lon")
            val lon: Double
        )
    }

    data class Forecast(
        @SerializedName("clouds")
        val clouds: Clouds,
        @SerializedName("dt")
        val dt: Int,
        @SerializedName("dt_txt")
        val dtTxt: String,
        @SerializedName("main")
        val main: Main,
        @SerializedName("rain")
        val rain: Rain,
        @SerializedName("sys")
        val sys: Sys,
        @SerializedName("weather")
        val weather: List<Weather>,
        @SerializedName("wind")
        val wind: Wind
    ) {
        data class Clouds(
            @SerializedName("all")
            val all: Int
        )

        data class Main(
            @SerializedName("grnd_level")
            val grndLevel: Double,
            @SerializedName("humidity")
            val humidity: Int,
            @SerializedName("pressure")
            val pressure: Double,
            @SerializedName("sea_level")
            val seaLevel: Double,
            @SerializedName("temp")
            val temp: Double,
            @SerializedName("temp_max")
            val tempMax: Double,
            @SerializedName("temp_min")
            val tempMin: Double
        )

        class Rain(
        )

        data class Sys(
            @SerializedName("pod")
            val pod: String
        )

        data class Weather(
            @SerializedName("description")
            val description: String,
            @SerializedName("icon")
            val icon: String,
            @SerializedName("id")
            val id: Int,
            @SerializedName("main")
            val main: String
        )

        data class Wind(
            @SerializedName("deg")
            val deg: Double,
            @SerializedName("speed")
            val speed: Double
        )
    }
}