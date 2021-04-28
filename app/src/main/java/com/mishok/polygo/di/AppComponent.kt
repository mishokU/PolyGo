package com.mishok.polygo.di

import com.mishok.core_api.di.CoreApi
import com.mishok.core_db_api.di.DbCoreApi
import com.mishok.polygo.app.PolyGoApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        ActivityModule::class,
        FragmentModule::class,
        SearchModule::class,
        BookmarksModule::class,
        BuildingInsideModule::class,
        RadarModule::class,
        StartModule::class,
        UiModule::class,
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

    override fun inject(instance: PolyGoApplication)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: PolyGoApplication): Builder

        fun dbCoreApi(dbCoreApi: DbCoreApi): Builder
        fun coreApi(coreApi: CoreApi): Builder

        fun build(): AppComponent
    }
}