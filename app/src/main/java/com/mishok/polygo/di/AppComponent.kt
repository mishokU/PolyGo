package com.mishok.polygo.di

import com.mishok.core_api.di.CoreApi
import com.mishok.polygo.app.PolyGoApplication
import com.mishok.polygo.db.api.di.DbCoreApi
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(
        modules = [
            AndroidInjectionModule::class,
            AppModule::class
        ],
        dependencies = [
            DbCoreApi::class,
            CoreApi::class
        ]
)
interface AppComponent :
        CoreApi,
        DbCoreApi,
        AndroidInjector<PolyGoApplication> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: PolyGoApplication): Builder

        fun dbCoreApi(dbCoreApi: DbCoreApi): Builder
        fun coreApi(coreApi: CoreApi): Builder
        fun build(): AppComponent
    }

    override fun inject(instance: PolyGoApplication)
}