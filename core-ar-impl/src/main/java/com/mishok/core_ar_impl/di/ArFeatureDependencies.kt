package com.mishok.core_ar_impl.di

import com.mishok.core_db_api.providers.BuildingPathProvider
import kotlinx.coroutines.CoroutineScope

interface ArFeatureDependencies {

    fun provideBuildingPathProvider(): BuildingPathProvider

}