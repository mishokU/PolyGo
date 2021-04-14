package com.mishok.polygo.domain.mappers

import com.mishok.core_db_api.models.LocalBuildingInfo
import com.mishok.core_db_api.models.LocalBuildings
import com.mishok.core_db_api.models.LocalEmployees
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
            title = it.title,
            longitude = it.longitude,
            latitude = it.latitude
        )
    }
}

fun List<LocalBuildingInfo>.toUIBuildingInfoModel(): List<CreateAdapterListItem> {
    return map {
        CreateAdapterListItem.BuildingInfoItem(
            id = it.id,
            title = it.title
        )
    }
}