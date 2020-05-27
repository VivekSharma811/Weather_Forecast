package com.hypheno.weatherforecast.data

data class Current(
    val cloudcover: Int,
    val feelslike: Int,
    val humidity: Int,
    val is_day: String,
    val observation_time: String,
    val precip: Int,
    val pressure: Double,
    val temperature: Double,
    val uv_index: Int,
    val visibility: Int,
    val weather_code: Int,
    val weather_descriptions: List<String>,
    val weather_icons: List<String>,
    val wind_degree: Int,
    val wind_dir: String,
    val wind_speed: Double
)