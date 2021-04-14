package com.mishok.polygo.domain.map

import com.mishok.core_db_api.providers.BuildingsProvider
import com.mishok.polygo.domain.mappers.toUIBuildingModel
import com.mishok.polygo.ui.base.CreateAdapterListItem
import com.yandex.mapkit.geometry.Point
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MapInteractorImpl @Inject constructor(
    private val buildingsProvider: BuildingsProvider
) : MapInteractor {

    override suspend fun loadNearCorps(point: Point): Flow<List<CreateAdapterListItem>> {
        return buildingsProvider.getBuildingsByPoint(point).map {
            it.toUIBuildingModel()
        }
    }
}