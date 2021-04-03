package com.mishok.polygo.domain.mappers

import com.mishok.polygo.db.api.models.LocalBuildings
import com.mishok.polygo.db.api.models.LocalEmployees
import com.mishok.polygo.ui.base.CreateAdapterListItem

fun List<LocalEmployees>.toUIEmployeeModel(): List<CreateAdapterListItem> {
    return map {
        CreateAdapterListItem.EmployeeItem(
            id = it.id,
            title = it.name
        )
    }
}

fun List<LocalBuildings>.toUIBuildingModel(): List<CreateAdapterListItem> {
    return map {
        CreateAdapterListItem.BuildingItem(
            id = it.id,
            title = it.title
        )
    }
}