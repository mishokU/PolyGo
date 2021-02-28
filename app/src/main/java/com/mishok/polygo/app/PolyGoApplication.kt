package com.mishok.polygo.app

import android.app.Application
import com.mishok.core_api.utils.AppCodeProvider
import com.mishok.core_api.utils.LocaleManager
import com.mishok.polygo.BuildConfig
import com.mishok.polygo.di.AppComponent
import com.mishok.polygo.utils.ComponentRegistry
import com.mishok.polygo.utils.woods.ReleaseTree
import timber.log.Timber
import java.util.*

class PolyGoApplication : Application(), AppCodeProvider {

    override val appCode: String
        get() = appCodeString

    private val localeManager: LocaleManager
        get() = ComponentRegistry.get<AppComponent>().localeManager()


    override fun onCreate() {
        super.onCreate()
        appCodeString = UUID.randomUUID().toString()
        initTimber()
        localeManager.setLocale()
    }

    private fun initTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        } else {
            Timber.plant(ReleaseTree())
        }
    }

    companion object {
        var appCodeString: String = ""
            private set
    }
}