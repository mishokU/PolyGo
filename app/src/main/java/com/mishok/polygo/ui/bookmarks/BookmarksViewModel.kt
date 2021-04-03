package com.mishok.polygo.ui.bookmarks

import com.mishok.polygo.base.BaseViewModelImpl
import com.mishok.polygo.domain.bookmarks.BookmarksInteractor
import com.mishok.polygo.ui.base.CreateAdapterListItem
import com.mishok.polygo.ui.map.MapsState
import com.mishok.polygo.ui.search.adapter.SearchCallback
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class BookmarksViewModel @Inject constructor(
    private val bookmarksInteractor: BookmarksInteractor,
    private val coroutineScope: CoroutineScope
) : BaseViewModelImpl<BookmarksState>(), SearchCallback {

    override val initialState: BookmarksState = BookmarksState()

    fun loadBookmarks() {
        coroutineScope.launch {
            bookmarksInteractor.loadAllBookmarks()
        }
    }

    override fun onSearchClick(search: CreateAdapterListItem.SearchItem) {

    }

    override fun onBuildingClick(building: CreateAdapterListItem.BuildingItem) {

    }

    override fun onEmployeeClick(employee: CreateAdapterListItem.EmployeeItem) {

    }

}