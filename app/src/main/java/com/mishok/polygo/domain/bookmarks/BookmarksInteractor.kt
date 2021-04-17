package com.mishok.polygo.domain.bookmarks

import com.mishok.polygo.ui.base.CreateAdapterListItem
import kotlinx.coroutines.flow.Flow

interface BookmarksInteractor {
    suspend fun loadAllBookmarks(): Flow<List<CreateAdapterListItem>>
    suspend fun loadEmployeesBookmarks(): Flow<List<CreateAdapterListItem>>
    suspend fun loadBuildingsBookmarks(): Flow<List<CreateAdapterListItem>>

    suspend fun addBuildingBookmark(buildingId: Long)
    suspend fun addEmployeeBookmark(employeeId: Long)

    suspend fun removeBuildingBookmark(buildingId: Long)
    suspend fun removeEmployeeBookmark(employeeId: Long)

    suspend fun addBuildingInfoBookmark(buildingInfoId: Long)
    suspend fun removeBuildingInfoBookmark(buildingInfoId: Long)
}