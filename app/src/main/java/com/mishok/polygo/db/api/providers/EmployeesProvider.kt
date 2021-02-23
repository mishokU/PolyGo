package com.mishok.polygo.db.api.providers

import com.mishok.polygo.db.api.models.LocalEmployees
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.flow.Flow

interface EmployeesProvider {

    fun saveEmployees(employees: List<LocalEmployees>): Flow<Long>

    fun getAllEmployee(): Flow<List<LocalEmployees>>
}