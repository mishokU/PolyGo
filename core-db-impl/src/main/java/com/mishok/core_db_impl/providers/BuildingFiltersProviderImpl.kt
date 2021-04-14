package com.mishok.core_db_impl.providers

import com.mishok.core_db_api.models.LocalBuildingFilters
import com.mishok.core_db_api.providers.BuildingFiltersProvider
import com.mishok.core_db_impl.dao.BuildingFiltersDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class BuildingFiltersProviderImpl @Inject constructor(
    private val buildingFiltersDao: BuildingFiltersDao
) : BuildingFiltersProvider {

    override fun saveBuildingFilters(filters: List<LocalBuildingFilters>) {
        buildingFiltersDao.insertFilters(filters)
    }

    override suspend fun getAllFilters(): Flow<List<LocalBuildingFilters>> {
        return buildingFiltersDao.getAllFilters()
    }


}