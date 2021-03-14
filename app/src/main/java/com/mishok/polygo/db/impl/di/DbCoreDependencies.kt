package com.mishok.polygo.db.impl.di

import android.content.Context
import com.mishok.core_api.di.IsDebug

interface DbCoreDependencies {
    fun context(): Context

    @IsDebug
    fun isDebug(): Boolean
}