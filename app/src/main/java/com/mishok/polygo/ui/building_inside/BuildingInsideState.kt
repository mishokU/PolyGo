package com.mishok.polygo.ui.building_inside

import com.mishok.polygo.base.api.BaseViewState
import com.mishok.polygo.ui.base.CreateAdapterListItem

data class BuildingInsideState(
    val isActive: Boolean = true,
    val map: Map<String, List<CreateAdapterListItem.BuildingInfoItem>> = emptyMap(),
    val chips: List<Any> = emptyList(),
    val employee: CreateAdapterListItem.EmployeeItem? = null,
    val building: CreateAdapterListItem.BuildingItem? = null,
) : BaseViewState