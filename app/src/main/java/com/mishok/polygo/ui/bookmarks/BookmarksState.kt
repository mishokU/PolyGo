package com.mishok.polygo.ui.bookmarks

import com.mishok.polygo.base.api.BaseViewState
import com.mishok.polygo.ui.base.CreateAdapterListItem
import com.mishok.polygo.utils.filter.SearchFilter

data class BookmarksState(
    val isLoading: Boolean = true,
    val isError: Boolean = false,
    val isEmpty: Boolean = false,
    val clearText: Boolean = false,
    val crossMark: Boolean = false,
    val lastFilter: SearchFilter = SearchFilter.ALL,
    val list: List<CreateAdapterListItem> = emptyList(),
    val employee: CreateAdapterListItem.EmployeeItem? = null,
    val building: CreateAdapterListItem.BuildingItem? = null,
) : BaseViewState