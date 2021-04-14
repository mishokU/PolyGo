package com.mishok.core_db_api.providers

import com.mishok.core_db_api.models.LocalBuildingInfo
import kotlinx.coroutines.flow.Flow

interface BuildingInfoProvider {

    fun saveBuildingInfo(buildings: List<LocalBuildingInfo>)

    suspend fun getAllBuildingInfoByBuildingId(buildingId: Long): Flow<List<LocalBuildingInfo>>
}