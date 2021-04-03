package com.mishok.core_api.utils

interface PolyGoLocationManager {
    var longitude: Double
    var latitude: Double
    fun loadCurrentLocation()
}