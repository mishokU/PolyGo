package com.mishok.polygo.ui.bookmarks

import com.mishok.polygo.base.api.BaseViewState

data class BookmarksState(
    val isLoading: Boolean = true,
    val isError: Boolean = false,
    val isEmpty: Boolean = false,
    val list: List<Any> = emptyList()
) : BaseViewState