package com.mishok.polygo.ui.bookmarks

import com.mishok.polygo.base.BaseViewModelImpl
import com.mishok.polygo.domain.bookmarks.BookmarksInteractor
import com.mishok.polygo.ui.base.CreateAdapterListItem
import com.mishok.polygo.ui.search.adapter.SearchCallback
import com.mishok.polygo.utils.filter.SearchFilter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class BookmarksViewModel @Inject constructor(
    private val bookmarksInteractor: BookmarksInteractor,
    private val coroutineScope: CoroutineScope
) : BaseViewModelImpl<BookmarksState>(), SearchCallback {

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
        }
    }

    private suspend fun switchDispatcher(it: List<CreateAdapterListItem>) {
        withContext(Dispatchers.Main) {
            state = state.copy(list = it)
        }
    }

    override fun onBuildingClick(building: CreateAdapterListItem.BuildingItem) {
        state = state.copy(building = building)
    }

    override fun onEmployeeClick(employee: CreateAdapterListItem.EmployeeItem) {
        state = state.copy(employee = employee)
    }

}