package com.mishok.polygo.ui.map

import com.mishok.polygo.base.api.BaseViewState
import com.mishok.polygo.ui.base.CreateAdapterListItem

data class MapsState(
    val isMapLoading: Boolean = true,
    val items: List<CreateAdapterListItem> = emptyList(),
    val building: CreateAdapterListItem.BuildingItem = CreateAdapterListItem.BuildingItem(
        title = "Поблизости нет зданий",
        longitude = MapsFragment.POLITECH_LONGITUDE,
        latitude = MapsFragment.POLITECH_LATITUDE,
        id = 0
    ),
    val isFineLocationGranted: Boolean = false,
    val moveCamera: Boolean = false
) : BaseViewState