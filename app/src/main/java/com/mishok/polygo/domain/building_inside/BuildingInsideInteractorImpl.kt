package com.mishok.polygo.domain.building_inside

import com.mishok.core_db_api.providers.BuildingInfoProvider
import com.mishok.polygo.domain.mappers.toUIBuildingInfoModel
import com.mishok.polygo.ui.base.CreateAdapterListItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class BuildingInsideInteractorImpl @Inject constructor(
    private val buildingInfoProvider: BuildingInfoProvider
) : BuildingInsideInteractor {

    override suspend fun loadBuildingInfoByBuildingId(buildingId: Long)
            : Flow<List<CreateAdapterListItem.BuildingInfoItem>> {
        return buildingInfoProvider.getAllBuildingInfoByBuildingId(buildingId).map {
            it.toUIBuildingInfoModel()
        }
    }

    override suspend fun filterBuildingInfoByCategory(category: CreateAdapterListItem.ChipItem)
            : Flow<List<CreateAdapterListItem.BuildingInfoItem>> {
        return buildingInfoProvider.filterBuildingInfoByCategory(
            category.buildingId,
            category.title
        ).map {
            it.toUIBuildingInfoModel()
        }
    }
}