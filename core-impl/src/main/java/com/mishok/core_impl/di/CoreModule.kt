package com.mishok.core_impl.di

import android.app.Application
import android.content.Context
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GoogleApiAvailability
import com.mishok.core_api.di.GooglePlayAvailable
import com.mishok.core_api.utils.AppCodeProvider
import com.mishok.core_api.utils.LocaleManager
import com.mishok.core_impl.utils.LocaleManagerImpl
import dagger.Module
import dagger.Provides


@Module
class CoreModule {

    @Module
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
        @GooglePlayAvailable
        fun providePlayServicesAvailability(context: Context): Boolean {
            return GoogleApiAvailability.getInstance()
                .isGooglePlayServicesAvailable(context) == ConnectionResult.SUCCESS
        }
    }
}