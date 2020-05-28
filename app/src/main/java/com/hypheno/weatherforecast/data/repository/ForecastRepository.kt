package com.hypheno.weatherforecast.data.repository

import androidx.lifecycle.LiveData
import com.hypheno.weatherforecast.data.db.unitlocalized.UnitSpecificCurrentWeatherEntry

interface ForecastRepository {

    suspend fun getCurrentWeather(metric : Boolean) : LiveData<out UnitSpecificCurrentWeatherEntry>

}