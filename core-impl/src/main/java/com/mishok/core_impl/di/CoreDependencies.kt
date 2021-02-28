package com.mishok.core_impl.di

import android.app.Application
import com.mishok.core_api.di.AppVersion
import com.mishok.core_api.di.IsDebug

interface CoreDependencies {

    fun application(): Application
    @IsDebug
    fun isDebug(): Boolean
    @AppVersion
    fun appVersion(): String
}