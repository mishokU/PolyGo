package com.mishok.polygo.di

import com.mishok.polygo.start.StartFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract fun contributeBaseFragment(): StartFragment
}