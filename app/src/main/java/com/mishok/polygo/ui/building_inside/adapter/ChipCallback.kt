package com.mishok.polygo.ui.building_inside.adapter

import com.mishok.polygo.ui.base.CreateAdapterListItem

interface ChipCallback {
    fun onChipClick(building: CreateAdapterListItem.ChipItem)
}