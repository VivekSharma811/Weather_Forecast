package com.hypheno.weatherforecast.util

import java.io.IOException
import java.lang.Exception

class NoConnectivityException : IOException()

class LocationPermissionNotGrantedException : Exception()