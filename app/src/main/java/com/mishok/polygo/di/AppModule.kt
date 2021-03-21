package com.mishok.polygo.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mishok.polygo.base.route.RouteDestination
import com.mishok.polygo.db.api.providers.SearchProvider
import com.mishok.polygo.domain.search.SearchInteractor
import com.mishok.polygo.domain.search.SearchInteractorImpl
import com.mishok.polygo.utils.CoroutineScopeIO
import com.mishok.polygo.utils.CoroutineScopeMain
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import javax.inject.Provider
import javax.inject.Singleton

@Module
class AppModule {

    @Module
    companion object {

        @Provides
        @Singleton
        fun provideCoroutineScopeIO(): CoroutineScope = CoroutineScope(Dispatchers.IO)

        @CoroutineScopeMain
        @Provides
        fun provideCoroutineScopeMain(): CoroutineScope = CoroutineScope(Dispatchers.Main)
    }

}