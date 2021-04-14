package com.mishok.core_db_api.providers

import com.mishok.core_db_api.models.LocalBuildings
import com.yandex.mapkit.geometry.Point
import kotlinx.coroutines.flow.Flow

interface BuildingsProvider {

    fun saveBuildings(buildings: List<LocalBuildings>)

    suspend fun getAllBuildings(): Flow<List<LocalBuildings>>
    suspend fun getBuildingsByPoint(point: Point): Flow<List<LocalBuildings>>
    suspend fun getSavedBuildings(): Flow<List<LocalBuildings>>
}