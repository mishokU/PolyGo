package com.mishok.polygo.ui.search.adapter

import com.mishok.polygo.ui.base.CreateAdapterListItem

interface SearchCallback {
    fun onBuildingClick(building: CreateAdapterListItem.BuildingItem)
    fun onEmployeeClick(employee: CreateAdapterListItem.EmployeeItem)
}