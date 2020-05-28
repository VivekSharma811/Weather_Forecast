package com.hypheno.weatherforecast.data.provider

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import com.hypheno.weatherforecast.util.UnitSystem

const val UNIT_SYSTEM = "UNIT_SYSTEM"

class UnitProviderImpl(context: Context) : PreferenceProvider(context), UnitProvider {



    override fun getUnitSystem(): UnitSystem {
        return UnitSystem.METRIC
    }

//    override fun getUnitSystem(): UnitSystem {
//        val selectedName = preferences.getString(UNIT_SYSTEM, UnitSystem.METRIC.name)
//        return UnitSystem.valueOf(selectedName!!)
//    }
}