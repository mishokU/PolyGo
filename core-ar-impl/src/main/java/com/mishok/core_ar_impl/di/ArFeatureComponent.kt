package com.mishok.core_ar_impl.di

import com.mishok.core_api.di.CoreApi
import com.mishok.core_ar_api.starter.ArFeatureConfiguration
import com.mishok.core_ar_api.starter.ArModuleApi
import com.mishok.core_ar_impl.ui.BaseArFragment
import com.mishok.core_db_api.di.DbCoreApi
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Component(
    dependencies = [ArFeatureDependencies::class],
    modules = [
        ArFeatureModule::class
    ]
)
@Singleton
interface ArFeatureComponent : ArModuleApi {

    fun inject(fragment: BaseArFragment)

    @Component(
        dependencies = [
            CoreApi::class,
            DbCoreApi::class
        ]
    )
    //Ad core database api
    interface ArFeatureDependenciesComponent : ArFeatureDependencies {
        @Component.Factory
        interface Factory {
            fun create(
                coreApi: CoreApi,
                dbCoreApi: DbCoreApi,
                @BindsInstance configuration: ArFeatureConfiguration
            ): ArFeatureDependenciesComponent
        }
    }
}