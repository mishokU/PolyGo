package com.mishok.polygo.app

import com.mishok.core_api.di.CoreApi
import com.mishok.core_impl.di.DaggerCoreComponent
import com.mishok.core_impl.di.DaggerCoreComponent_CoreDependenciesComponent
import com.mishok.polygo.BuildConfig
import com.mishok.polygo.db.api.di.DbCoreApi
import com.mishok.polygo.db.impl.di.DaggerDbCoreComponent
import com.mishok.polygo.db.impl.di.DaggerDbCoreComponent_DbCoreDependenciesComponent
import com.mishok.polygo.di.AppComponent
import com.mishok.polygo.di.DaggerAppComponent
import com.mishok.polygo.utils.ComponentRegistry

class ComponentInitializer(val app: PolyGoApplication) {

    fun initAppComponent(): AppComponent {
        return DaggerAppComponent
                .builder()
                .coreApi(getCoreApi())
                .dbCoreApi(getDbCoreApi())
                .application(app)
                .build()
    }

    private fun getDbCoreApi(): DbCoreApi {
        val dependencies = DaggerDbCoreComponent_DbCoreDependenciesComponent.builder()
                .coreApi(getCoreApi())
                .build()

        return DaggerDbCoreComponent.builder()
                .dbCoreDependencies(dependencies)
                .build()
                .registerComponent<DbCoreApi>()
    }

    private fun getCoreApi(): CoreApi {
        val dependencies = DaggerCoreComponent_CoreDependenciesComponent
                .factory()
                .create(app, BuildConfig.DEBUG, BuildConfig.VERSION_NAME)
        return DaggerCoreComponent.builder()
                .coreDependencies(dependencies)
                .build()
                .registerComponent<CoreApi>()
    }

    private inline fun <reified T : Any> T.registerComponent(): T {
        return if (ComponentRegistry.hasComponent<T>()) {
            ComponentRegistry.get()
        } else {
            ComponentRegistry.register { this }
            this
        }
    }
}