package com.mishok.polygo.domain.search

import com.mishok.polygo.db.api.models.LocalBuildings
import com.mishok.polygo.db.api.models.LocalEmployees
import com.mishok.polygo.db.api.providers.BuildingsProvider
import com.mishok.polygo.db.api.providers.EmployeesProvider
import com.mishok.polygo.db.api.providers.SearchProvider
import com.mishok.polygo.domain.mappers.toUIBuildingModel
import com.mishok.polygo.domain.mappers.toUIEmployeeModel
import com.mishok.polygo.ui.base.CreateAdapterListItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SearchInteractorImpl @Inject constructor(
    private val searchProvider: SearchProvider,
    private val employeesProvider: EmployeesProvider,
    private val buildingsProvider: BuildingsProvider
) : SearchInteractor {

    override suspend fun loadAllSearching(): Flow<List<CreateAdapterListItem>> {
        return combine(
                employeesProvider.getAllEmployee(),
                buildingsProvider.getAllBuildings()
        ) { employee, buildings ->
            employee.toUIEmployeeModel() + buildings.toUIBuildingModel()
        }
    }

    override suspend fun loadEmployees(): Flow<List<CreateAdapterListItem>> {
        return employeesProvider.getAllEmployee().map {
            it.toUIEmployeeModel()
        }
    }

    override suspend fun loadBuildings(): Flow<List<CreateAdapterListItem>> {
        return buildingsProvider.getAllBuildings().map {
            it.toUIBuildingModel()
        }
    }

    override suspend fun addSearching(list: MutableList<LocalEmployees>) {
        searchProvider.addSearching(list)
    }

    override suspend fun addBuildings(buildings: MutableList<LocalBuildings>) {
        buildingsProvider.saveBuildings(buildings)
    }
}
