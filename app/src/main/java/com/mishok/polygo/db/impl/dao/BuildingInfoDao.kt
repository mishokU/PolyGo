package com.mishok.polygo.db.impl.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mishok.polygo.db.api.models.LocalBuildingInfo
import com.mishok.polygo.db.api.models.LocalBuildings
import kotlinx.coroutines.flow.Flow

@Dao
interface BuildingInfoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBuildingInfo(buildings: List<LocalBuildingInfo>)

    @Query(value = LocalBuildingInfo.QUERY_GET_BY_BUILDING_ID)
    fun getAllBuildingInfoByBuildingId(buildingId: Long): Flow<List<LocalBuildingInfo>>

}