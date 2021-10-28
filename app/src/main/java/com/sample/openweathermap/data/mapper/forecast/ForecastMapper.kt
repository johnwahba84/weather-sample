package com.sample.openweathermap.data.mapper.forecast


import com.sample.openweathermap.data.model.forecast.ForecastResponse
import com.sample.openweathermap.domain.model.forecast.ForecastModel
import javax.inject.Inject

class ForecastMapper @Inject constructor() {

    //TODO: Need to filter model to only needed data
    fun toForecastDetails(data: ForecastResponse): ForecastModel {

        val weatherList = ArrayList<ForecastModel.Forecast>()

        val weather = data.list[0].weather[0]

        weatherList.add(
            ForecastModel.Forecast(
                dt = data.list[0].dt,
                dtTxt = data.list[0].dtTxt,
                main = ForecastModel.Forecast.Main(
                    tempMin = data.list[0].main.tempMin,
                    tempMax = data.list[0].main.tempMax
                ),
                wind = ForecastModel.Forecast.Wind(speed = data.list[0].wind.speed),
                weather = ForecastModel.Forecast.Weather(
                    description = weather.description,
                    id = weather.id,
                    main = weather.main
                )
            )
        )

        return ForecastModel(
            city = ForecastModel.City(
                country = data.city.country, id = data.city.id, name = data.city.name
            ),
            cnt = data.cnt,
            cod = data.cod,
            list = weatherList,
            message = data.message
        )
    }
}