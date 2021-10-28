package com.sample.openweathermap.data.local.dp

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.sample.openweathermap.data.local.dp.dao.WeatherDao
import com.sample.openweathermap.data.model.forecast.ForecastResponse

@Database(
    entities = [ForecastResponse::class],
    version = 1,
    exportSchema = false
)

@TypeConverters(MyCustomTypeConverter::class)
abstract class AppDatabase: RoomDatabase() {

    abstract fun weatherDao(): WeatherDao
}