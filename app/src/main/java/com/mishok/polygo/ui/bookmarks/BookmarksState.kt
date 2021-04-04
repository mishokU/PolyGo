package com.mishok.polygo.ui.bookmarks

import com.mishok.polygo.base.api.BaseViewState
import com.mishok.polygo.ui.base.CreateAdapterListItem

data class BookmarksState(
        val isLoading: Boolean = true,
        val isError: Boolean = false,
        val isEmpty: Boolean = false,
        val list: List<CreateAdapterListItem> = emptyList(),
        val employee: CreateAdapterListItem.EmployeeItem? = null,
        val building: CreateAdapterListItem.BuildingItem? = null,
) : BaseViewState