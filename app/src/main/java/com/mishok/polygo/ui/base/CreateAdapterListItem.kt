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

    data class EmployeeItem(
        val id: Long,
        val title: String
    ) : CreateAdapterListItem()

    data class BuildingItem(
        val id: Long,
        val title: String,
        val latitude: Double,
        val longitude: Double
    ) : CreateAdapterListItem()

    data class BuildingInfoItem(
        val id: Long,
        val title: String
    ) : CreateAdapterListItem()

    data class BuildingTitleItem(
        val title: String
    ) : CreateAdapterListItem()

    data class ChipItem(
        val title: String
    )

}
