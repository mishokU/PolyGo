package com.mishok.core_db_impl.providers

import com.mishok.core_db_api.models.LocalEmployees
import com.mishok.core_db_api.providers.EmployeesProvider
import com.mishok.core_db_impl.dao.EmployeeDao
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

    override suspend fun getSavedEmployees(): Flow<List<LocalEmployees>> {
        return employeeDao.getSavedEmployees()
    }

}