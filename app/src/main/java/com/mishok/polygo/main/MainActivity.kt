package com.mishok.polygo.main

import android.os.Bundle
import com.mishok.core_api.utils.PolyGoLocationManager
import com.mishok.polygo.R
import com.yandex.mapkit.MapKitFactory
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var polyGoLocationManager: PolyGoLocationManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_PolyGo)
        setContentView(R.layout.activity_main)
        initMap()
        initStartLocation()
    }

    private fun initStartLocation() {
        polyGoLocationManager.loadCurrentLocation()
    }

    private fun initMap() {
        MapKitFactory.setApiKey(getString(R.string.YandexKey))
        MapKitFactory.initialize(this)
    }

}