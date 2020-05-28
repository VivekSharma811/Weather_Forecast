package com.hypheno.weatherforecast.ui.weather.current

import androidx.lifecycle.ViewModel
import com.hypheno.weatherforecast.data.provider.UnitProvider
import com.hypheno.weatherforecast.data.repository.ForecastRepository
import com.hypheno.weatherforecast.util.UnitSystem
import com.hypheno.weatherforecast.util.lazyDeferred

class CurrentWeatherViewModel(
    private val forecastRepository: ForecastRepository,
    private val unitProvider: UnitProvider
) : ViewModel() {

    private val unitSystem = unitProvider.getUnitSystem()

    val isMetric : Boolean
        get() = unitSystem == UnitSystem.METRIC

    val weather by lazyDeferred {
        forecastRepository.getCurrentWeather(isMetric)
    }
}
