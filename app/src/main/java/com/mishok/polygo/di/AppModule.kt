package com.mishok.polygo.di

import com.mishok.polygo.utils.CoroutineScopeMain
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
class AppModule {

    companion object {

        @Provides
        @Singleton
        fun provideCoroutineScopeIO(): CoroutineScope = CoroutineScope(Dispatchers.IO)

        @CoroutineScopeMain
        @Provides
        fun provideCoroutineScopeMain(): CoroutineScope = CoroutineScope(Dispatchers.Main)
    }

}