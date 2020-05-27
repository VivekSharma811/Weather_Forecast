package com.hypheno.weatherforecast.data.network.response

import com.hypheno.weatherforecast.data.db.entity.Current
import com.hypheno.weatherforecast.data.db.entity.Location

data class CurrentWeatherResponse(
    val current: Current,
    val location: Location
)