package com.mishok.core_db_impl.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mishok.core_db_api.models.LocalBuildingPath
import kotlinx.coroutines.flow.Flow

@Dao
interface BuildingPathDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(paths: List<LocalBuildingPath>)

    @Query(LocalBuildingPath.QUERY_GET_BY_BUILDING_ID)
    fun getPathByBuildingId(buildingId: Long): Flow<List<LocalBuildingPath>>
}