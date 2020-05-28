package com.hypheno.weatherforecast

import android.app.Application
import androidx.preference.PreferenceManager
import com.hypheno.weatherforecast.data.db.ForecastDatabase
import com.hypheno.weatherforecast.data.network.*
import com.hypheno.weatherforecast.data.provider.UnitProvider
import com.hypheno.weatherforecast.data.provider.UnitProviderImpl
import com.hypheno.weatherforecast.data.repository.ForecastRepository
import com.hypheno.weatherforecast.data.repository.ForecastRepositoryImpl
import com.hypheno.weatherforecast.ui.weather.current.CurrentWeatherViewModelFactory
import com.jakewharton.threetenabp.AndroidThreeTen
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class ForecastApplication : Application(), KodeinAware {

    override val kodein = Kodein.lazy {
        import(androidXModule(this@ForecastApplication))

        bind() from singleton { ForecastDatabase(instance()) }
        bind() from singleton { instance<ForecastDatabase>().currentWeatherDao() }
        bind<ConnectivityInterceptor>()with singleton { ConnectivityInterceptorImpl(instance()) }
        bind() from singleton { WeatherApiService(instance()) }
        bind<WeatherNetworkDatasource>() with singleton { WeatherNetworkDatasourceImpl(instance()) }
        bind<ForecastRepository>() with singleton { ForecastRepositoryImpl(instance(), instance()) }
        bind<UnitProvider>() with singleton { UnitProviderImpl(instance()) }
        bind() from provider { CurrentWeatherViewModelFactory(instance(), instance()) }
    }

    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)
        PreferenceManager.setDefaultValues(this, R.xml.preferences, false)
    }

}