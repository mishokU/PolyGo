package com.mishok.core_db_impl.providers

import com.mishok.core_db_api.models.LocalEmployees
import com.mishok.core_db_api.models.LocalSearching
import com.mishok.core_db_api.providers.SearchProvider
import com.mishok.core_db_impl.dao.EmployeeDao
import com.mishok.core_db_impl.dao.SearchDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchProviderImpl @Inject constructor(
    private val searchDao: SearchDao,
    private val employeeDao: EmployeeDao
) : SearchProvider {

    override fun saveSearch(search: LocalSearching) {
        searchDao.insert(search)
    }

    override suspend fun getAllSearching(): Flow<List<LocalSearching>> {
        return searchDao.getAllSearching()
    }

    override suspend fun addSearching(list: MutableList<LocalEmployees>) {
        employeeDao.insertEmployees(list)
    }
}