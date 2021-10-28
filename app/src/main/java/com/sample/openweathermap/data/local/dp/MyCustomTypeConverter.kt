package com.sample.openweathermap.data.local.dp

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.sample.openweathermap.data.model.forecast.ForecastResponse.Forecast

class MyCustomTypeConverter {

    @TypeConverter
    fun stringToSomeObjectList(data: String?): List<Forecast> {
        if (data == null) {
            return emptyList()
        }
        val listType = object : TypeToken<List<Forecast?>?>() {}.type
        return Gson().fromJson(data, listType)
    }

    @TypeConverter
    fun someObjectListToString(someObjects: List<Forecast?>?): String {
        return Gson().toJson(someObjects)
    }
}