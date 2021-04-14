package com.mishok.core_ar_impl.starter

import androidx.fragment.app.Fragment
import com.mishok.common_registry.ComponentRegistry
import com.mishok.core_ar_api.starter.ArFeatureConfiguration
import com.mishok.core_ar_api.starter.ArModuleStarter
import com.mishok.core_ar_impl.R
import com.mishok.core_ar_impl.di.ArFeatureComponent
import com.mishok.core_ar_impl.di.DaggerArFeatureComponent
import com.mishok.core_ar_impl.di.DaggerArFeatureComponent_ArFeatureDependenciesComponent
import com.mishok.core_ar_impl.ui.BaseArFragment
import javax.inject.Inject

class ArModuleStarterImpl @Inject constructor() : ArModuleStarter {

    override fun startArFeature(configuration: ArFeatureConfiguration): Int {
        val dependencies =
            DaggerArFeatureComponent_ArFeatureDependenciesComponent.factory()
                .create(
                    coreApi = ComponentRegistry.get(),
                    dbCoreApi = ComponentRegistry.get(),
                    configuration = configuration
                )

        DaggerArFeatureComponent.builder()
            .arFeatureDependencies(dependencies)
            .build()
            .also {
                if (configuration.tag != null) {
                    ComponentRegistry.register<ArFeatureComponent>(
                        ownerTag = configuration.tag.toString()
                    ) { it }
                } else {
                    ComponentRegistry.register<ArFeatureComponent> { it }
                }
            }
        return R.id.sceneForm
    }

}