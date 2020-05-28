package com.hypheno.weatherforecast.data.network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.hypheno.weatherforecast.data.network.response.CurrentWeatherResponse
import com.hypheno.weatherforecast.util.NoConnectivityException

class WeatherNetworkDatasourceImpl(
    private val weatherApiService: WeatherApiService
) : WeatherNetworkDatasource {

    private val _downloadedCurrentWeather = MutableLiveData<CurrentWeatherResponse>()

    override val downloadedCurrentWeather: LiveData<CurrentWeatherResponse>
        get() = _downloadedCurrentWeather

    override suspend fun fetchCurrentWeather(location: String, languageCode: String) {
        try {
            val fetchedCurrentWeather = weatherApiService
                .getCurrentWeather(location, languageCode)
                .await()

            _downloadedCurrentWeather.postValue(fetchedCurrentWeather)
        } catch (e : NoConnectivityException) {
            Log.e("Connectivity", "No internet connection", e)
        }
    }
}