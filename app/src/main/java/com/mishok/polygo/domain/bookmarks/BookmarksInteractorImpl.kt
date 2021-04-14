package com.mishok.polygo.domain.bookmarks

import com.mishok.core_db_api.models.LocalEmployees
import com.mishok.core_db_api.providers.BuildingsProvider
import com.mishok.core_db_api.providers.EmployeesProvider
import com.mishok.polygo.domain.mappers.toUIBuildingModel
import com.mishok.polygo.domain.mappers.toUIEmployeeModel
import com.mishok.polygo.ui.base.CreateAdapterListItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class BookmarksInteractorImpl @Inject constructor(
    private val buildingsProvider: BuildingsProvider,
    private val employeesProvider: EmployeesProvider
) : BookmarksInteractor {

    override suspend fun loadAllBookmarks(): Flow<List<CreateAdapterListItem>> {
        return combine(
                employeesProvider.getAllEmployee(),
                buildingsProvider.getAllBuildings()
        ) { employee, buildings ->
            employee.toUIEmployeeModel() + buildings.toUIBuildingModel()
        }
    }

    override suspend fun loadEmployeesBookmarks(): Flow<List<CreateAdapterListItem>> {
        return employeesProvider.getSavedEmployees().map {
            it.toUIEmployeeModel()
        }
    }

    override suspend fun loadBuildingsBookmarks(): Flow<List<CreateAdapterListItem>> {
        return buildingsProvider.getSavedBuildings().map {
            it.toUIBuildingModel()
        }
    }

    override suspend fun removeBookmark(list: MutableList<LocalEmployees>) {

    }

}