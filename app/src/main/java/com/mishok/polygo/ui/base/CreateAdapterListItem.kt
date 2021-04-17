package com.mishok.polygo.ui.base

import com.google.android.datatransport.cct.StringMerger

sealed class CreateAdapterListItem {

    data class SearchItem(
        val id: Long,
        val title: String
    ) : CreateAdapterListItem()

    data class BookmarkItem(
        val id: Long,
        val title: String
    ) : CreateAdapterListItem()

    data class EmployeeItem(
        val id: Long,
        val title: String,
        val description: String,
        val schedule: String,
        val email: String,
        val inBookmark: Boolean
    ) : CreateAdapterListItem()

    data class BuildingItem(
        val id: Long,
        val title: String,
        val latitude: Double,
        val longitude: Double,
        val inBookmark: Boolean
    ) : CreateAdapterListItem()

    data class BuildingInfoItem(
        val id: Long,
        val title: String,
        val description: String,
        val category: String,
        val inBookmark: Boolean
    ) : CreateAdapterListItem()

    data class BuildingTitleItem(
        val title: String
    ) : CreateAdapterListItem()

    data class ChipItem(
        val title: String
    )

}
