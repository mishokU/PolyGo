package com.mishok.polygo.domain.bookmarks

import com.mishok.core_db_api.models.LocalEmployees
import com.mishok.polygo.ui.base.CreateAdapterListItem
import kotlinx.coroutines.flow.Flow

interface BookmarksInteractor {
    suspend fun loadAllBookmarks(): Flow<List<CreateAdapterListItem>>
    suspend fun loadEmployeesBookmarks(): Flow<List<CreateAdapterListItem>>
    suspend fun loadBuildingsBookmarks(): Flow<List<CreateAdapterListItem>>
    suspend fun removeBookmark(list: MutableList<LocalEmployees>)
}