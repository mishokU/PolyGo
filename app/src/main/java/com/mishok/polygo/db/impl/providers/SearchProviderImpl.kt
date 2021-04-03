package com.mishok.polygo.db.impl.providers

import com.mishok.polygo.db.api.models.LocalEmployees
import com.mishok.polygo.db.api.models.LocalSearching
import com.mishok.polygo.db.api.providers.SearchProvider
import com.mishok.polygo.db.impl.dao.EmployeeDao
import com.mishok.polygo.db.impl.dao.SearchDao
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