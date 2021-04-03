package com.mishok.polygo.db.api.providers

import com.mishok.polygo.db.api.models.LocalEmployees
import com.mishok.polygo.db.api.models.LocalSearching
import kotlinx.coroutines.flow.Flow

interface SearchProvider {

    fun saveSearch(search: LocalSearching)

    suspend fun getAllSearching(): Flow<List<LocalSearching>>
    suspend fun addSearching(list: MutableList<LocalEmployees>)
}