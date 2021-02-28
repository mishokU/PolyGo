package com.mishok.core_impl.di

import android.app.Application
import com.mishok.core_api.di.AppVersion
import com.mishok.core_api.di.CoreApi
import com.mishok.core_api.di.IsDebug
import dagger.BindsInstance
import dagger.Component

@Component(
    modules = [CoreModule::class],
    dependencies = [CoreDependencies::class]
)
interface CoreComponent : CoreApi {

    @Component
    interface CoreDependenciesComponent : CoreDependencies {

        @Component.Factory
        interface Factory {
            fun create(
                @BindsInstance application: Application,
                @BindsInstance @IsDebug isDebug: Boolean,
                @BindsInstance @AppVersion appVersion: String
            ): CoreDependenciesComponent
        }
    }
}