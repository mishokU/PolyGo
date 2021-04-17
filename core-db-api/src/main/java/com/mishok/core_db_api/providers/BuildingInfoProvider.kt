package com.mishok.core_db_api.providers

import com.mishok.core_db_api.models.LocalBuildingInfo
import kotlinx.coroutines.flow.Flow

interface BuildingInfoProvider {

    fun saveBuildingInfo(buildings: List<LocalBuildingInfo>)

    fun addBookmark(buildingInfoId: Long)
    fun removeBookmark(buildingInfoId: Long)

    suspend fun getAllBuildingInfoByBuildingId(buildingId: Long): Flow<List<LocalBuildingInfo>>
}