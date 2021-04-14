package com.mishok.polygo.domain.building_inside

import com.mishok.polygo.ui.base.CreateAdapterListItem
import kotlinx.coroutines.flow.Flow

interface BuildingInsideInteractor {
    suspend fun loadBuildingInfoByBuildingId(buildingId: Long): Flow<List<CreateAdapterListItem>>
}