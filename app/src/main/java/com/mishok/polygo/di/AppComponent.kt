package com.mishok.polygo.di

import com.mishok.polygo.db.api.di.DbCoreApi
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class
    ],
    dependencies = [
        DbCoreApi::class
    ]
)
interface AppComponent : DbCoreApi {

    @Component.Builder
    interface Builder {
        fun dbCoreApi(dbCoreApi: DbCoreApi): Builder
        fun build(): AppComponent
    }
}