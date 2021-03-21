package com.mishok.polygo.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.mishok.polygo.base.route.RouteDestination
import com.mishok.polygo.db.api.providers.SearchProvider
import com.mishok.polygo.domain.search.SearchInteractor
import com.mishok.polygo.domain.search.SearchInteractorImpl
import com.mishok.polygo.ui.search.SearchFragment
import com.mishok.polygo.ui.search.SearchViewModel
import com.mishok.polygo.utils.CoroutineScopeIO
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module(includes = [
    SearchModule.ProvideViewModel::class
])
abstract class SearchModule {

    @ContributesAndroidInjector(modules = [InjectViewModel::class])
    abstract fun contributeSearchFragment(): SearchFragment

    @Module
    class InjectViewModel {

        @Provides
        fun provideFeatureViewModel(
                factory: ViewModelProvider.Factory,
                target: SearchFragment
        ) = ViewModelProvider(target, factory).get(SearchViewModel::class.java)

    }

    @Module(includes = [AppModule::class])
    class ProvideViewModel {

        @Provides
        @IntoMap
        @ViewModelKey(SearchViewModel::class)
        fun provideFeatureViewModel(
                expensive: SearchInteractor,
                coroutineScope: CoroutineScope
        ): ViewModel = SearchViewModel(expensive, coroutineScope)

    }

    companion object {

        @Provides
        @Singleton
        fun provideSearchInteractor(searchProvider: SearchProvider): SearchInteractor = SearchInteractorImpl(searchProvider)

    }
}