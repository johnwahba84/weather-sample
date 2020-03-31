package com.sample.openweathermap.constants

import androidx.annotation.StringDef

object AppConstants{

    @StringDef(ApiPath.WEATHER)
    annotation class ApiConfiguration{
        companion object{
            const val URL = "https://api.openweathermap.org/data/2.5/"
            const val API_ID = "fc7fa1eb62e6fabe073ea86f3dd570d5\n"
        }
    }

    @StringDef(ApiPath.WEATHER)
    annotation class ApiPath{
        companion object{
            const val WEATHER = "weather"
        }
    }
}
