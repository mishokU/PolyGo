package com.mishok.core_db_impl.providers

import com.mishok.core_db_api.models.LocalBuildingPath
import com.mishok.core_db_api.providers.BuildingPathProvider
import com.mishok.core_db_impl.dao.BuildingPathDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class BuildingPathProviderImpl @Inject constructor(
    private val buildingPathDao: BuildingPathDao
) : BuildingPathProvider {

    override fun saveBuildingPaths(paths: List<LocalBuildingPath>) {
        buildingPathDao.insertAll(paths)
    }

    override fun getPathByBuildingId(buildingId: Long): Flow<List<LocalBuildingPath>> {
        return buildingPathDao.getPathByBuildingId(buildingId)
    }

}