package com.hypheno.weatherforecast.data.repository

import androidx.lifecycle.LiveData
import com.hypheno.weatherforecast.data.db.CurrentWeatherDao
import com.hypheno.weatherforecast.data.db.unitlocalized.UnitSpecificCurrentWeatherEntry
import com.hypheno.weatherforecast.data.network.WeatherNetworkDatasource
import com.hypheno.weatherforecast.data.network.response.CurrentWeatherResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.threeten.bp.ZonedDateTime
import java.util.*

class ForecastRepositoryImpl(
    private val currentWeatherDao: CurrentWeatherDao,
    private val weatherNetworkDatasource: WeatherNetworkDatasource
) : ForecastRepository {

    init {
        weatherNetworkDatasource.downloadedCurrentWeather.observeForever { newCurrentWeather ->
            persistFetchedCurrentWeather(newCurrentWeather)
        }
    }

    override suspend fun getCurrentWeather(metric: Boolean): LiveData<out UnitSpecificCurrentWeatherEntry> {
        return withContext(Dispatchers.IO) {
            initWeatherData()
            return@withContext if(metric) currentWeatherDao.getWeatherMetric()
            else currentWeatherDao.getWeatherImperial()
        }
    }

    private fun persistFetchedCurrentWeather(fetchedWeather : CurrentWeatherResponse) {
        GlobalScope.launch(Dispatchers.IO) {
            currentWeatherDao.upsert(fetchedWeather.current)
        }
    }

    private suspend fun initWeatherData() {
        if(isFetchCurrentNeeded(ZonedDateTime.now().minusHours(1)))
            fetchCurrentWeather()
    }

    private suspend fun fetchCurrentWeather() {
        weatherNetworkDatasource.fetchCurrentWeather("Delhi", Locale.getDefault().language)
    }

    private fun isFetchCurrentNeeded(lastFetchTime : ZonedDateTime) : Boolean {
        val thirtyMinutesAgo = ZonedDateTime.now().minusMinutes(30)
        return lastFetchTime.isBefore(thirtyMinutesAgo)
    }
}