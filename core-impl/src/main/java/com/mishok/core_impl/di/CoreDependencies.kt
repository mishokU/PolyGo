package com.mishok.core_impl.di

import android.app.Application
import com.mishok.core_api.di.AppVersion
import com.mishok.core_api.di.IsDebug
import com.mishok.core_api.utils.PolyGoLocationManager

interface CoreDependencies {

    fun application(): Application
    @IsDebug
    fun isDebug(): Boolean
    @AppVersion
    fun appVersion(): String
}