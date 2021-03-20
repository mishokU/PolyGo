package com.mishok.polygo.ui.base

sealed class CreateAdapterListItem {

    data class SearchItem(
            val id: Long,
            val title: String
    ) : CreateAdapterListItem()

    data class BookmarkItem(
            val id: Long,
            val title: String
    ) : CreateAdapterListItem()


}
