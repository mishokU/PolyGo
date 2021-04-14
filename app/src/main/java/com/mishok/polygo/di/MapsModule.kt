package com.mishok.polygo.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mishok.polygo.domain.map.MapInteractor
import com.mishok.polygo.domain.map.MapInteractorImpl
import com.mishok.polygo.ui.map.MapsFragment
import com.mishok.polygo.ui.map.MapsViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap
import kotlinx.coroutines.CoroutineScope

@Module(
    includes = [
        MapsModule.ProvideViewModel::class
    ]
)
abstract class MapsModule {

    @ContributesAndroidInjector(modules = [InjectViewModel::class])
    abstract fun contributeMapsFragment(): MapsFragment

    @Binds
    abstract fun bindMapsInteractor(impl: MapInteractorImpl): MapInteractor

    @Module
    class InjectViewModel {

        @Provides
        fun provideFeatureViewModel(
            factory: ViewModelProvider.Factory,
            target: MapsFragment
        ) = ViewModelProvider(target, factory).get(MapsViewModel::class.java)

    }

    @Module(includes = [AppModule::class])
    class ProvideViewModel {

        @Provides
        @IntoMap
        @ViewModelKey(MapsViewModel::class)
        fun provideFeatureViewModel(
            interactor: MapInteractor,
            coroutineScope: CoroutineScope
        ): ViewModel = MapsViewModel(interactor, coroutineScope)

    }

}