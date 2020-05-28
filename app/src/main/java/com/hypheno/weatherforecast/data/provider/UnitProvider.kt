package com.hypheno.weatherforecast.data.provider

import com.hypheno.weatherforecast.util.UnitSystem

interface UnitProvider {

    fun getUnitSystem() : UnitSystem

}