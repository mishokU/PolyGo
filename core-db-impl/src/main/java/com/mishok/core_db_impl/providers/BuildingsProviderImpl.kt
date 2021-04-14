package com.mishok.core_db_impl.providers

import com.mishok.core_db_api.models.LocalBuildings
import com.mishok.core_db_api.providers.BuildingsProvider
import com.mishok.core_db_impl.dao.BuildingDao
import com.yandex.mapkit.geometry.Point
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class BuildingsProviderImpl @Inject constructor(
    private val buildingDao: BuildingDao
) : BuildingsProvider {

    override fun saveBuildings(buildings: List<LocalBuildings>) {
        buildingDao.insertBuildings(buildings)
    }

    override suspend fun getAllBuildings(): Flow<List<LocalBuildings>> {
        return buildingDao.getAllBuildings()
    }

    override suspend fun getBuildingsByPoint(point: Point): Flow<List<LocalBuildings>> {
        return buildingDao.getBuildingsByPoint(
                point.latitude - LocalBuildings.DELTA_DISTANCE,
                point.latitude + LocalBuildings.DELTA_DISTANCE,
                point.longitude - LocalBuildings.DELTA_DISTANCE,
                point.longitude + LocalBuildings.DELTA_DISTANCE
        )
    }

    override suspend fun getSavedBuildings(): Flow<List<LocalBuildings>> {
        return buildingDao.getSavedBuildings()
    }
}