package com.mishok.polygo.domain.bookmarks

import com.mishok.polygo.db.api.models.LocalEmployees
import kotlinx.coroutines.flow.Flow

interface BookmarksInteractor {
    suspend fun loadAllBookmarks(): Flow<List<Any>>
    suspend fun removeBookmark(list: MutableList<LocalEmployees>)
}