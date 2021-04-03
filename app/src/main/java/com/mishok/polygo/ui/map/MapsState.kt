package com.mishok.polygo.ui.map

import com.mishok.polygo.base.api.BaseViewState
import com.mishok.polygo.ui.base.CreateAdapterListItem

data class MapsState(
    val isMapLoading: Boolean = true,
    val items: List<CreateAdapterListItem> = emptyList(),
    val isFineLocationGranted: Boolean = false,
    val moveCamera: Boolean = false
) : BaseViewState