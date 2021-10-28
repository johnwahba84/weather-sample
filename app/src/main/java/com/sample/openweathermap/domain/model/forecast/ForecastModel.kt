package com.sample.openweathermap.domain.model.forecast


data class ForecastModel(
    val city: City,
    val cnt: Int,
    val cod: String,
    val list: List<Forecast>,
    val message: Double
) {
    data class City(
        val country: String,
        val id: Int,
        val name: String
    )

    data class Forecast(
        val dt: Int,
        val dtTxt: String,
        val main: Main,
        val wind: Wind,
        val weather: Weather
    ) {
        data class Weather(
            val description: String,
            val id: Int,
            val main: String
        )

        data class Main(
            val tempMax: Double,
            val tempMin: Double
        )

        data class Wind(
            val speed: Double
        )
    }
}