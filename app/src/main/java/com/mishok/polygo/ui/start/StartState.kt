package com.mishok.polygo.ui.start

import com.mishok.polygo.base.api.BaseViewState

data class StartState(
    val isOpen: Boolean = false,
    val finish: Boolean = false,
    val searchOpen: Boolean = false,
    val radarOpen: Boolean = false,
    val bookmarksOpen: Boolean = false,
    val mapOpen: Boolean = false
) : BaseViewState