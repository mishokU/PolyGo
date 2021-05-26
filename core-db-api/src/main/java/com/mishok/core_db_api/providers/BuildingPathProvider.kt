package com.mishok.core_db_api.providers

import com.mishok.core_db_api.models.LocalBuildingPath
import kotlinx.coroutines.flow.Flow

interface BuildingPathProvider {

    fun saveBuildingPaths(paths: List<LocalBuildingPath>)

    fun getPathByBuildingId(buildingId: Long): Flow<List<LocalBuildingPath>>

}