package com.mishok.polygo.ui.search.adapter

import com.mishok.polygo.ui.base.CreateAdapterListItem

abstract class SearchCallbackWrapper : SearchCallback {
    override fun onBuildingClick(building: CreateAdapterListItem.BuildingItem) {}
    override fun onBuildingBookmarkClick(item: CreateAdapterListItem.BuildingItem) {}
    override fun onBuildingInfoItemClick(item: CreateAdapterListItem.BuildingInfoItem) {}
    override fun onEmployeeBookmarkClick(item: CreateAdapterListItem.EmployeeItem) {}
    override fun onEmployeeClick(employee: CreateAdapterListItem.EmployeeItem) {}
    override fun onBuildingInfoBookmarkClick(item: CreateAdapterListItem.BuildingInfoItem) {}
}

interface OnBuildingClickCallback {
    fun onBuildingClick(building: CreateAdapterListItem.BuildingItem)
}

interface OnBuildingBookmarkClickCallback {
    fun onBuildingBookmarkClick(item: CreateAdapterListItem.BuildingItem)
}

interface OnBuildingInfoBookmarkItemClickCallback {
    fun onBuildingInfoItemClick(item: CreateAdapterListItem.BuildingInfoItem)
}

interface OnEmployeeBookmarkClickCallback {
    fun onEmployeeBookmarkClick(item: CreateAdapterListItem.EmployeeItem)
}

interface OnEmployeeClickCallback {
    fun onEmployeeClick(employee: CreateAdapterListItem.EmployeeItem)
}

interface SearchCallback {
    fun onBuildingClick(building: CreateAdapterListItem.BuildingItem)
    fun onBuildingBookmarkClick(item: CreateAdapterListItem.BuildingItem)
    fun onBuildingInfoItemClick(item: CreateAdapterListItem.BuildingInfoItem)
    fun onBuildingInfoBookmarkClick(item: CreateAdapterListItem.BuildingInfoItem)
    fun onEmployeeBookmarkClick(item: CreateAdapterListItem.EmployeeItem)
    fun onEmployeeClick(employee: CreateAdapterListItem.EmployeeItem)

}