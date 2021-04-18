package com.mishok.polygo.ui.building_inside.adapter

import com.mishok.polygo.ui.base.CreateAdapterListItem

interface ChipCallback {
    fun onChipClick(chip: CreateAdapterListItem.ChipItem)
    fun onResetClick()
}