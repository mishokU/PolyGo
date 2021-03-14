package com.mishok.polygo.start

import com.mishok.polygo.base.api.BaseViewState

data class StartState(
        val isOpen: Boolean = false,
        val finish: Boolean = false
) : BaseViewState