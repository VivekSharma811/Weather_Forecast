package com.hypheno.weatherforecast.data.network

import com.hypheno.weatherforecast.data.network.response.CurrentWeatherResponse
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

const val API_KEY = "aaae861edb521abe41ccf9299a89d159"

interface WeatherApiService {

    companion object {
        operator fun invoke(
            connectivityInterceptor: ConnectivityInterceptor
        ) : WeatherApiService {
            val requestInterceptor = Interceptor {chain ->

                val url = chain.request()
                    .url()
                    .newBuilder()
                    .addQueryParameter("access_key" ,
                        API_KEY
                    )
                    .build()

                val request = chain.request()
                    .newBuilder()
                    .url(url)
                    .build()

                return@Interceptor chain.proceed(request)
            }

            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(requestInterceptor)
                .addInterceptor(connectivityInterceptor)
                .build()

            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("http://api.weatherstack.com/")
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(WeatherApiService::class.java)
        }
    }

    @GET("current")
    fun getCurrentWeather(
        @Query("query") location : String,
        @Query("lang") languageCode : String = "en"
    ) : Deferred<CurrentWeatherResponse>

}