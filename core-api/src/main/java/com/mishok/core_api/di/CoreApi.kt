package com.mishok.core_api.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.mishok.core_api.utils.AppCodeProvider
import com.mishok.core_api.utils.LocaleManager
import com.mishok.core_api.utils.PolyGoLocationManager

interface CoreApi {

    fun localeManager(): LocaleManager
    fun sharedPreference(): SharedPreferences
    fun context(): Context
    fun appCodeProvider(): AppCodeProvider
    fun locationManager(): PolyGoLocationManager
    fun application(): Application

    @IsDebug
    fun isDebug(): Boolean
    @AppVersion
    fun appVersion(): String
    @GooglePlayAvailable
    fun googlePlayAvailable(): Boolean

}