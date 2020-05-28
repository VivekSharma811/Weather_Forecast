package com.hypheno.weatherforecast.data.network

import androidx.lifecycle.LiveData
import com.hypheno.weatherforecast.data.network.response.CurrentWeatherResponse

interface WeatherNetworkDatasource {

    val downloadedCurrentWeather : LiveData<CurrentWeatherResponse>

    suspend fun fetchCurrentWeather(
        location : String,
        languageCode : String
    )

}