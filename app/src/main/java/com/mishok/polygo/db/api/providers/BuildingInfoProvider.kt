package com.mishok.polygo.db.api.providers

import com.mishok.polygo.db.api.models.LocalBuildingInfo
import com.mishok.polygo.db.api.models.LocalBuildings
import com.yandex.mapkit.geometry.Point
import kotlinx.coroutines.flow.Flow

interface BuildingInfoProvider {

    fun saveBuildingInfo(buildings: List<LocalBuildingInfo>)

    suspend fun getAllBuildingInfoByBuildingId(buildingId: Long): Flow<List<LocalBuildingInfo>>
}