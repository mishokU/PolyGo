package com.mishok.polygo.db.impl.providers

import androidx.lifecycle.LiveData
import com.mishok.polygo.db.api.models.LocalEmployees
import com.mishok.polygo.db.api.providers.EmployeesProvider
import com.mishok.polygo.db.impl.dao.EmployeeDao
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class EmployeeProviderImpl @Inject constructor(
    private val employeeDao: EmployeeDao
) : EmployeesProvider {

    override fun saveEmployees(employees: List<LocalEmployees>) {
        return employeeDao.insertEmployees(employees)
    }

    override suspend fun getAllEmployee(): Flow<List<LocalEmployees>> {
        return employeeDao.getAllEmployee()
    }

}