package com.mishok.polygo.domain.bookmarks

import com.mishok.polygo.db.api.models.LocalEmployees
import com.mishok.polygo.db.api.providers.BuildingsProvider
import com.mishok.polygo.db.api.providers.EmployeesProvider
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class BookmarksInteractorImpl @Inject constructor(
    private val buildingsProvider: BuildingsProvider,
    private val employeesProvider: EmployeesProvider
) : BookmarksInteractor {

    override suspend fun loadAllBookmarks(): Flow<List<Any>> {
        return buildingsProvider.getAllBuildings()
    }

    override suspend fun removeBookmark(list: MutableList<LocalEmployees>) {

    }

}