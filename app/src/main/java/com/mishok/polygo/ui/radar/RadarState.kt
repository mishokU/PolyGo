package com.mishok.polygo.ui.radar

import com.mishok.polygo.base.api.BaseViewState

data class RadarState(
    val isLoading: Boolean = true,
    val isCamera: Boolean = false
) : BaseViewState