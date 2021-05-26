package com.mishok.core_impl.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GoogleApiAvailability
import com.mishok.core_api.di.GooglePlayAvailable
import com.mishok.core_api.utils.AppCodeProvider
import com.mishok.core_api.utils.CoroutineScopeMain
import com.mishok.core_api.utils.LocaleManager
import com.mishok.core_api.utils.PolyGoLocationManager
import com.mishok.core_impl.utils.LocaleManagerImpl
import com.mishok.core_impl.utils.PolyGoLocationManagerImpl
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton


@Module
class CoreModule {

    companion object {

        @Provides
        @JvmStatic
        fun providesContext(application: Application): Context {
            return application
        }

        @Provides
        @JvmStatic
        fun provideAppCodeProvider(context: Context): AppCodeProvider {
            return context.applicationContext as AppCodeProvider
        }

        @Provides
        @JvmStatic
        fun provideLocaleManager(context: Context): LocaleManager = LocaleManagerImpl(context)

        @Provides
        @JvmStatic
        fun provideSharedPreference(context: Context): SharedPreferences {
            return PreferenceManager.getDefaultSharedPreferences(context)
        }

        @Provides
        @Singleton
        @JvmStatic
        fun providePolyGoLocationManager(context: Context): PolyGoLocationManager =
            PolyGoLocationManagerImpl(context)

        @Provides
        @GooglePlayAvailable
        fun providePlayServicesAvailability(context: Context): Boolean {
            return GoogleApiAvailability.getInstance()
                .isGooglePlayServicesAvailable(context) == ConnectionResult.SUCCESS
        }

        @Provides
        @Singleton
        fun provideCoroutineScopeIO(): CoroutineScope = CoroutineScope(Dispatchers.IO)

        @CoroutineScopeMain
        @Provides
        fun provideCoroutineScopeMain(): CoroutineScope = CoroutineScope(Dispatchers.Main)
    }
}