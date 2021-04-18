package com.mishok.core_db_impl.providers

import com.mishok.core_db_api.models.LocalBuildingInfo
import com.mishok.core_db_api.providers.BuildingInfoProvider
import com.mishok.core_db_impl.dao.BuildingInfoDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class BuildingInfoProviderImpl @Inject constructor(
    private val buildingInfoDao: BuildingInfoDao
) : BuildingInfoProvider {

    override fun saveBuildingInfo(buildings: List<LocalBuildingInfo>) {
        buildingInfoDao.insertBuildingInfo(buildings)
    }

    override fun addBookmark(buildingInfoId: Long) {
        buildingInfoDao.updateBookmark(buildingInfoId, true)
    }

    override fun removeBookmark(buildingInfoId: Long) {
        buildingInfoDao.updateBookmark(buildingInfoId, false)
    }

    override suspend fun getAllBuildingInfoByBuildingId(buildingId: Long)
            : Flow<List<LocalBuildingInfo>> {
        return buildingInfoDao.getAllBuildingInfoByBuildingId(buildingId)
    }

    override suspend fun filterBuildingInfoByCategory(buildingId: Long, category: String)
            : Flow<List<LocalBuildingInfo>> {
        return buildingInfoDao.filterBuildingByCategory(buildingId, category)
    }


}