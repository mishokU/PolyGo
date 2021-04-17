package com.mishok.polygo.ui.search

import androidx.lifecycle.LifecycleObserver
import com.mishok.core_db_api.models.LocalBuildings
import com.mishok.core_db_api.models.LocalEmployees
import com.mishok.polygo.base.BaseViewModelImpl
import com.mishok.polygo.domain.bookmarks.BookmarksInteractor
import com.mishok.polygo.domain.search.SearchInteractor
import com.mishok.polygo.ui.base.CreateAdapterListItem
import com.mishok.polygo.ui.search.adapter.OnBuildingBookmarkClickCallback
import com.mishok.polygo.ui.search.adapter.OnEmployeeBookmarkClickCallback
import com.mishok.polygo.ui.search.adapter.OnEmployeeClickCallback
import com.mishok.polygo.ui.search.adapter.SearchCallback
import com.mishok.polygo.utils.filter.SearchFilter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SearchViewModel @Inject constructor(
    private val searchInteractor: SearchInteractor,
    private val bookmarksInteractor: BookmarksInteractor,
    private val coroutineScope: CoroutineScope
) : BaseViewModelImpl<SearchState>(),
    LifecycleObserver,
    OnBuildingBookmarkClickCallback,
    OnEmployeeBookmarkClickCallback,
    OnEmployeeClickCallback {

    override val initialState: SearchState = SearchState()

    fun loadSearching(filter: SearchFilter) {
        coroutineScope.launch {
            when (filter) {
                SearchFilter.ALL -> {
                    searchInteractor.loadAllSearching().collect {
                        switchDispatcher(it)
                    }
                }
                SearchFilter.EMPLOYEE -> {
                    searchInteractor.loadEmployees().collect {
                        switchDispatcher(it)
                    }
                }
                SearchFilter.BUILDINGS -> {
                    searchInteractor.loadBuildings().collect {
                        switchDispatcher(it)
                    }
                }
            }
            withContext(Dispatchers.Main) {
                state = state.copy(lastFilter = filter)
            }
        }
    }

    private suspend fun switchDispatcher(it: List<CreateAdapterListItem>) {
        withContext(Dispatchers.Main) {
            state = state.copy(list = it)
        }
    }

    override fun onBuildingBookmarkClick(item: CreateAdapterListItem.BuildingItem) {
        coroutineScope.launch {
            if (item.inBookmark) {
                bookmarksInteractor.removeBuildingBookmark(item.id)
            } else {
                bookmarksInteractor.addBuildingBookmark(item.id)
            }
        }.invokeOnCompletion {
            loadSearching(state.lastFilter)
        }
    }

    override fun onEmployeeBookmarkClick(item: CreateAdapterListItem.EmployeeItem) {
        coroutineScope.launch {
            if (item.inBookmark) {
                bookmarksInteractor.removeEmployeeBookmark(item.id)
            } else {
                bookmarksInteractor.addEmployeeBookmark(item.id)
            }
        }.invokeOnCompletion {
            loadSearching(state.lastFilter)
        }
    }

    override fun onEmployeeClick(employee: CreateAdapterListItem.EmployeeItem) {
        state = state.copy(employee = employee)
    }

    fun resetEmployee() {
        state = state.copy(employee = null)
    }

    fun search(query: String) {
        coroutineScope.launch {
            searchInteractor.search(query).collect {
                switchDispatcher(it)
            }
        }
    }

}