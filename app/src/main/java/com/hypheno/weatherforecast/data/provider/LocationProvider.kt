package com.hypheno.weatherforecast.data.provider

import com.hypheno.weatherforecast.data.db.entity.WeatherLocation

interface LocationProvider {

    suspend fun hasLocationChanged(lastWeatherLocation: WeatherLocation) : Boolean
    suspend fun getPreferredLocationString() : String

}