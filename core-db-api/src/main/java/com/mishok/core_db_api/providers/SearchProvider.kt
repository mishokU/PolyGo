package com.mishok.core_db_api.providers

import com.mishok.core_db_api.models.LocalEmployees
import com.mishok.core_db_api.models.LocalSearching
import kotlinx.coroutines.flow.Flow

interface SearchProvider {

    fun saveSearch(search: LocalSearching)

    suspend fun getAllSearching(): Flow<List<LocalSearching>>
    suspend fun addSearching(list: MutableList<LocalEmployees>)
}