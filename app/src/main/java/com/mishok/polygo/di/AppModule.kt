package com.mishok.polygo.di

import com.mishok.polygo.utils.CoroutineScopeIO
import com.mishok.polygo.utils.CoroutineScopeMain
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

@Module
class AppModule {

    @CoroutineScopeIO
    @Provides
    fun provideCoroutineScopeIO() = CoroutineScope(Dispatchers.IO)

    @CoroutineScopeMain
    @Provides
    fun provideCoroutineScopeMain() = CoroutineScope(Dispatchers.Main)


}