package com.mishok.core_db_impl.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mishok.core_db_api.models.LocalBuildingInfo
import com.mishok.core_db_api.models.LocalEmployees
import kotlinx.coroutines.flow.Flow

@Dao
interface BuildingInfoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBuildingInfo(buildings: List<LocalBuildingInfo>)

    @Query(value = LocalBuildingInfo.QUERY_GET_BY_BUILDING_ID)
    fun getAllBuildingInfoByBuildingId(): Flow<List<LocalBuildingInfo>>

    @Query(value = LocalBuildingInfo.QUERY_UPDATE_BOOKMARK)
    fun updateBookmark(buildingInfoId: Long, state: Boolean)

}