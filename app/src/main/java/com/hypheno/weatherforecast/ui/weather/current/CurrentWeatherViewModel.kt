package com.hypheno.weatherforecast.ui.weather.current

import androidx.lifecycle.ViewModel
import com.hypheno.weatherforecast.data.repository.ForecastRepository
import com.hypheno.weatherforecast.util.UnitSystem
import com.hypheno.weatherforecast.util.lazyDeferred

class CurrentWeatherViewModel(
    private val forecastRepository: ForecastRepository
) : ViewModel() {

    private val unitSystem = UnitSystem.METRIC

    val isMetric : Boolean
        get() = unitSystem == UnitSystem.METRIC

    val weather by lazyDeferred {
        forecastRepository.getCurrentWeather(isMetric)
    }
}
