package com.mishok.polygo.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mishok.polygo.domain.bookmarks.BookmarksInteractor
import com.mishok.polygo.domain.bookmarks.BookmarksInteractorImpl
import com.mishok.polygo.ui.bookmarks.BookmarksFragment
import com.mishok.polygo.ui.bookmarks.BookmarksViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap
import kotlinx.coroutines.CoroutineScope

@Module(
    includes = [
        BookmarksModule.ProvideViewModel::class
    ]
)
abstract class BookmarksModule {

    @ContributesAndroidInjector(modules = [InjectViewModel::class])
    abstract fun contributeSearchFragment(): BookmarksFragment

    @Binds
    abstract fun bindBookmarksInteractor(impl: BookmarksInteractorImpl): BookmarksInteractor

    @Module
    class InjectViewModel {

        @Provides
        fun provideFeatureViewModel(
            factory: ViewModelProvider.Factory,
            target: BookmarksFragment
        ) = ViewModelProvider(target, factory).get(BookmarksViewModel::class.java)

    }

    @Module(includes = [AppModule::class])
    class ProvideViewModel {

        @Provides
        @IntoMap
        @ViewModelKey(BookmarksViewModel::class)
        fun provideFeatureViewModel(
            expensive: BookmarksInteractor,
            coroutineScope: CoroutineScope
        ): ViewModel = BookmarksViewModel(expensive, coroutineScope)

    }

}