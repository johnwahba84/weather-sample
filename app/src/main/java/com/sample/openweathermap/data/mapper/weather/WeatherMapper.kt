package com.sample.openweathermap.data.mapper.weather


import com.sample.openweathermap.data.model.weather.WeatherResponse
import com.sample.openweathermap.domain.model.weather.WeatherModel
import javax.inject.Inject

class WeatherMapper @Inject constructor() {

    //TODO: Need to filter model to only needed data
    fun toWeatherDetails(data: WeatherResponse): WeatherModel {
        return WeatherModel(
            id = data.id,
            name = data.name,
            main = WeatherModel.Main(
                tempMin = data.main.tempMin,
                tempMax = data.main.tempMax
            ),
            wind = WeatherModel.Wind(speed = data.wind.speed),
            weather = WeatherModel.Weather(
                description = data.weather[0].description,
                id = data.weather[0].id,
                main = data.weather[0].main
            )
        )
    }
}