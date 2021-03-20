package com.mishok.polygo.domain.search

import com.mishok.polygo.ui.base.CreateAdapterListItem
import kotlinx.coroutines.flow.Flow

interface SearchInteractor {
    suspend fun loadAllSearching(): Flow<List<CreateAdapterListItem.SearchItem>>
}