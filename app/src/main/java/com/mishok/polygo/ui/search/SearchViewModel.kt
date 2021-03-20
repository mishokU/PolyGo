package com.mishok.polygo.ui.search

import com.mishok.polygo.base.BaseViewModelImpl
import com.mishok.polygo.db.api.models.LocalSearching
import com.mishok.polygo.domain.search.SearchInteractor
import com.mishok.polygo.ui.base.CreateAdapterListItem
import com.mishok.polygo.ui.search.adapter.SearchCallback
import com.mishok.polygo.utils.CoroutineScopeIO
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SearchViewModel : BaseViewModelImpl<SearchState>(), SearchCallback {

    @Inject
    lateinit var searchInteractor: SearchInteractor

    @Inject
    @CoroutineScopeIO
    lateinit var coroutineScope: CoroutineScope

    override val initialState: SearchState = SearchState()

    init {
        loadSearching()
    }

    private fun loadSearching() {
        coroutineScope.launch {
            searchInteractor.loadAllSearching().collect {
                state = state.copy(list = it)
            }
        }
    }

    override fun onSearchClick(search: CreateAdapterListItem.SearchItem) {

    }

}