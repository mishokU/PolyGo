package com.mishok.core_db_impl.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mishok.core_db_api.models.LocalBuildingInfo
import kotlinx.coroutines.flow.Flow

@Dao
interface BuildingInfoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBuildingInfo(buildings: List<LocalBuildingInfo>)

    @Query(value = LocalBuildingInfo.QUERY_GET_BY_BUILDING_ID)
    fun getAllBuildingInfoByBuildingId(buildingId: Long): Flow<List<LocalBuildingInfo>>

    @Query(value = LocalBuildingInfo.QUERY_GET_BY_CATEGORY)
    fun filterBuildingByCategory(buildingId: Long, category: String): Flow<List<LocalBuildingInfo>>

    @Query(value = LocalBuildingInfo.QUERY_UPDATE_BOOKMARK)
    fun updateBookmark(buildingInfoId: Long, state: Boolean)

}