package com.mishok.polygo.db.api.providers

import com.mishok.polygo.db.api.models.LocalSearching
import kotlinx.coroutines.flow.Flow

interface SearchProvider {

    suspend fun saveSearch(search: LocalSearching)

    suspend fun getAllSearching(): Flow<List<LocalSearching>>
}