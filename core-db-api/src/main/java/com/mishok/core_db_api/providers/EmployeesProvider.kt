package com.mishok.core_db_api.providers

import com.mishok.core_db_api.models.LocalEmployees
import kotlinx.coroutines.flow.Flow

interface EmployeesProvider {

    fun saveEmployees(employees: List<LocalEmployees>)

    suspend fun getAllEmployee(): Flow<List<LocalEmployees>>
    suspend fun getSavedEmployees(): Flow<List<LocalEmployees>>
}