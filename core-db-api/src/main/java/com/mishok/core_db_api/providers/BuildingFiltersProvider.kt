package com.mishok.core_db_api.providers

import com.mishok.core_db_api.models.LocalBuildingFilters
import kotlinx.coroutines.flow.Flow

interface BuildingFiltersProvider {
    fun saveBuildingFilters(filters: List<LocalBuildingFilters>)
    suspend fun getAllFilters(): Flow<List<LocalBuildingFilters>>
}