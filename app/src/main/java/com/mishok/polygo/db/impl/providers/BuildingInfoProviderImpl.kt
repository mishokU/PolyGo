package com.mishok.polygo.db.impl.providers

import com.mishok.polygo.db.api.models.LocalBuildingInfo
import com.mishok.polygo.db.api.models.LocalBuildings
import com.mishok.polygo.db.api.providers.BuildingInfoProvider
import com.mishok.polygo.db.api.providers.BuildingsProvider
import com.mishok.polygo.db.impl.dao.BuildingDao
import com.mishok.polygo.db.impl.dao.BuildingInfoDao
import com.yandex.mapkit.geometry.Point
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class BuildingInfoProviderImpl @Inject constructor(
    private val buildingInfoDao: BuildingInfoDao
) : BuildingInfoProvider {

    override fun saveBuildingInfo(buildings: List<LocalBuildingInfo>) {
        buildingInfoDao.insertBuildingInfo(buildings)
    }

    override suspend fun getAllBuildingInfoByBuildingId(buildingId: Long): Flow<List<LocalBuildingInfo>> {
        return buildingInfoDao.getAllBuildingInfoByBuildingId(buildingId)
    }


}