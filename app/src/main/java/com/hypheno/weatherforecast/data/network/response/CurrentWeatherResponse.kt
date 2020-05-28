package com.hypheno.weatherforecast.data.network.response

import com.hypheno.weatherforecast.data.db.entity.Current
import com.hypheno.weatherforecast.data.db.entity.WeatherLocation

data class CurrentWeatherResponse(
    val current: Current,
    val location: WeatherLocation
)