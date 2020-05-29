package com.hypheno.weatherforecast.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters

const val CURRENT_WEATHER_ID = 0

@Entity(tableName = "current_weather")
data class Current(
    val feelslike: Int,
    val is_day: String,
    val observation_time: String,
    val precip: Double,
    val temperature: Double,
    val uv_index: Int,
    val visibility: Int,
    val weather_code: Int,
//    @TypeConverters(WeatherDesc::class)
//    val weather_descriptions: List<String>,
//    @TypeConverters(WeatherDesc::class)
//    val weather_icons: List<String>,
    val wind_dir: String,
    val wind_speed: Double
) {
    @PrimaryKey(autoGenerate = false)
    var id : Int = CURRENT_WEATHER_ID
}