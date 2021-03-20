package com.mishok.polygo.db.impl.dao

import androidx.room.Dao
import com.mishok.polygo.db.api.models.LocalSearching
import kotlinx.coroutines.flow.Flow

@Dao
interface SearchDao {

    suspend fun insert(search: LocalSearching)

    suspend fun getAllSearching(): Flow<List<LocalSearching>>

}