package com.mishok.polygo.domain.search

import com.mishok.core_db_api.models.LocalBuildings
import com.mishok.core_db_api.models.LocalEmployees
import com.mishok.polygo.ui.base.CreateAdapterListItem
import kotlinx.coroutines.flow.Flow

interface SearchInteractor {
    suspend fun loadAllSearching(): Flow<List<CreateAdapterListItem>>
    suspend fun loadEmployees(): Flow<List<CreateAdapterListItem>>
    suspend fun loadBuildings(): Flow<List<CreateAdapterListItem>>
    suspend fun addSearching(list: MutableList<LocalEmployees>)
    suspend fun addBuildings(buildings: MutableList<LocalBuildings>)
    suspend fun search(query: String): Flow<List<CreateAdapterListItem>>
    suspend fun searchBookmarks(query: String): Flow<List<CreateAdapterListItem>>
}