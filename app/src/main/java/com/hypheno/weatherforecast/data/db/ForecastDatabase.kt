package com.hypheno.weatherforecast.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.hypheno.weatherforecast.data.db.entity.Current

@Database(
    entities = [Current::class],
    version = 1
)
abstract class ForecastDatabase : RoomDatabase() {

    abstract fun currentWeatherDao() : CurrentWeatherDao

    companion object {
        @Volatile private var instance : ForecastDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also { instance = it }
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext,
                ForecastDatabase::class.java, "forecast.db")
                .build()
    }

}