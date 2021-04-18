package com.mishok.polygo.app

import com.mishok.core_api.utils.AppCodeProvider
import com.mishok.polygo.BuildConfig
import com.mishok.polygo.R
import com.mishok.polygo.utils.woods.ReleaseTree
import com.yandex.mapkit.MapKitFactory
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import timber.log.Timber
import java.util.*

class PolyGoApplication : DaggerApplication(), AppCodeProvider {

    override val appCode: String
        get() = appCodeString

    private val applicationInjector = ComponentInitializer(this).initAppComponent()

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return applicationInjector
    }

    override fun onCreate() {
        super.onCreate()
        appCodeString = UUID.randomUUID().toString()
        initTimber()

        MapKitFactory.setApiKey(getString(R.string.YandexKey))
        MapKitFactory.initialize(this)

        //localeManager.setLocale()
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