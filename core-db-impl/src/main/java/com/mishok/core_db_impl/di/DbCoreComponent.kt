package com.mishok.core_db_impl.di

import com.mishok.core_api.di.CoreApi
import com.mishok.core_db_api.di.DbCoreApi
import dagger.Component


@Component(
        dependencies = [
            DbCoreDependencies::class
        ],
        modules = [
            DbProvidersModule::class,
            DbCoreModule::class
        ]
)
interface DbCoreComponent : DbCoreApi {

    @Component(dependencies = [CoreApi::class])
    interface DbCoreDependenciesComponent : DbCoreDependencies
}