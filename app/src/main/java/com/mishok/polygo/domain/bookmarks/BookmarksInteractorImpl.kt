package com.mishok.polygo.domain.bookmarks

import com.mishok.core_db_api.providers.BuildingInfoProvider
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
    private val employeesProvider: EmployeesProvider,
    private val buildingInfoProvider: BuildingInfoProvider
) : BookmarksInteractor {

    override suspend fun loadAllBookmarks(): Flow<List<CreateAdapterListItem>> {
        return combine(
            employeesProvider.getSavedEmployees(),
            buildingsProvider.getSavedBuildings(),
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

    override suspend fun addBuildingBookmark(buildingId: Long) {
        buildingsProvider.addBookmark(buildingId)
    }

    override suspend fun addEmployeeBookmark(employeeId: Long) {
        employeesProvider.addBookmark(employeeId)
    }

    override suspend fun removeBuildingBookmark(buildingId: Long) {
        buildingsProvider.removeBookmark(buildingId)
    }

    override suspend fun removeEmployeeBookmark(employeeId: Long) {
        employeesProvider.removeBookmark(employeeId)
    }

    override suspend fun addBuildingInfoBookmark(buildingInfoId: Long) {
        buildingInfoProvider.addBookmark(buildingInfoId)
    }

    override suspend fun removeBuildingInfoBookmark(buildingInfoId: Long) {
        buildingInfoProvider.removeBookmark(buildingInfoId)
    }
}