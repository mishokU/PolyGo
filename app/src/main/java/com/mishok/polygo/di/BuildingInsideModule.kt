package com.mishok.polygo.di


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mishok.polygo.domain.building_inside.BuildingInsideInteractor
import com.mishok.polygo.domain.building_inside.BuildingInsideInteractorImpl
import com.mishok.polygo.ui.building_inside.BuildingInsideFragment
import com.mishok.polygo.ui.building_inside.BuildingInsideViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap
import kotlinx.coroutines.CoroutineScope

@Module(
    includes = [
        BuildingInsideModule.ProvideViewModel::class
    ]
)
abstract class BuildingInsideModule {

    @ContributesAndroidInjector(modules = [InjectViewModel::class])
    abstract fun contributeBuildingInsideFragment(): BuildingInsideFragment

    @Binds
    abstract fun bindBuildingInsideInteractor(impl: BuildingInsideInteractorImpl): BuildingInsideInteractor

    @Module
    class InjectViewModel {

        @Provides
        fun provideFeatureViewModel(
            factory: ViewModelProvider.Factory,
            target: BuildingInsideFragment
        ) = ViewModelProvider(target, factory).get(BuildingInsideViewModel::class.java)

    }

    @Module(includes = [AppModule::class])
    class ProvideViewModel {

        @Provides
        @IntoMap
        @ViewModelKey(BuildingInsideViewModel::class)
        fun provideFeatureViewModel(
            expensive: BuildingInsideInteractor,
            coroutineScope: CoroutineScope
        ): ViewModel = BuildingInsideViewModel(expensive, coroutineScope)

    }

}