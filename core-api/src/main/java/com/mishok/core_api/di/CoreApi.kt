package com.mishok.core_api.di

import android.app.Application
import android.content.Context
import com.mishok.core_api.utils.LocaleManager

interface CoreApi {

    fun localeManager(): LocaleManager
    fun context(): Context
    fun application(): Application


}