package com.mishok.core_impl.utils

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.LocationManager
import android.location.LocationManager.GPS_PROVIDER
import android.util.Log
import androidx.core.app.ActivityCompat
import com.mishok.core_api.utils.PolyGoLocationManager
import javax.inject.Inject

class PolyGoLocationManagerImpl @Inject constructor(
    private val context: Context
) : PolyGoLocationManager {

    override var longitude: Double = 0.0
    override var latitude: Double = 0.0

    override fun loadCurrentLocation() {
        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            val locationManager =
                context.applicationContext.getSystemService(Context.LOCATION_SERVICE) as LocationManager
            locationManager.requestLocationUpdates(GPS_PROVIDER, 100, 2F) {
                Log.d("location", it.latitude.toString())
                this.latitude = it.latitude
                this.longitude = it.longitude
            }
        }
    }
}