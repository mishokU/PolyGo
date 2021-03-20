package com.mishok.polygo.db.api.models

import com.mishok.polygo.ui.search.SearchTypes

data class LocalSearching(
        val id: Long,
        val type: SearchTypes,
        val title: String,
        val time: Long
)