package com.mishok.core_db_api.providers

import com.mishok.core_db_api.models.LocalBuildings
import com.yandex.mapkit.geometry.Point
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharedFlow

interface BuildingsProvider {

    fun saveBuildings(buildings: List<LocalBuildings>)
    fun addBookmark(buildingId: Long)
    fun removeBookmark(buildingId: Long)


    suspend fun getSearchedBuildings(query: String): Flow<List<LocalBuildings>>
    suspend fun getAllBuildings(): Flow<List<LocalBuildings>>
    suspend fun getBuildingsByPoint(point: Point): Flow<List<LocalBuildings>>
    suspend fun getSavedBuildings(): Flow<List<LocalBuildings>>
}