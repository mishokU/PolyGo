package com.mishok.polygo.domain.search

import com.mishok.polygo.db.api.models.LocalSearching
import com.mishok.polygo.db.api.providers.SearchProvider
import com.mishok.polygo.ui.base.CreateAdapterListItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SearchInteractorImpl @Inject constructor(
        private val searchProvider: SearchProvider
) : SearchInteractor {

    override suspend fun loadAllSearching(): Flow<List<CreateAdapterListItem.SearchItem>> {
        return searchProvider.getAllSearching().map {
            it.toUIModel()
        }
    }

}

private fun List<LocalSearching>.toUIModel(): List<CreateAdapterListItem.SearchItem> {
    return map {
        CreateAdapterListItem.SearchItem(
                id = it.id,
                title = it.title
        )
    }
}
