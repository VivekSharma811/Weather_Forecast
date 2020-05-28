package com.hypheno.weatherforecast.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import org.threeten.bp.Instant
import org.threeten.bp.ZoneId
import org.threeten.bp.ZonedDateTime

const val WEATHER_LOCATION_ID = 0

@Entity(tableName = "weather_location")
data class WeatherLocation(
    val country: String,
    val lat: Double,
    val localtime_epoch: Long,
    val lon: Double,
    val name: String,
    val region: String,
    val timezone_id: String,
    val utc_offset: String
) {
    @PrimaryKey(autoGenerate = false)
    var id : Int = WEATHER_LOCATION_ID

    val zonedDateTime : ZonedDateTime
        get() {
            val instant = Instant.ofEpochSecond(localtime_epoch)
            val zoneId = ZoneId.of(timezone_id)
            return ZonedDateTime.ofInstant(instant, zoneId)
        }
}