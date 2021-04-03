package com.mishok.polygo.domain.map

import com.mishok.polygo.ui.base.CreateAdapterListItem
import com.yandex.mapkit.geometry.Point
import kotlinx.coroutines.flow.Flow

interface MapInteractor {
    suspend fun loadNearCorps(point: Point): Flow<List<CreateAdapterListItem>>
}