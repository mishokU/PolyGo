package com.mishok.polygo.ui.search.adapter

import com.mishok.polygo.ui.base.CreateAdapterListItem

interface SearchCallback {
    fun onSearchClick(search: CreateAdapterListItem.SearchItem)
}