package com.mishok.polygo.db.impl.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mishok.polygo.db.api.models.LocalSearching
import kotlinx.coroutines.flow.Flow

@Dao
interface SearchDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(search: LocalSearching)

    @Query(value = LocalSearching.QUERY_GET_ALL)
    fun getAllSearching(): Flow<List<LocalSearching>>

}