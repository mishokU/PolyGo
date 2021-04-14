package com.mishok.core_db_impl.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mishok.core_db_api.models.LocalBuildings
import kotlinx.coroutines.flow.Flow

@Dao
interface BuildingDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBuildings(buildings: List<LocalBuildings>)

    @Query(value = LocalBuildings.QUERY_GET_ALL)
    fun getAllBuildings(): Flow<List<LocalBuildings>>

    @Query(value = LocalBuildings.QUERY_GET_SAVED)
    fun getSavedBuildings(): Flow<List<LocalBuildings>>

    @Query(value = LocalBuildings.QUERY_GET_BY_COORDINATES)
    fun getBuildingsByPoint(
            latitudeFrom: Double, latitudeTo: Double,
            longitudeFrom: Double, longitudeTo: Double
    ): Flow<List<LocalBuildings>>

}