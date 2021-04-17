package com.mishok.polygo.ui.search

import com.mishok.polygo.base.api.BaseViewState
import com.mishok.polygo.ui.base.CreateAdapterListItem
import com.mishok.polygo.utils.filter.SearchFilter

data class SearchState(
    val isActive: Boolean = true,
    val list: List<Any> = emptyList(),
    val lastFilter: SearchFilter = SearchFilter.ALL,
    val employee: CreateAdapterListItem.EmployeeItem? = null,
    val building: CreateAdapterListItem.BuildingItem? = null,
) : BaseViewState