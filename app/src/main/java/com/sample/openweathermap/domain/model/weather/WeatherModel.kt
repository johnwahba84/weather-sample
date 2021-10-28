package com.sample.openweathermap.domain.model.weather


data class WeatherModel(
    val id: Int,
    val name: String,
    val main: Main,
    val weather: Weather,
    val wind: Wind
) {
    data class Main(
        val tempMax: Double,
        val tempMin: Double
    )

    data class Weather(
        val description: String,
        val id: Int,
        val main: String
    )

    data class Wind(
        val speed: Double
    )
}