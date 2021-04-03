package com.mishok.polygo.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Module
import dagger.Provides
import javax.inject.Provider


@Module(
    includes = [
        SearchModule::class,
        BookmarksModule::class,
        MapsModule::class
    ]
)
class UiModule {

    @Provides
    fun provideViewModelFactory(
            providers: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>
    ): ViewModelProvider.Factory = AppViewModelFactory(providers)

}