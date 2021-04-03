package com.mishok.polygo.db.impl.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mishok.polygo.db.api.models.LocalBuildings
import com.mishok.polygo.db.api.models.LocalEmployees
import kotlinx.coroutines.flow.Flow

@Dao
interface BuildingDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBuildings(buildings: List<LocalBuildings>)

    @Query(value = LocalBuildings.QUERY_GET_ALL)
    fun getAllBuildings(): Flow<List<LocalBuildings>>

    @Query(value = LocalBuildings.QUERY_GET_BY_COORDINATES)
    fun getBuildingsByPoint(
        latitudeFrom: Double, latitudeTo: Double,
        longitudeFrom: Double, longitudeTo: Double
    ): Flow<List<LocalBuildings>>

}