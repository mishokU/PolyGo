package com.mishok.core_ar_api.starter

import androidx.fragment.app.Fragment

interface ArModuleStarter {
    fun startArFeature(configuration: ArFeatureConfiguration): Int
}