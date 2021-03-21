package com.mishok.polygo.ui.search

import androidx.lifecycle.LifecycleObserver
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

class SearchViewModel @Inject constructor(
        private val searchInteractor: SearchInteractor,
        private val coroutineScope: CoroutineScope
) : BaseViewModelImpl<SearchState>(), LifecycleObserver, SearchCallback {

    override val initialState: SearchState = SearchState()

    fun loadSearching() {
        coroutineScope.launch {
            searchInteractor.loadAllSearching().collect {
                withContext(Dispatchers.Main) {
                    state = state.copy(list = it)
                }
            }
        }
    }

    override fun onSearchClick(search: CreateAdapterListItem.SearchItem) {

    }

}