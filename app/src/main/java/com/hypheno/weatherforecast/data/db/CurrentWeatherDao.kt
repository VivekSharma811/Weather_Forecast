package com.hypheno.weatherforecast.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hypheno.weatherforecast.data.db.entity.CURRENT_WEATHER_ID
import com.hypheno.weatherforecast.data.db.entity.Current
import com.hypheno.weatherforecast.data.db.unitlocalized.ImperialCurrentWeatherEntry
import com.hypheno.weatherforecast.data.db.unitlocalized.MetricCurrentWeatherEntry

@Dao
interface CurrentWeatherDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsert(weatherEntry : Current)

    @Query("SELECT * FROM current_weather where id= $CURRENT_WEATHER_ID")
    fun getWeatherMetric() : LiveData<MetricCurrentWeatherEntry>

    @Query("SELECT * FROM current_weather where id= $CURRENT_WEATHER_ID")
    fun getWeatherImperial() : LiveData<ImperialCurrentWeatherEntry>

}