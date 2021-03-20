package com.mishok.polygo.ui.search

import com.mishok.polygo.base.api.BaseViewState
import com.mishok.polygo.ui.base.CreateAdapterListItem

data class SearchState(
        val isActive: Boolean = true,
        val list: List<CreateAdapterListItem.SearchItem> = emptyList()
) : BaseViewState