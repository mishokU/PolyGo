package com.mishok.polygo.di

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mishok.polygo.domain.map.MapInteractor
import com.mishok.polygo.ui.radar.RadarFragment
import com.mishok.polygo.ui.radar.RadarViewModel
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap
import kotlinx.coroutines.CoroutineScope

@Module(
    includes = [
        RadarModule.ProvideViewModel::class
    ]
)
abstract class RadarModule {

    @ContributesAndroidInjector(modules = [InjectViewModel::class])
    abstract fun contributeRadarFragment(): RadarFragment

    @Module
    class InjectViewModel {

        @Provides
        fun provideFeatureViewModel(
            factory: ViewModelProvider.Factory,
            target: RadarFragment
        ) = ViewModelProvider(target, factory).get(RadarViewModel::class.java)

    }

    @Module(includes = [AppModule::class])
    class ProvideViewModel {

        @Provides
        @IntoMap
        @ViewModelKey(RadarViewModel::class)
        fun provideFeatureViewModel(
            expensive: MapInteractor,
            coroutineScope: CoroutineScope
        ): ViewModel = RadarViewModel(expensive, coroutineScope)

    }

}