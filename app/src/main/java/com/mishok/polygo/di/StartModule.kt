package com.mishok.polygo.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mishok.polygo.domain.building_inside.BuildingInsideInteractor
import com.mishok.polygo.domain.building_inside.BuildingInsideInteractorImpl
import com.mishok.polygo.domain.start.StartInteractor
import com.mishok.polygo.domain.start.StartInteractorImpl
import com.mishok.polygo.ui.building_inside.BuildingInsideFragment
import com.mishok.polygo.ui.building_inside.BuildingInsideViewModel
import com.mishok.polygo.ui.start.StartFragment
import com.mishok.polygo.ui.start.StartViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap
import kotlinx.coroutines.CoroutineScope

@Module(
    includes = [
        StartModule.ProvideViewModel::class
    ]
)
abstract class StartModule {

    @ContributesAndroidInjector(modules = [InjectViewModel::class])
    abstract fun contributeStartFragment(): StartFragment

    @Binds
    abstract fun bindStartInteractor(impl: StartInteractorImpl): StartInteractor

    @Module
    class InjectViewModel {

        @Provides
        fun provideFeatureViewModel(
            factory: ViewModelProvider.Factory,
            target: StartFragment
        ) = ViewModelProvider(target, factory).get(StartViewModel::class.java)

    }

    @Module(includes = [AppModule::class])
    class ProvideViewModel {

        @Provides
        @IntoMap
        @ViewModelKey(StartViewModel::class)
        fun provideFeatureViewModel(
            expensive: StartInteractor,
            coroutineScope: CoroutineScope
        ): ViewModel = StartViewModel(expensive, coroutineScope)

    }

}