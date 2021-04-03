package com.mishok.polygo.domain.search

import com.mishok.polygo.db.api.models.LocalBuildings
import com.mishok.polygo.db.api.models.LocalEmployees
import com.mishok.polygo.ui.base.CreateAdapterListItem
import kotlinx.coroutines.flow.Flow

interface SearchInteractor {
    suspend fun loadAllSearching(): Flow<List<CreateAdapterListItem>>
    suspend fun addSearching(list: MutableList<LocalEmployees>)
    suspend fun addBuildings(buildings: MutableList<LocalBuildings>)
}