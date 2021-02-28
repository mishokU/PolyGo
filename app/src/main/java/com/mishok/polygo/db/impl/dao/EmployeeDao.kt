package com.mishok.polygo.db.impl.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mishok.polygo.db.api.models.LocalEmployees
import kotlinx.coroutines.flow.Flow

@Dao
interface EmployeeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertEmployees(employees: List<LocalEmployees>)

    @Query(value = LocalEmployees.QUERY_GET_ALL)
    fun getAllEmployee(): Flow<List<LocalEmployees>>
}