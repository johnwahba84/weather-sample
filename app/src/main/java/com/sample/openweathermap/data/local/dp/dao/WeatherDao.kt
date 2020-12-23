package com.sample.openweathermap.data.local.dp.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sample.openweathermap.domain.model.forecast.ForecastResponse
import kotlinx.coroutines.flow.Flow

@Dao
interface WeatherDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun updateForecast(data: ForecastResponse)

    @Query("SELECT * FROM forecast_info")
    fun getForecast(): Flow<ForecastResponse>

    @Query("DELETE FROM forecast_info")
    fun delete()
}