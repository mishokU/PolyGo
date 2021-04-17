package com.mishok.polygo.ui.bookmarks

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
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class BookmarksViewModel @Inject constructor(
    private val searchInteractor: SearchInteractor,
    private val bookmarksInteractor: BookmarksInteractor,
    private val coroutineScope: CoroutineScope
) : BaseViewModelImpl<BookmarksState>(), OnBuildingBookmarkClickCallback,
    OnEmployeeBookmarkClickCallback,
    OnEmployeeClickCallback {

    override val initialState: BookmarksState = BookmarksState()

    fun loadBookmarks(filter: SearchFilter) {
        coroutineScope.launch {
            when (filter) {
                SearchFilter.ALL -> {
                    bookmarksInteractor.loadAllBookmarks().collect {
                        switchDispatcher(it)
                    }
                }
                SearchFilter.EMPLOYEE -> {
                    bookmarksInteractor.loadEmployeesBookmarks().collect {
                        switchDispatcher(it)
                    }
                }
                SearchFilter.BUILDINGS -> {
                    bookmarksInteractor.loadBuildingsBookmarks().collect {
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

    fun search(query: String) {
        coroutineScope.launch {
            searchInteractor.search(query).collect {
                switchDispatcher(it)
            }
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
            loadBookmarks(state.lastFilter)
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
            loadBookmarks(state.lastFilter)
        }
    }

    override fun onEmployeeClick(employee: CreateAdapterListItem.EmployeeItem) {
        state = state.copy(employee = employee)
    }

    fun resetEmployee() {
        state = state.copy(employee = null)
    }
}