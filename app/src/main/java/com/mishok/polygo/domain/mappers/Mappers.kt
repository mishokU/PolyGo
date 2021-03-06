package com.mishok.polygo.domain.mappers

import com.mishok.core_db_api.models.LocalBuildingInfo
import com.mishok.core_db_api.models.LocalBuildings
import com.mishok.core_db_api.models.LocalEmployees
import com.mishok.polygo.ui.base.CreateAdapterListItem

fun List<LocalEmployees>.toUIEmployeeModel(): List<CreateAdapterListItem> {
    return map {
        CreateAdapterListItem.EmployeeItem(
            id = it.id,
            title = it.name,
            email = it.contacts,
            schedule = it.scheduleUrl,
            description = it.contacts,
            inBookmark = it.saved
        )
    }
}

fun List<LocalBuildings>.toUIBuildingModel(): List<CreateAdapterListItem> {
    return map {
        CreateAdapterListItem.BuildingItem(
            id = it.id,
            title = it.title,
            time = it.time,
            longitude = it.longitude,
            latitude = it.latitude,
            inBookmark = it.saved
        )
    }
}

fun List<LocalBuildingInfo>.toUIBuildingInfoModel(): List<CreateAdapterListItem.BuildingInfoItem> {
    return map {
        CreateAdapterListItem.BuildingInfoItem(
            description = it.category,
            category = it.category,
            inBookmark = it.saved,
            id = it.buildingId,
            title = it.title,
        )
    }
}

fun String.toTitleModel(): CreateAdapterListItem.BuildingTitleItem {
    return CreateAdapterListItem.BuildingTitleItem(
        title = this
    )
}

fun CreateAdapterListItem.BuildingInfoItem.toChipItem(): CreateAdapterListItem.ChipItem {
    return CreateAdapterListItem.ChipItem(
        title = this.category,
        buildingId = this.id
    )
}