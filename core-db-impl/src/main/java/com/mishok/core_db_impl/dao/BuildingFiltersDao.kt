package com.mishok.core_db_impl.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mishok.core_db_api.models.LocalBuildingFilters
import kotlinx.coroutines.flow.Flow

@Dao
interface BuildingFiltersDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFilters(filters: List<LocalBuildingFilters>)

    @Query(value = LocalBuildingFilters.QUERY_GET_ALL)
    fun getAllFilters(): Flow<List<LocalBuildingFilters>>
}